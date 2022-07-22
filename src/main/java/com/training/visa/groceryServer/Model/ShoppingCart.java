package com.training.visa.groceryServer.Model;

import java.util.List;

public class ShoppingCart {
    private int NumItems;
    private List<String> Cart;
    private String Username;      // final -> need to set during constructor
    
    public void setUsername(String username) {
        Username = username;
    }

    private String GroceryItem;
    private List<GroceryItem> ItemCart;
    

    public List<GroceryItem> getItemCart() {
        return ItemCart;
    }

    public void setItemCart(List<GroceryItem> itemCart) {
        ItemCart = itemCart;
    }

    public ShoppingCart(){}     // default constructor

    public ShoppingCart(String username)    // constructor only set username
    {   Username = username;    }
    
    public String getUsername() 
    {   return Username;    }

    public String getGroceryItem() 
    {   return GroceryItem; }

    public void setGroceryItem(String groceryItem) 
    {   GroceryItem = groceryItem;  }

    public int getNumItems() 
    {   return NumItems;    }

    public void setNumItems(int numItems) 
    {   NumItems = numItems;    }

    public List<String> getCart() 
    {   return Cart;        }

    public void LoadCart(List<String> CurrCart) 
    {   Cart = CurrCart;   }
    
}
