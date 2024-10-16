package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Product {
    private int id;
    private Category category;
    private double price;
    private String sizes;
    private String color;

    public Product(Category category, double price, String sizes,String color) {
        this.category = category;
        this.price = price;
        this.sizes = sizes;
        this.color=color;
    }
}
