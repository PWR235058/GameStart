package com.game.start.GameStart.REST;

import com.game.start.GameStart.DTO.ProductList;
import com.game.start.GameStart.entity.Product;
import com.game.start.GameStart.entity.User;
import com.game.start.GameStart.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProductsController {

    @Autowired
    ProductRepository produkty;
    @Autowired
    Login sessions;
    @PostMapping("/add")
    void addnew(@RequestBody Product p){
        User user = sessions.getUser(null);
        if(user==null || user.getClient()==null)return;
        p.setId(0L);
        p.setSeller(null);
        p.setDataCarrier(null);
        p.setProductType(null);
        produkty.save(p);
    }
}
