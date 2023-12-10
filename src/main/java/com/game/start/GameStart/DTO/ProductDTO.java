package com.game.start.GameStart.DTO;

import com.game.start.GameStart.entity.Product;
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
}