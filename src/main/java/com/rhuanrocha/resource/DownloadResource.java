package com.rhuanrocha.resource;

import com.rhuanrocha.dao.FileDao;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.Objects;

/**
 *
 * @author rhuan rocha
 */


@Path("/download")
public class DownloadResource {

    @Inject
    private FileDao fileDao;

    @GET
    public Response download(@NotNull @QueryParam("fileName") String fileName){

        File file = fileDao.find( fileName );

        if( Objects.isNull( file ) ){

            return Response.status(Response.Status.NOT_FOUND).build();

        }

        return Response.ok(file)
                .header("Content-Disposition",
                "attachment; filename=\"" + fileName + "\"")
                .build();
    }

}
