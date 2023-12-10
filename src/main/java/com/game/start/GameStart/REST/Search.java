package com.game.start.GameStart.REST;

import com.game.start.GameStart.DTO.ProductList;
import com.game.start.GameStart.entity.DataCarrier;
import com.game.start.GameStart.jpa.DataCarrierRepository;
import com.game.start.GameStart.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Search {

    @Autowired
    ProductRepository produkty;

    @Autowired
    DataCarrierRepository nosniki;
    @GetMapping("/carrier")
    List<DataCarrier> getCarriers(){
        return nosniki.findAll();
    }
    @GetMapping("/products")
    ProductList findAll(){
        return new ProductList(produkty.findAll());
    }
    @GetMapping("/products/{name}")
    ProductList findName(@PathVariable String name){
        ProductList productList = new ProductList(produkty.findAll());
        ProductList productr = new ProductList(new ArrayList<>());
        for (var p:productList.getProductList()) {
            if(p.getTitle().contains(name)){//todo replace with fuzzy search
                productr.getProductList().add(p);
            }
        }
        return productList;
    }
}
