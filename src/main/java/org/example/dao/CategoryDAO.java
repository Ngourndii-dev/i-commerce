package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.entity.Category;
import org.example.entity.Store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CategoryDAO {
    private final Connection connection;
    public List<Category> findAll(){
     List<Category> categoryList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from category");
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                categoryList.add(new Category(
                                result.getInt("id_category"),
                                result.getObject("store", Store.class),
                                result.getString("category_name"),
                                result.getString("description"),
                                result.getString("unity")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
     return categoryList;
    }
    public List<Category> searchByCategoryOrDescription(String category){
        List<Category> categoryList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from category where category='%s' or description i like '%s'",category);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                categoryList.add(new Category(
                                result.getInt("id_category"),
                                result.getObject("store", Store.class),
                                result.getString("category_name"),
                                result.getString("description"),
                                result.getString("unity")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return categoryList;
    }
    public List<Category> searchByStore(int id){
        List<Category> categoryList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from category where store='%s'",id);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                categoryList.add(new Category(
                                result.getInt("id_category"),
                                result.getObject("store", Store.class),
                                result.getString("category_name"),
                                result.getString("description"),
                                result.getString("unity")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return categoryList;
    }
    public List<Category> searchByUnity(String unity){
        List<Category> categoryList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from category where unity i like '%s'",unity);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                categoryList.add(new Category(
                                result.getInt("id_category"),
                                result.getObject("store", Store.class),
                                result.getString("category_name"),
                                result.getString("description"),
                                result.getString("unity")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return categoryList;
    }

}
