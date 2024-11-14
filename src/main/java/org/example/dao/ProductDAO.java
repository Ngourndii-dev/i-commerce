package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.entity.Category;
import org.example.entity.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductDAO {
    private final Connection connection;
    public List<Product> findAll(){
        List<Product> productList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from product");
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                productList.add(new Product(
                                result.getInt("id_product"),
                                result.getObject("category", Category.class),
                                result.getDouble("price"),
                                result.getString("sizes"),
                                result.getString("color")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }
    public List<Product> searchByCategory(int id){
        List<Product> productList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from product where category='%s",id);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                productList.add(new Product(
                                result.getInt("id_product"),
                                result.getObject("category", Category.class),
                                result.getDouble("price"),
                                result.getString("sizes"),
                                result.getString("color")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }
    public List<Product> searchByPrice(double price){
        List<Product> productList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM product WHERE price BETWEEN '%s' AND '%s'",price);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                productList.add(new Product(
                                result.getInt("id_product"),
                                result.getObject("category", Category.class),
                                result.getDouble("price"),
                                result.getString("sizes"),
                                result.getString("color")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }
    public List<Product> searchBySizes(String sizes){
        List<Product> productList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM product WHERE sizes ILIKE '%s'",sizes);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                productList.add(new Product(
                                result.getInt("id_product"),
                                result.getObject("category", Category.class),
                                result.getDouble("price"),
                                result.getString("sizes"),
                                result.getString("color")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }
    public List<Product> searchByIntervalPrice(double start,double end){
        List<Product> productList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from product where price between '%s' and '%s'",start,end);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                productList.add(new Product(
                                result.getInt("id_product"),
                                result.getObject("category", Category.class),
                                result.getDouble("price"),
                                result.getString("sizes"),
                                result.getString("color")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return productList;
    }
}
