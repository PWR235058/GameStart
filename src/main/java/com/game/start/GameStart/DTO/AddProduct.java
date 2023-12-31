package com.game.start.GameStart.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProduct {
    String title;
    float price;
    int stock;
    int carrier;
    String link;
}
