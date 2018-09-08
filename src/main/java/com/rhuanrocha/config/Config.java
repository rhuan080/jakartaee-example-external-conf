package com.rhuanrocha.config;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 *
 * @author rhuan rocha
 */


@Singleton
public class Config {


    @Inject
    @Property(key="message.welcome")
    public String WELCOME;

    @Inject
    @Property(key="message.welcome", container = false, fileName = "config.properties")
    public String WELCOME_EXTERNAL_FILE;

    @Inject
    @Property(key="path.download")
    public String PATH_DOWNLOAD;

    @Inject
    @Property(key="path.upload")
    public String PATH_UPLOAD;



}
