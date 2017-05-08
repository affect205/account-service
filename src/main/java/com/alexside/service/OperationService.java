package com.alexside.service;

import com.alexside.dto.AccountDTO;
import com.alexside.dto.OperationDTO;
import com.alexside.dto.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.alexside.utils.MappingUtils.OPERATION_ROW_MAPPER;
import static java.lang.String.format;

/**
 * Created by Alex on 07.05.2017.
 */
@Service
public class OperationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountService accountService;

    public List<OperationDTO> getByNumber(String number) {
        String sql = "select " +
                "  operation.id as id, " +
                "  operation.amount as amount, " +
                "  operation.operation_type as type, " +
                "  operation.operation_date as date, " +
                "  operation.description as description, " +
                "  sender.num as sender, " +
                "  recipient.num as recipient " +
                " from operation " +
                " join account sender on operation.sender_id = sender.id " +
                " join account recipient on operation.recipient_id = recipient.id " +
                " join operation_type on operation.operation_type = operation_type.id " +
                " where recipient.num = ? or sender.num = ? " +
                " order by operation_date";
        List<OperationDTO> result = jdbcTemplate.query(sql, new Object[] { number, number }, OPERATION_ROW_MAPPER);
        return result;
    }

    @Transactional
    public boolean doTransfer(TransferDTO dto) {
        try {
            AccountDTO sender = accountService.getByNumber(dto.getSender());
            if (sender == null) {
                throw new RuntimeException(format("Sender account with number %s doesn't exist", dto.getSender()));
            }
            AccountDTO recipient = accountService.getByNumber(dto.getRecipient());
            if (recipient == null) {
                throw new RuntimeException(format("Recipient account with number %s doesn't exist", dto.getRecipient()));
            }
            if ((sender.getBalance() - dto.getAmount()) < 0) {
                throw new RuntimeException(format("Sender's account balance is less than transfer amount"));
            }
            if (Objects.equals(dto.getSender(), dto.getRecipient())) {
                throw new RuntimeException(format("Transfer between the same account cannot be performed"));
            }

            String sql = "insert into operation(operation_type, sender_id, recipient_id, operation_date, amount, description) values (?, ?, ?, ?, ?, ?)";
            Object[] params = new Object[] { 1, sender.getId(), recipient.getId(), new Date(), dto.getAmount(), dto.getDescription() };
            int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.NUMERIC, Types.VARCHAR };
            int operationRows = jdbcTemplate.update(sql, params, types);

            sql = "update account set balance = (round(balance, 2) - round(?, 2)) where id = ? ";
            params = new Object[] { dto.getAmount(), sender.getId() };
            types = new int[] { Types.NUMERIC, Types.INTEGER };
            int senderRows = jdbcTemplate.update(sql, params, types);

            sql = "update account set balance = (round(balance, 2) + round(?, 2)) where id = ? ";
            params = new Object[] { dto.getAmount(), recipient.getId() };
            types = new int[] { Types.NUMERIC, Types.INTEGER };
            int recipientRows = jdbcTemplate.update(sql, params, types);

            return operationRows > 0 && senderRows > 0 && recipientRows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
