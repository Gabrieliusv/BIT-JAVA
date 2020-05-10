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
import lt.bit.data.Address;
import lt.bit.data.DB;

@Path("person/{id}/address")
public class AddressREST {

    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public List<Address> list(@PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        List<Address> list = DB.getPersonAddresses(em, id);
        return list;
    }
    
     @GET
    @Path("{addressId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Address getAddress(@PathParam("addressId") Integer id, @PathParam("id") Integer personId) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        Address address = DB.getAddress(em, id);
        if(address.getPerson().getId() + 0 == personId + 0){
        return address;
        } else{
        return null;
        }
    }
    
    @DELETE
    @Path("{addressId}")
    public void removeAddress(@PathParam("addressId") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.removeAddress(em, id);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Address addPersonAddress(Address a, @PathParam("id") Integer id) {
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.addPersonAddress(em, id, a);
        return a;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("{addressId}")
    public Address updateAddress(@PathParam("addressId") Integer id, Address a) {
        a.setId(id);
        EntityManager em = (EntityManager) request.getAttribute("em");
        DB.updateAddress(em, a);
        return a;
    }

}
