package com.game.start.GameStart.REST;

import com.game.start.GameStart.DTO.ProductList;
import com.game.start.GameStart.entity.*;
import com.game.start.GameStart.jpa.ProductRepository;
import com.game.start.GameStart.jpa.SellerRepository;
import com.game.start.GameStart.jpa.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
public class ProductsController {
    @Autowired
    ProductRepository produkty;
    @Autowired
    ShopRepository sklepy;
    @Autowired
    SellerRepository sprzedawcy;
    @Autowired
    Login sessions;



    @PostMapping("/products")
    void addnew(@RequestBody Product p){
        Seller seller = sessions.getSeller();
        if(seller==null)return;

        Product product = new Product(0L, (DataCarrier) null,seller, (ProductType) null,0, 0.0F);

        produkty.save(product);
    }
    @PutMapping("/products")
    void modify(@RequestBody Product p){
        Seller seller = sessions.getSeller();
        if(seller==null)return;
        Product product = produkty.findById(p.getId()).get();
        if(!Objects.equals(product.getSeller().getId(), seller.getId()))
            return;
        product.setStock(p.getStock());
        product.setPrice(p.getPrice());
        produkty.save(product);
    }
}
