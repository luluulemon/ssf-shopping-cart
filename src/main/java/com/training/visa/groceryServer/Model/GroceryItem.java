package com.training.visa.groceryServer.Model;

//import java.util.Random;

public class GroceryItem {
    
    private int Quantity;
    private String ID;
    private String Name;
    private double Price;




    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() 
    {   return Quantity;    }

    public void setQuantity(int quantity) 
    {   Quantity = quantity;    }

    public String getID() 
    {   return ID;      }

    public void setID(String iD) 
    {   ID = iD;    }


}
