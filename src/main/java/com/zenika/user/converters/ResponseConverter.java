package com.zenika.user.converters;

import com.zenika.user.restservice.User;
import org.apache.camel.Converter;

import javax.ws.rs.core.Response;

/**
 * Created by armel on 15/07/15.
 */
@Converter
public class ResponseConverter {

    @Converter
    public static User toUser(Response response){
        return (User) response.getEntity();
    }
}
