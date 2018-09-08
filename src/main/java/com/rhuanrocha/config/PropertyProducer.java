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
                .fileName(), key);

    }

    private String readFromPath(String fileName, String key){

        try(InputStream in = new FileInputStream( readPathConfigurationStore() + fileName)){

            Properties properties = new Properties();
            properties.load( in );

            return properties.getProperty( key );

        } catch ( Exception e ) {
            e.printStackTrace();
            throw new PropertyException("Error to read property.");
        }


    }

    private String readPathConfigurationStore(){

        Properties configStore = new Properties();

        try( InputStream stream = PropertyProducer.class
                .getResourceAsStream("/configurationStore.properties") ) {

            configStore.load(stream);
        }
        catch ( Exception e ) {
            e.printStackTrace();
            throw new PropertyException("Error to read property.");
        }

        return configStore.getProperty("path");
    }

}
