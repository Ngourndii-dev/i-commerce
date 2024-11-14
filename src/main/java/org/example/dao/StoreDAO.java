package org.example.dao;

import lombok.AllArgsConstructor;
import org.example.entity.Store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class StoreDAO {
    private final Connection connection;
    public List<Store> findAll(){
        List<Store> storeList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from store");
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                storeList.add(new Store(
                                result.getInt("id_store"),
                                result.getString("title"),
                                result.getString("location"),
                                result.getString("pub")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return storeList;
    }
    public List<Store> searchByTitleOrLocation(String title){
        List<Store> storeList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * FROM store WHERE title ILIKE '%s' OR location ILIKE '%s'",title);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                storeList.add(new Store(
                                result.getInt("id_store"),
                                result.getString("title"),
                                result.getString("location"),
                                result.getString("pub")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return storeList;
    }
}
