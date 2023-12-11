package com.game.start.GameStart.REST;

import com.game.start.GameStart.entity.Address;
import com.game.start.GameStart.entity.User;
import com.game.start.GameStart.jpa.AddressRepository;
import com.game.start.GameStart.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Profile {
    @Autowired
    private AddressRepository adres;
    @Autowired
    Login sessions;
    @Autowired
    private UserRepository users;

    @PutMapping("/api/profile")
    String editAddres(@RequestBody Address address){
        User u = sessions.getUser(null);
        if(u==null)return "{\"ok\":false}";
        address.setId(0L);
        address = adres.save(address);
        u.getClient().setAddress(address);
        users.save(u);
        return "{\"ok\":true}";
    }
}
