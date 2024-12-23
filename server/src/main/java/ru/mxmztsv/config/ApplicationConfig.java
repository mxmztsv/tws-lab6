package ru.mxmztsv.config;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import ru.mxmztsv.app.rest.ClientRestService;
import ru.mxmztsv.app.rest.exception.GlobalExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(ClientRestService.class);
        resources.add(GlobalExceptionHandler.class);
        return resources;
    }
}
