package cz.kucharo2.rest;

import cz.kucharo2.common.model.RegisterNewAccountModel;
import cz.kucharo2.filter.Secured;
import cz.kucharo2.rest.model.FieldError;
import cz.kucharo2.rest.model.FormResponse;
import cz.kucharo2.rest.validator.FormValidatorUtil;
import cz.kucharo2.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @Author Pavel Štíbal <stibapa1@fel.cvut.cz>.
 */
@Path("account")
public class AccountEndpoint {

    @Inject
    AccountService accountService;

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public FormResponse registerNewAccount(RegisterNewAccountModel model) {
        List<FieldError> errors = FormValidatorUtil.validate(model);

        if (errors.isEmpty()) {
            // todo add logic for successfully filled form
        }

        return new FormResponse(null, errors);
    }
}
