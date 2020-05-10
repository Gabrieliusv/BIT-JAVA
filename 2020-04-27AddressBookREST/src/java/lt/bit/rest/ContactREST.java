package lt.bit.rest;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
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
import lt.bit.data.Address;
import lt.bit.data.Contact;
import lt.bit.data.DB;

/**
 *
 * @author Gabrielius
 */
@Path("person/{id}/contact")
public class ContactREST {
    
    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Contact> list(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        List<Contact> list = DB.getPersonContacts(em, id);
        return list;
    }
    
     @GET
    @Path("{contactId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Contact getContact(@PathParam("contactId") Integer id, @PathParam("id") Integer personId) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Contact contact = DB.getContact(em, id);
        if(contact.getPerson().getId() + 0 == personId + 0){
        return contact;
        } else{
        return null;
        }
    }
    
    @DELETE
    @Path("{contactId}")
    public void removeContact(@PathParam("contactId") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.removeContact(em, id);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Contact addPersonContact(Contact c, @PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.addPersonContact(em, id, c);
        return c;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("{contactId}")
    public Contact updateContact(@PathParam("contactId") Integer id, @PathParam("id") Integer personId, Contact c) {
        c.setId(id);
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.updateContact(em, c);
        return c;
    }

    
}
