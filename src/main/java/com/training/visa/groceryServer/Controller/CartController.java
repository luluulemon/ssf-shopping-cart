package com.training.visa.groceryServer.Controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.visa.groceryServer.Model.ShoppingCart;
import com.training.visa.groceryServer.util.IOUtil;

@Controller
@RequestMapping  //("/mycart")
public class CartController {
    
    private Logger logger = LoggerFactory.getLogger(CartController.class); 

    ShoppingCart CurrShoppingCart;
    String CurrUser;    // stores Current Session

    @RequestMapping("/")
    public String LogIn(Model model){       // gets Username
        //model.addAttribute("User", new GetUser());
        model.addAttribute("Shoppingcart", new ShoppingCart());
        return "shoppingCart";
    }

    @RequestMapping("/mycart")            // input get user object
    public String GetUrCart(@ModelAttribute ShoppingCart cart, Model model){
        
        CurrUser = cart.getUsername();  // store current user - everytime new Cart gets loaded
        logger.info(cart.getUsername());
        //CurrUser = username;        // store current user

        ShoppingCart shoppingcart = IOUtil.GetList(CurrUser);   // Instantiate new ShoppingCart obj, get existing list
        model.addAttribute("Shoppingcart", shoppingcart);

        logger.info(shoppingcart.getUsername());       
        
        return "shoppingCart";
    }


    @GetMapping("/mycartadd")
    public String UpdateCart(@ModelAttribute ShoppingCart shoppingcart, Model model){
        
        CurrShoppingCart = IOUtil.GetList(CurrUser);    // load up current user cart
        CurrShoppingCart.setGroceryItem(shoppingcart.getGroceryItem());

        shoppingcart = IOUtil.SaveList(CurrShoppingCart);
        model.addAttribute("Shoppingcart", shoppingcart);

        return "shoppingCart";
    }


    @RequestMapping("/mycartdelete/{item}")
    public String DeleteCart (@PathVariable("item") String item, @ModelAttribute ShoppingCart shoppingcart, Model model){

        logger.info("Logger inside Controller(Delete). item to delete is: " + item);
        //String username = CurrShoppingCart.getUsername();
        shoppingcart = IOUtil.GetList(CurrUser);   // load up current user cart

        IOUtil.DeleteItem(shoppingcart, item);
        model.addAttribute("Shoppingcart", shoppingcart);
        
        CurrShoppingCart = shoppingcart;

        return "shoppingCart";
    }


    @RequestMapping("/mycartedit/{item}")
    public String EditCart (@PathVariable("item") String item, Model model){

        logger.info("item to edit is: " + item);

        ShoppingCart shoppingcart = IOUtil.GetList(CurrUser);   // load up current user cart
        
        String CurrItem = item.split(",")[1];
        shoppingcart.setGroceryItem(CurrItem);

        model.addAttribute("Shoppingcart", shoppingcart);
        
        //CurrShoppingCart = shoppingcart;

        return "shoppingCart";
    }


    @RequestMapping("/mycartarrange/{item}")
    public String ArrangeCart (@PathVariable("item") String item, Model model){

        logger.info("item to edit is: " + item);

        ShoppingCart shoppingcart = IOUtil.GetList(CurrUser);   // load up current user cart
        
        String CurrItem = item.split(",")[1];
        shoppingcart.setGroceryItem(CurrItem);

        model.addAttribute("Shoppingcart", shoppingcart);
        
        //CurrShoppingCart = shoppingcart;

        return "shoppingCart";
    }

}
