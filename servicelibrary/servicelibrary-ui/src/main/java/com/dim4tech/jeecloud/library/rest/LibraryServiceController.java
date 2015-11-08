package com.dim4tech.jeecloud.library.rest;

import com.dim4tech.jeecloud.domain.*;
import com.dim4tech.jeecloud.library.domain.ServiceDescription;
import com.dim4tech.jeecloud.library.service.LibraryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("service")
@RequestScoped
public class LibraryServiceController {
    @Inject
    private LibraryService libraryService;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public JEECloudResponse<ServiceDescription> getServiceDescriptionWithMinimalLoad(@PathParam("name") String name) {
        try {
            return new JEECloudResponse<ServiceDescription>(libraryService.getServiceDescriptionWithMinimalLoad(name), null);
        }
        catch (Exception ex) {
            return new JEECloudResponse(null, new JEECloudError(ex.getMessage()));
        }
    }
}
