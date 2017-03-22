package ru.atom.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.ConcurrentArrayQueue;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/chat")
public class ChatResource  {
    private ChatDAO chat = ChatUtil.getInstance();
    private UserServiceDAO userservicedao = UserService.getInstance();
    private static final Logger log = LogManager.getLogger(ChatResource.class);

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/login")
    public Response login(@QueryParam("name") String name) {
        try {
            User user = userservicedao.getUserByName(name);
        } catch (NullPointerException nu) {
            try {
                if (name.length()>30) {
                    throw new RuntimeException("Too long name (longer than 30 symbols)");
                }
            } catch (RuntimeException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } finally {
                chat.login(name);
                log.info("Success");
                return Response.status(Response.Status.OK).build();
            }
        } finally {
            log.error("Already loggined");
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}

    @GET
    @Produces("text/plain")
    @Path("/online")
    public Response online() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/say")
    public Response say(@QueryParam("name") String name, @FormParam("msg") String msg) {
        try {
            User user = userservicedao.getUserByName(name);
        } catch (NullPointerException nu) {
            log.error("Not logined");
            return Response.status(Response.Status.BAD_REQUEST).build();
        } finally {
            try {
                if (msg.length()>140) {
                    throw new RuntimeException("Too long message (longer than 140 symbols)");
                }
            } catch (RuntimeException e) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } finally {
                chat.sendMessage(name, msg);
                log.info("Success");
                return Response.status(Response.Status.OK).build();
            }
        }
    }

    @GET
    @Produces("text/plain")
    @Path("/chat")
    public Response chat() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Path("/logout")
    public Response logout(@QueryParam("name") String name) {
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
