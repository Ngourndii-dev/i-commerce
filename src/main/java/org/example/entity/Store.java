package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Store {
    private int id;
    private String title;
    private String location;
    private String pub;

    public Store(String title, String location, String pub) {
        this.title = title;
        this.location = location;
        this.pub = pub;
    }
}
