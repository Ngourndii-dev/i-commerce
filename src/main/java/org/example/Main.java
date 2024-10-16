package org.example;

import lombok.AllArgsConstructor;
import org.example.configuration.DbConnection;
import org.example.dao.CategoryDAO;
import org.example.dao.ClientDAO;
import org.example.dao.ProductDAO;
import org.example.dao.StoreDAO;
import org.example.entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
@AllArgsConstructor
public class Main{
    public static void  main(String[] args) throws SQLException {
        Connection connection1=new DbConnection().getConnection();
        ClientDAO clientDAO=new ClientDAO(connection1);
        StoreDAO storeDAO=new StoreDAO(connection1);
        CategoryDAO categoryDAO=new CategoryDAO(connection1);
        ProductDAO productDAO=new ProductDAO(connection1);
        System.out.println("Welcome to i-commerce ! ");
        System.out.println("----------*----------");

        Scanner scanner=new Scanner(System.in);

        System.out.println("1_Login , 2_Signup : ");
        int logOrSign=scanner.nextInt();
        if(logOrSign==1)
        {
            System.out.print("Your username : ");
            String username= scanner.next();
            System.out.println("");
            System.out.print("Your password : ");
            System.out.println("");
            String password= scanner.next();
            Client client=clientDAO.verificationLogin(username,password);
            if(client!=null)
            {
                System.out.println("---**---");
                System.out.println(username.toUpperCase()+" You are now connected ! "+client.getId()+" is your id ");
                System.out.println("---**---");
                System.out.println("Do you want search : " +
                        "\n 1_Gift" +
                        "\n 2_Our product" +
                        "\n 3_surf to the store" +
                        "\n 4_delete your account");
                int choiceAction=scanner.nextInt();
                if(choiceAction==1)
                {
                    System.out.println("We are here to help you to search Gift" +
                            "\n ****** Complete the information here to easy the process ******");

                    System.out.println("Would you like to search " +
                            "\n 1_one price" +
                            "\n 2_interval price" +
                            "\n 3_searchByCategory");
                    int choicePrice=scanner.nextInt();
                    if(choicePrice==1){
                        System.out.println("Type the price : ");
                        double price=scanner.nextDouble();
                        System.out.println("There are the gift correspond of your price : ");
                        System.out.println("--------**--------");
                        productDAO.searchByPrice(price);
                        System.out.println("--------**--------");
                    }
                    else if (choicePrice==2) {
                        System.out.println("Type the start price : ");
                        double priceStart= scanner.nextDouble();
                        System.out.println("Type the end price : ");
                        double priceEnd= scanner.nextDouble();
                        System.out.println("There are the gift correspond of your interval price : ");
                        System.out.println("--------**--------");
                        productDAO.searchByIntervalPrice(priceStart,priceEnd);
                        System.out.println("--------**--------");
                    }
                    else if (choicePrice==3) {
                        categoryDAO.findAll();
                        System.out.println("Get all category by : " +
                                "\n REMEMBER THE ID 's category" +
                                "\n 1_unity" +
                                "\n 2_store" +
                                "\n 3_description");
                        int choiceCategory=scanner.nextInt();
                        if(choiceCategory==1){
                            System.out.println("Type the unity : ");
                            String unity=scanner.next();
                            System.out.println("There are the category correspond of your unity : ");
                            System.out.println("--------**--------");
                            categoryDAO.searchByUnity(unity);
                            System.out.println("--------**--------");
                        }
                        else if (choiceCategory==2) {
                            System.out.println("Type the store you want explore (location or title): ");
                            String locationOrTitle = scanner.next();
                            System.out.println("There are the store correspond of your research (REMEMBER THE ID ' s store) : ");
                            System.out.println("--------**--------");
                            storeDAO.searchByTitleOrLocation(locationOrTitle);
                            System.out.println("--------**--------");
                            System.out.println("Type the id ' s Store : ");
                            int store=scanner.nextInt();
                            System.out.println("There are the category correspond of your store : ");
                            System.out.println("--------**--------");
                            categoryDAO.searchByStore(store);
                            System.out.println("--------**--------");
                        }
                        else if (choiceCategory==3) {
                            System.out.println("Type the description , you know : ");
                            String description=scanner.next();
                            System.out.println("There are the category ' s result of your research  : ");
                            System.out.println("--------**--------");
                            categoryDAO.searchByCategoryOrDescription(description);
                            System.out.println("--------**--------");
                        }
                        else {
                            System.out.println("Type 1 or 2 or 3");
                        }
                        System.out.println("Now you are now ready to choose your product ;" +
                                "\n Type the id's category you choose : ");
                        int id=scanner.nextInt();
                        System.out.println("There are the category perfect of your needed : ");
                        System.out.println("--------**--------");
                        productDAO.searchByCategory(id);
                        System.out.println("--------**--------");
                    }
                    else {
                        System.out.println("Type 1 or 2 or 3 for the choice ");
                    }
                }
                else if (choiceAction==2)
                {
                    System.out.println("Do you like to search by " +
                            "\n 1_SIZE" +
                            "\n 2_PRICE" +
                            "\n 3_CATEGORY");
                    int choiceProduct=scanner.nextInt();
                    if(choiceProduct==1){
                        System.out.println("Type the size : ");
                        String sizes=scanner.next();
                        System.out.println("There are the product's result of your size : ");
                        System.out.println("--------**--------");
                        productDAO.searchBySizes(sizes);
                        System.out.println("--------**--------");
                    }
                    else if (choiceProduct==2) {
                        System.out.println("" +
                                "\n 1_One price " +
                                "\n 2_Interval price");
                        int choicePrice=scanner.nextInt();
                        if(choicePrice==1){
                            System.out.println("Type the price : ");
                            double price=scanner.nextDouble();
                            System.out.println("There are the product correspond of your price : ");
                            System.out.println("--------**--------");
                            productDAO.searchByPrice(price);
                            System.out.println("--------**--------");
                        }
                        else if (choicePrice==2) {
                            System.out.println("Type the start price : ");
                            double priceStart= scanner.nextDouble();
                            System.out.println("Type the end price : ");
                            double priceEnd= scanner.nextDouble();
                            System.out.println("There are the product correspond of your interval price : ");
                            System.out.println("--------**--------");
                            productDAO.searchByIntervalPrice(priceStart,priceEnd);
                            System.out.println("--------**--------");
                        }
                        else{
                            System.out.println("Type 1 or 2 for the choice : ");
                        }
                    }
                    else if (choiceProduct==3)
                    {
                        categoryDAO.findAll();
                        System.out.println("Get all category by : " +
                                "\n REMEMBER THE ID 's category" +
                                "\n 1_unity" +
                                "\n 2_store" +
                                "\n 3_description");
                        int choiceCategory=scanner.nextInt();
                        if(choiceCategory==1){
                            System.out.println("Type the unity : ");
                            String unity=scanner.next();
                            System.out.println("There are the category correspond of your unity : ");
                            System.out.println("--------**--------");
                            categoryDAO.searchByUnity(unity);
                            System.out.println("--------**--------");
                        }
                        else if (choiceCategory==2) {
                            System.out.println("Type the store you want explore (location or title): ");
                            String locationOrTitle = scanner.next();
                            System.out.println("There are the store correspond of your research (REMEMBER THE ID ' s store) : ");
                            System.out.println("--------**--------");
                            storeDAO.searchByTitleOrLocation(locationOrTitle);
                            System.out.println("--------**--------");
                            System.out.println("Type the id ' s Store : ");
                            int store=scanner.nextInt();
                            System.out.println("There are the category correspond of your store : ");
                            System.out.println("--------**--------");
                            categoryDAO.searchByStore(store);
                            System.out.println("--------**--------");
                        }
                        else if (choiceCategory==3) {
                            System.out.println("Type the description , you know : ");
                            String description=scanner.next();
                            System.out.println("There are the category ' s result of your research  : ");
                            System.out.println("--------**--------");
                            categoryDAO.searchByCategoryOrDescription(description);
                            System.out.println("--------**--------");
                        }
                        else {
                            System.out.println("Type 1 or 2 or 3");
                        }
                        System.out.println("Now you are now ready to choose your product ;" +
                                "\n Type the id's category you choose : ");
                        int id=scanner.nextInt();
                        System.out.println("There are the category perfect of your needed : ");
                        System.out.println("--------**--------");
                        productDAO.searchByCategory(id);
                        System.out.println("--------**--------");
                    }
                    else {
                        System.out.println("Type 1 or 2 or 3 for the choice : ");
                    }
                }
                else if (choiceAction==3)
                {
                    System.out.println("There are your store : ");
                    System.out.println("--------**--------");
                    storeDAO.findAll();
                    System.out.println("--------**--------");
                    System.out.println("Type the store (title or location) for get details of your store : ");
                    String store=scanner.next();
                    System.out.println("There are the store correspond of your research : ");
                    System.out.println("--------**--------");
                    storeDAO.searchByTitleOrLocation(store);
                    System.out.println("--------**--------");
                }
                else if(choiceAction==4)
                {
                    System.out.println("Are you sure to delete your account : yes / no => ");
                    String result=scanner.next();
                    if(result.equals("yes")){
                        clientDAO.deleteById(client.getId());
                    }
                    else if (result.equals("no")) {
                        System.out.println("Ok ! bien ! ");
                    }
                    else {
                        System.out.println("Pas sur ! ");
                    }
                }
                else
                {
                    System.out.println("Type 1 or 2 or 3 or 4 for the choice ");
                }
            }
            else
            {
                System.out.println("---**---");
                System.out.println("Failed to login !!!");
                System.out.println("---**---");
            }
        }
        else if (logOrSign==2) {
            System.out.print("Your full name : ");
            String fullName=scanner.next();
            System.out.println("---**---");

            System.out.print("Your username : ");
            String username=scanner.next();
            System.out.println("---**---");

            System.out.print("Create your password : ");
            String password=scanner.next();
            System.out.println("---**---");

            Client toSave=new Client(fullName,username,password);
            clientDAO.save(toSave);
            System.out.println("---**---");
            System.out.println("You are ready to login !");
            System.out.println("---**---");
        }
        else{

            System.out.println("Type 1 or 2 for the choice !!! ");
        }
        scanner.close();
    }
}