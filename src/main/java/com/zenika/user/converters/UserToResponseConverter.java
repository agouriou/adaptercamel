package com.zenika.user.converters;

import com.zenika.user.restservice.FlatUser;
import com.zenika.user.soapservice.Address;
import com.zenika.user.soapservice.GetUserResponse;
import com.zenika.user.soapservice.User;
import org.apache.camel.Converter;

/**
 * Created by armel on 10/07/15.
 */
@Converter
public class UserToResponseConverter {

    @Converter
    public static GetUserResponse toGetUserResponse(User user) {
        GetUserResponse rep = new GetUserResponse();
        rep.setUser(user);
        return rep;
    }
}
