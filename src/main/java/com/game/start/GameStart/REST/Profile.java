package com.game.start.GameStart.REST;

import com.game.start.GameStart.entity.Address;
import com.game.start.GameStart.entity.Client;
import com.game.start.GameStart.entity.User;
import com.game.start.GameStart.jpa.AddressRepository;
import com.game.start.GameStart.jpa.ClientRepository;
import com.game.start.GameStart.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Profile {
    @Autowired
    private AddressRepository adres;
    @Autowired
    Login sessions;
    @Autowired
    private UserRepository users;
    @Autowired
    private ClientRepository clients;

    @PostMapping("/api/profile")
    String editAddres(@RequestBody Address address){
        Client u = sessions.getClient();
        if(u==null)return "{\"ok\":false}";
        address.setId(0L);
        address = adres.save(address);
        u.setAddress(address);
        clients.save(u);
        return "{\"ok\":true}";
    }
    @GetMapping("/api/profile")
    Address getAddres(){
        Client u = sessions.getClient();
        if(u==null)throw new RuntimeException();
        return u.getAddress();
    }
}
