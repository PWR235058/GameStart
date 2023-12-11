package com.game.start.GameStart.REST;

import com.game.start.GameStart.DTO.ProductList;
import com.game.start.GameStart.entity.Client;
import com.game.start.GameStart.entity.Seller;
import com.game.start.GameStart.entity.User;
import com.game.start.GameStart.jpa.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Transactions {
    @Autowired
    TransactionRepository tranzakcje;
    @Autowired
    Login sessions;

    @GetMapping("/api/transactions")
    ProductList getHistory(){
        ProductList p = new ProductList(new ArrayList<>());
        Client c = sessions.getClient();
        if(c!=null) {
            var t = tranzakcje.findByClient(c);
            p.add(t, true);
        }
        Seller seller = sessions.getSeller();
        if(seller!=null){
            var t = tranzakcje.findAllByProduct_Seller(seller);
            p.add(t, false);
        }
        return p;
    }
}
