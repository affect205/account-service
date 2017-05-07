package com.alexside.mapper;

import com.alexside.dto.AccountDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 07.05.2017.
 */
public class AccountRowMapper implements RowMapper<AccountDTO> {
    @Override
    public AccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountDTO dto = new AccountDTO();
        dto.setId(rs.getInt("id"));
        dto.setNumber(rs.getString("num"));
        dto.setBalance(rs.getDouble("balance"));
        return dto;
    }
}
