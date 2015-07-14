package com.zenika.user.restserver;

import com.zenika.user.restservice.Address;
import com.zenika.user.restservice.FlatUser;
import com.zenika.user.restservice.UnkownUserException;
import com.zenika.user.restservice.User;
import org.apache.camel.Header;

/**
 * Created by armel on 10/07/15.
 */
public class UserManager {

    public User getUser(@Header("id") int id) throws UnkownUserException {
        if (id == 1) {
            User user = new User(1, "toto");
            Address address = new Address();
            address.setPostcode(35000);
            address.setTown("Rennes");
            user.setAddress(address);
            return user;
        } else {
            throw new UnkownUserException("User d'id inconnu: " + id);
        }
    }

    public FlatUser getFlatUser(@Header("id") int id) throws UnkownUserException {
        if (id == 1) {
            FlatUser user = new FlatUser(1, "toto");
            user.setAddressStreet("1_ rue de la monnaie");
            user.setAddressTown("Rennes");
            return user;
        } else {
            throw new UnkownUserException("User d'id inconnu: " + id);
        }
    }
}
