package com.alexside.rest;

import com.alexside.dto.AccountDTO;
import com.alexside.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Alex on 07.05.2017.
 */
@Component
@Path("/account")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
public class AccountRest {
    @Autowired
    private AccountService accountService;

    @GET
    @Path("/all")
    public List<AccountDTO> getAll() {
        List<AccountDTO> result = accountService.getAll();
        return result;
    }

    @GET
    @Path("{number}")
    public AccountDTO getByNumber(@PathParam("number") @DefaultValue("") String number) {
        AccountDTO result = accountService.getByNumber(number);
        return result;
    }
}
