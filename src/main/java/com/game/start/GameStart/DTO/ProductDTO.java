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
    String image;
    public ProductDTO(Product product){
        id = product.getId();
        title = product.getProductType().getTitle();
        price = product.getPrice();
        seller = product.getSeller().getName();
        stock = product.getStock();
        image = product.getImageUrl();
    }
    public ProductDTO(Transaction transaction, boolean inv){
        this(transaction.getProduct());
        price = transaction.getKoszt()*(inv?-1:1);
    }
}
