package com.game.start.GameStart.DTO;

import com.game.start.GameStart.entity.Product;
import com.game.start.GameStart.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductList {
    List<ProductDTO> productList;
    public ProductList(List<Product> products){
        productList = new ArrayList<>();
        for (var x:products) {
            productList.add(new ProductDTO(x));
        }
    }
    public void add(List<Transaction> transactions,boolean inv){
        for (var x:transactions) {
            productList.add(new ProductDTO(x,inv));
        }
    }
}
