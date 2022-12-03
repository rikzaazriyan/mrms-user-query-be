package com.rikza.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import com.rikza.dao.UserDao;
import com.rikza.model.CustomResponse;
import com.rikza.model.User;

import io.agroal.api.AgroalDataSource;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    AgroalDataSource dataSource;

    public Jdbi jdbi;

    @PostConstruct
    public void init() {
        jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    @Path("/")
    @GET
    public Response getUser(User user) {
        List<User> userList = jdbi.withExtension(
                UserDao.class,
                dao -> dao.readAllUsers());

        CustomResponse cr = new CustomResponse();
        cr.data = userList;
        return Response.ok(cr).build();
    }

}
