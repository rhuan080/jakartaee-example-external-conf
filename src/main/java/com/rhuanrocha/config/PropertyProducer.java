package com.rhuanrocha.config;

import com.rhuanrocha.exception.PropertyException;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


/**
 *
 * @author rhuan rocha
 */


public class PropertyProducer {

    @Property
    @Produces
    public String readProperty(InjectionPoint point){

        String key = point
                .getAnnotated()
                .getAnnotation(Property.class)
                .key();

        if( point
                .getAnnotated()
                .getAnnotation(Property.class)
                .container() ){

           String value = System.getProperty(key);

           if( Objects.nonNull(value) ){
               return value;
           }

        }

        return readFromPath(point
                .getAnnotated()
                .getAnnotation(Property.class)
                .path(), key);

    }

    private String readFromPath(String path, String key){

        try(InputStream in = new FileInputStream(path)){

            Properties properties = new Properties();
            properties.load( in );

            return properties.getProperty( key );

        } catch ( Exception e ) {
            e.printStackTrace();
            throw new PropertyException("Error to read property.");
        }


    }

}
