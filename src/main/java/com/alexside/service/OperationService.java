package com.alexside.service;

import com.alexside.dto.OperationDTO;
import com.alexside.dto.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import static com.alexside.utils.MappingUtils.OPERATION_ROW_MAPPER;

/**
 * Created by Alex on 07.05.2017.
 */
@Service
public class OperationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OperationDTO> getByNumber(String number) {
        String sql = "select " +
                "  operation.id as id, " +
                "  operation.amount as amount, " +
                "  operation.operation_type as type, " +
                "  operation.operation_date as date, " +
                "  operation.description as description, " +
                "  sender.id as senderid, " +
                "  sender.num as sendernum, " +
                "  sender.balance as senderbalance, " +
                "  recipient.id as recipientid, " +
                "  recipient.num as recipientnum, " +
                "  recipient.balance as recipientbalance " +
                " from operation " +
                " join account sender on operation.sender_id = sender.id " +
                " join account recipient on operation.recipient_id = recipient.id " +
                " join operation_type on operation.operation_type = operation_type.id " +
                " where recipient.num = ? or sender.num = ? " +
                " order by operation_date";
        List<OperationDTO> result = jdbcTemplate.query(sql, new Object[] { number, number }, OPERATION_ROW_MAPPER);
        return result;
    }

    public boolean doTransfer(TransferDTO dto) {
        // TODO: добавляем проверки: остаток на счете, enum для типов операций и т.д.
        try {
            String sql = "insert into operation(operation_type, sender_id, recipient_id, operation_date, amount, description) values (?, ?, ?, ?, ?, ?)";
            Object[] params = new Object[] { 1, dto.getSenderId(), dto.getRecipientId(), new Date(), dto.getAmount(), dto.getDescription() };
            int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.NUMERIC, Types.VARCHAR };
            int rows = jdbcTemplate.update(sql, params, types);
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
