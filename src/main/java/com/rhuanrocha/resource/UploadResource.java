package com.rhuanrocha.resource;


import com.rhuanrocha.dao.FileDao;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URI;

/**
 *
 * @author rhuan rocha
 */


@Path("/upload")
public class UploadResource {

    @Inject
    private FileDao fileDao;

    @POST
    public Response upload(@NotNull File file){

        if( fileDao.save( file ) ){
            return Response
                    .created(URI.create("/download?fileName="+ file.getName()))
                    .build();
        }

        return Response.serverError().build();

    }
}
