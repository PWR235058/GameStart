package com.game.start.GameStart.DTO;

import com.game.start.GameStart.entity.Product;
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
}
