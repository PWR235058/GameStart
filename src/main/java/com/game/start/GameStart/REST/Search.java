package com.game.start.GameStart.REST;

import com.game.start.GameStart.entity.Product;
import com.game.start.GameStart.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Search {

    @Autowired
    ProductRepository produkty;

    @GetMapping("/findall")
    List<Product> findAll(){
        return produkty.findAll();
    }
    @PostMapping("/add")
    void addnew(){

    }
}
