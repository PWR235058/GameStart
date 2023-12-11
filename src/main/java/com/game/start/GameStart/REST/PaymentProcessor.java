package com.game.start.GameStart.REST;

import com.game.start.GameStart.DTO.ProductDTO;
import com.game.start.GameStart.DTO.ProductList;
import com.game.start.GameStart.entity.Transaction;
import com.game.start.GameStart.entity.TransactionAction;
import com.game.start.GameStart.entity.User;
import com.game.start.GameStart.jpa.ProductRepository;
import com.game.start.GameStart.jpa.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PaymentProcessor {
    @Autowired
    ProductRepository produkty;
    @Autowired
    TransactionRepository tranzakcje;
    @Autowired
    Login sessions;

    @Transactional
    @PostMapping("/api/buy")
    public ProductList buy(@RequestBody ProductList toBuy){
        User user = sessions.getUser(null);
        ProductList bought=new ProductList(new ArrayList<>());
        if(user==null || user.getClient()==null)return bought;
        for (var x:toBuy.getProductList()) {
            var prod = produkty.findById(x.getId());
            if(prod.isPresent()){
                var product = prod.get();
                if(product.getStock()>0){
                    product.setStock(product.getStock()-1);
                    bought.getProductList().add(new ProductDTO(product));
                    Transaction transaction = new Transaction(0L,user.getClient(),product, TransactionAction.BUY,product.getPrice());
                    tranzakcje.save(transaction);
                }
            }
        }
        return bought;
    }
}
