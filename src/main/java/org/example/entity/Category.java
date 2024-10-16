package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Category {
    private int id;
    private Store store;
    private String categoryName;
    private String description;
    private String unity;

    public Category(Store store, String categoryName, String description, String unity) {
        this.store = store;
        this.categoryName = categoryName;
        this.description = description;
        this.unity = unity;
    }
}
