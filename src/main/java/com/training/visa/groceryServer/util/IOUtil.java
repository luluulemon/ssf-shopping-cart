package com.training.visa.groceryServer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.visa.groceryServer.Model.GroceryItem;
import com.training.visa.groceryServer.Model.ShoppingCart;


public class IOUtil {
    
    private static Logger logger = LoggerFactory.getLogger(IOUtil.class); 


    public static void CreateDir(String dir){

        File directory = new File(dir);
        directory.mkdir();
    }

    public static ShoppingCart GetList(String username){
        String filepath = "data/" + username + ".db";
        Path path = Paths.get(filepath);

        List<String> CurrCart = new LinkedList<>();

        if (Files.exists(path))
        {   // retrieve existing Cart
            logger.info("Logger inside GetList: user exists");
            try
            (   Reader reader = new FileReader(filepath);
                BufferedReader br = new BufferedReader(reader); )
            {   String line;
                while(null!= (line=br.readLine())  )
                {   CurrCart.add(line);     }   
            }catch(Exception e)
            {   System.err.println((e)); }
        }
        else
        {   logger.info("user does not exists");    
            
            // create empty file for new user
            try {   Files.createFile(path); }
            catch(IOException e)
            {   System.err.println(e);      }
        }

            ShoppingCart UserCart = new ShoppingCart(username);
            UserCart.LoadCart(CurrCart);

            return UserCart;
 
    }


    public static ShoppingCart GetList2(String username){
        String filepath = "data/" + username + ".db";
        Path path = Paths.get(filepath);

        List<String> CurrCart = new LinkedList<>();
        GroceryItem loadItem = new GroceryItem();
        List<GroceryItem> ItemCart = new LinkedList<>();

        if (Files.exists(path))
        {   // retrieve existing Cart
            logger.info("user exists");
            try
            (   Reader reader = new FileReader(filepath);
                BufferedReader br = new BufferedReader(reader); )
            {   String line;
                while(null!= (line=br.readLine())  )
                {   CurrCart.add(line);     
                    loadItem.setName(line.split(",")[1]);
                    loadItem.setQuantity(Integer.parseInt(line.split(",")[0]));
                    ItemCart.add(loadItem);
                }   
            }catch(Exception e)
            {   System.err.println((e)); }
        }
        else
        {   logger.info("user does not exists");    
            
            // create empty file for new user
            try {   Files.createFile(path); }
            catch(IOException e)
            {   System.err.println(e);      }
        }

            ShoppingCart UserCart = new ShoppingCart(username);
            UserCart.LoadCart(CurrCart);
            UserCart.setItemCart(ItemCart);
            UserCart.setUsername(username);

            return UserCart;
 
    }

    public static ShoppingCart SaveList(ShoppingCart shoppingcart){
        
        String Additem = shoppingcart.getGroceryItem();
        List<String> CurrCart = shoppingcart.getCart();
        
        // check if item exists
        boolean existingItem = false;
        for (int i=1; i<CurrCart.size(); i++)
        {   if(CurrCart.get(i).split(",")[1].equalsIgnoreCase(Additem))
            {   
                // quantity + 1 if existing 
                int Quantity = Integer.parseInt(CurrCart.get(i).split(",")[0])  ;
                String item = CurrCart.get(i).split(",")[1];
                CurrCart.set(i, Integer.toString(Quantity+1) + "," + item );

                existingItem = true;
            }
        }
        if(!existingItem)
        {   CurrCart.add("1,"+Additem); }

        // load new Cart after adding item
        shoppingcart.LoadCart(CurrCart);


        // write into db file
        Path path = Paths.get("data/" + shoppingcart.getUsername()+".db");
  
  
        try{
        Files.write(path, CurrCart);  
        logger.info(shoppingcart.getCart().get(0));     // logging only 
        }
        catch(IOException e)
        {   System.err.println(e);  }

        return shoppingcart;
    }




    public static ShoppingCart DeleteItem(ShoppingCart shoppingcart, String item){
        
        List<String> CurrCart = shoppingcart.getCart();
        logger.info("item to delete is " + item);

        for(int i=0; i<CurrCart.size(); i++){
            if(CurrCart.get(i).equals(item)){
                CurrCart.remove(i);     // delete item
                break;
            }
        }

        shoppingcart.LoadCart(CurrCart);    // update shoppingcart after deleting

        // write into db file
        Path path = Paths.get("data/" + shoppingcart.getUsername()+".db");
  
        try
        {   Files.write(path, CurrCart);  }            
        catch(IOException e)
        {   System.err.println(e);  }

        return shoppingcart;
    
    }


}
