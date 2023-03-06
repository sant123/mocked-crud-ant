/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.santi;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author sant821
 */
@Path("people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    @Context
    private UriInfo context;
    
    @EJB
    private PeopleDAO peopleDAO;

    @GET
    public ArrayList<Person> getAll() {
        return peopleDAO.getAll();
    }

    @GET
    @Path("{id}")
    public Response getByID(@PathParam("id") int id) {
        Person p = peopleDAO.getByID(id);
        
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(p).build();
    }

    @POST
    public Response create(Person p) {
        peopleDAO.create(p);
        return Response.status(Response.Status.CREATED).entity(p).build();
    }

    @PUT
    public Response update(Person p) {
        try {
            peopleDAO.update(p);
            return Response.ok(p).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response destroy(@PathParam("id") int id) {
        try {
            peopleDAO.destroy(id);
            return Response.noContent().build();
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
