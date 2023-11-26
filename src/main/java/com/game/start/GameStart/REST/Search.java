package com.game.start.GameStart.REST;

import com.game.start.GameStart.entity.Product;
import com.game.start.GameStart.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.PropertiesBasedNamedQueries;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    void addnew(@RequestBody Product p){
        p.setId(0L);
        p.setShop(null);
        p.setDataCarrier(null);
        p.setProductType(null);
        produkty.save(p);
    }
}
