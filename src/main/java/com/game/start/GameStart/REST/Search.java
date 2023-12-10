package com.game.start.GameStart.REST;

import com.game.start.GameStart.DTO.ProductDTO;
import com.game.start.GameStart.DTO.ProductList;
import com.game.start.GameStart.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Search {

    @Autowired
    ProductRepository produkty;

    @GetMapping("/products/all")
    ProductList findAll(){
        return new ProductList(produkty.findAll());
    }
}
