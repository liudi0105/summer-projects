package com.github.liudi0105.helidon.admin.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;

@Path("/greet")
@RequestScoped
public class GreetResource {

    @GET
    @Path("aaa")
    @Produces(MediaType.APPLICATION_JSON)
    public Result getDefaultMessage() {
        return new Result("a", "b");
    }

    @Getter
    @Setter
    public static class Result {
        private String msg;
        private String code;

        public Result(String msg, String code) {
            this.msg = msg;
            this.code = code;
        }
    }
}
