package com.alexside.service;

import com.alexside.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alexside.utils.MappingUtils.ACCOUNT_ROW_MAPPER;

/**
 * Created by Alex on 07.05.2017.
 */
@Service
public class AccountService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<AccountDTO> getAll() {
        List<AccountDTO> result;
        result = jdbcTemplate.query("SELECT * FROM account", ACCOUNT_ROW_MAPPER);
        return result;
    }

    public AccountDTO getByNumber(String number) {
        try {
            AccountDTO result = jdbcTemplate.queryForObject("SELECT * FROM account WHERE num = ?",
                    new Object[] { number }, ACCOUNT_ROW_MAPPER);
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
