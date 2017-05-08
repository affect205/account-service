package com.alexside.mapper;

import com.alexside.dto.OperationDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 07.05.2017.
 */
public class OperationRowMapper implements RowMapper<OperationDTO> {
    @Override
    public OperationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OperationDTO dto = new OperationDTO();
        dto.setId(rs.getInt("id"));
        dto.setDate(rs.getDate("date").getTime());
        dto.setType(rs.getString("type"));
        dto.setSender(rs.getString("sender"));
        dto.setRecipient(rs.getString("recipient"));
        dto.setAmount(rs.getDouble("amount"));
        dto.setDescription(rs.getString("description"));
        return dto;
    }
}
