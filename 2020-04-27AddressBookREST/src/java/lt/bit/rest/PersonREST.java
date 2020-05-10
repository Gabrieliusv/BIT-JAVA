package lt.bit.rest;

import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import lt.bit.data.DB;
import lt.bit.data.Person;

@Path("person")
public class PersonREST {

    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Person> list() {
        EntityManager em = (EntityManager) request.getAttribute("em");
        List<Person> list = DB.getPersons(em);
        return list;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Person getPerson(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Person person = DB.getPerson(em, id);
        return person;
    }

    @DELETE
    @Path("{id}")
    public void removePerson(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.removePerson(em, id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Person createPerson(Person p) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.addPerson(em, p);
        return p;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("{id}")
    public Person updatePerson(@PathParam("id") Integer id, Person p) {
        p.setId(id);
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.updatePerson(em, p);
        return p;
    }
}
