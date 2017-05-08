package com.alexside.rest;

import com.alexside.dto.OperationDTO;
import com.alexside.dto.TransferDTO;
import com.alexside.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Alex on 07.05.2017.
 */
@Component
@Path("/operation")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
public class OperationRest {
    @Autowired
    private OperationService operationService;

    @GET
    @Path("{number}")
    public List<OperationDTO> getByNumber(@PathParam("number") @DefaultValue("") String number) {
        List<OperationDTO> result = operationService.getByNumber(number);
        return result;
    }

    @POST
    @Path("transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Boolean> doTransfer(TransferDTO dto) {
        boolean success = operationService.doTransfer(dto);
        return ResponseEntity.status(200).body(success);
    }
}
