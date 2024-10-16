package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.configuration.DbConnection;
import org.example.entity.Category;
import org.example.entity.Client;
import org.example.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
public class ClientDAO {
   private Connection connection;

    public Client save(Client client){
        String result;
        String sql="insert into client(full_name,username,password) values (?,?,?)";
        try (PreparedStatement statement= connection.prepareStatement(sql)){
            statement.setString(1,client.getFullName());
            statement.setString(2, client.getUsername());
            statement.setString(3, client.getPassword());
            statement.executeQuery();
            System.out.println("Account created");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return client;
    }
    public Client verificationLogin(String username , String password){
        List<Client> clientList=new ArrayList<>();
        Statement statement;
        ResultSet result=null;
        try{
            String query=String.format("SELECT * from client where username='%s' and password='%s'",username,password);
            statement=connection.createStatement();
            result=statement.executeQuery(query);
            while(result.next()){
                clientList.add(new Client(
                                result.getInt("id_client"),
                                result.getString("full_name"),
                                result.getString("username"),
                                result.getString("password")
                        )
                );
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        if(clientList.size()==0){
            return null;
        }else{
            return clientList.get(0);
        }
    }
    public void deleteById(int id){
        Statement statement;
        try{
            String query=String.format("delete from client where id_client='%s'",id);
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Account deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws SQLException {
        Connection connection1=new DbConnection().getConnection();
        ClientDAO clientDAO=new ClientDAO(connection1);
        clientDAO.deleteById(1);
    }
}
