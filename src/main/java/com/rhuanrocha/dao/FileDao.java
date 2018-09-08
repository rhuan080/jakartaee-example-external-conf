package com.rhuanrocha.dao;

import com.rhuanrocha.config.Config;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.*;
import java.nio.file.Files;


/**
 *
 * @author rhuan rocha
 */


@Stateless
public class FileDao {

    @Inject
    private Config config;

    public boolean save( File file ){


        File fileToSave = new File(config.PATH_UPLOAD + "/" + file.getName());

        try (InputStream input = new FileInputStream( file )) {

            Files.copy( input, fileToSave.toPath() );

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public File find( String fileName ){

        File file = new File(config.PATH_DOWNLOAD + "/" + fileName);

        if( file.exists() && file.isFile() ) {

            return file;
        }

        return null;
    }

}
