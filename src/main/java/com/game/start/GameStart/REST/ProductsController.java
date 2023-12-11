package com.game.start.GameStart.REST;

import com.game.start.GameStart.DTO.AddProduct;
import com.game.start.GameStart.DTO.ProductList;
import com.game.start.GameStart.entity.*;
import com.game.start.GameStart.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ProductsController {
    @Autowired
    ProductRepository produkty;
    @Autowired
    ShopRepository sklepy;
    @Autowired
    SellerRepository sprzedawcy;
    @Autowired
    DataCarrierRepository dataCarrierRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Autowired
    Login sessions;



    @PostMapping("/api/products")
    String addnew(@RequestBody AddProduct p){
        Seller seller = sessions.getSeller();
        if(seller==null)return "{\"ok\":false}";

        DataCarrier dataCarrier = dataCarrierRepository.findById((long) p.getCarrier()).get();
        ProductType productType = productTypeRepository.save(new ProductType(0L,p.getTitle(),"Brak opisu."));

        String image=ImageController.downloadAndSaveImage(p.getLink());

        Product product = new Product(0L, dataCarrier, seller, productType,p.getStock(), p.getPrice(),image);

        produkty.save(product);
        return "{\"ok\":true}";
    }
    @PutMapping("/api/products")
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
