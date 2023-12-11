package com.game.start.GameStart.DTO;

import com.game.start.GameStart.entity.Product;
import com.game.start.GameStart.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    Long id;
    String title;
    float price;
    String seller;
    int stock;
    public ProductDTO(Product product){
        id = product.getId();
        title = product.getSeller().getName();
        price = product.getPrice();
        seller = product.getSeller().getName();
        stock = product.getStock();
    }
    public ProductDTO(Transaction transaction, boolean inv){
        id = transaction.getProduct().getId();
        title = transaction.getProduct().getSeller().getName();
        price = transaction.getKoszt()*(inv?-1:1);
        seller = transaction.getProduct().getSeller().getName();
        stock = transaction.getProduct().getStock();
    }
}
