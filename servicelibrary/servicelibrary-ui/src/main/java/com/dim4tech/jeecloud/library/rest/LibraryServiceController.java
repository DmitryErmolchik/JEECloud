package com.dim4tech.jeecloud.library.rest;

import com.dim4tech.jeecloud.core.domain.*;
import com.dim4tech.jeecloud.core.service.sessioninfo.SessionInfoService;
import com.dim4tech.jeecloud.library.domain.ServiceDescription;
import com.dim4tech.jeecloud.library.service.LibraryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URL;
import java.util.Set;

@Path("service")
@RequestScoped
public class LibraryServiceController {
    @Context
    private UriInfo uriInfo;

    @Inject
    private SessionInfoService sessionInfoService;

    @Inject
    private LibraryService libraryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JEECloudResponse<Set<ServiceDescription>> getServiceDescriptions(@DefaultValue("en") @QueryParam("lang") String language) {
        sessionInfoService.setLocale(language);
        return new JEECloudResponse(libraryService.getAvailableServices(), null);
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public JEECloudResponse<ServiceDescription> getServiceDescriptionWithMinimalLoad(@PathParam("name") String name,
                                                                                     @DefaultValue("en") @QueryParam("lang") String language) {
        try {
            sessionInfoService.setLocale(language);
            return new JEECloudResponse<ServiceDescription>(libraryService.getServiceDescriptionWithMinimalLoad(name), null);
        }
        catch (Exception ex) {
            return new JEECloudResponse(null, new JEECloudError(ex.getMessage()));
        }
    }

    @GET
    @Path("{name}/{url}")
    @Produces(MediaType.APPLICATION_JSON)
    public JEECloudResponse<ServiceDescription> getServiceDescription(@PathParam("name") String name,
                                                                      @PathParam("url") URL url,
                                                                      @DefaultValue("en") @QueryParam("lang") String language) {
        try {
            sessionInfoService.setLocale(language);
            return new JEECloudResponse<ServiceDescription>(libraryService.get(name, url), null);
        }
        catch (Exception ex) {
            return new JEECloudResponse(null, new JEECloudError(ex.getMessage()));
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createServiceDescription(ServiceDescription serviceDescription) {
        libraryService.add(serviceDescription);
        URI serviceUri = uriInfo.getAbsolutePathBuilder().path(serviceDescription.getName()).build();
        return Response.created(serviceUri).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateServiceDescription(ServiceDescription serviceDescription) {
        libraryService.add(serviceDescription);
        URI serviceUri = uriInfo.getAbsolutePathBuilder().path(serviceDescription.getName()).build();
        return Response.ok(serviceUri).build();
    }
}
