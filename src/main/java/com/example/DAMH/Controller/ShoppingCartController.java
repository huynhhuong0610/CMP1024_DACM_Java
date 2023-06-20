package com.example.DAMH.Controller;

import com.example.DAMH.Model.Cart;
import com.example.DAMH.Model.User;
import com.example.DAMH.Service.CartService;
import com.example.DAMH.Service.OrderService;
import com.example.DAMH.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;


    @GetMapping("/cart")
    public String showCart(Model model, Long id){
        User user = userService.getActiveUserId();
        List<Cart> listcart = cartService.lstCart(user);
        model.addAttribute("carts", listcart);
        model.addAttribute("pageTitle", "Shopping Cart");
        model.addAttribute("Total", cartService.Total(user));
        return "cart/cart";
    }

    @PostMapping("/addToCart")
    public String addProductToCart(
            @RequestParam("id") Long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") Integer quantity,
            Model model,
            HttpServletRequest request){

        User user = userService.getActiveUserId();

        Cart cart = cartService.addProduct(productId, quantity,user );



        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/updateCart")
    public String updateCart(@RequestParam("quantity")Integer quantity,
                             @RequestParam("id") Long productId,
                             Model model){

        User user = userService.getActiveUserId();

        cartService.updateCart(productId,quantity,user);

        List<Cart> cart = cartService.lstCart(user);

        model.addAttribute("shoppingToCart", cart);
        return "redirect:/cart";
    }

    @GetMapping( "/deleteCart/{id}")
    public String deletecart(@PathVariable("id") Long productId,
                             Model model){
        User user = userService.getActiveUserId();

        cartService.deleteCartByProductAndUser(productId ,user);

        List<Cart> cart = cartService.lstCart(user);

        model.addAttribute("shoppingToCart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String pageCheckout(Model model){
        User user = userService.getActiveUserId();
        if(user.getPhonenumber().trim().isEmpty() || user.getAddress().trim().isEmpty()
                || user.getUsername().trim().isEmpty() || user.getEmail().trim().isEmpty()){
            model.addAttribute("user",user);
            model.addAttribute("errorInformation", "Bạn phải nhập đầy đủ thông tin");
            return "account";
        }
        else {
            model.addAttribute("user", user);
            List<Cart> cart = cartService.lstCart(user);
            model.addAttribute("cart",cart);
            model.addAttribute("Total", cartService.Total(user));
        }
        return "cart/checkout";
    }

    @GetMapping("/save_order")
    public String saveOrder(){
        User user = userService.getActiveUserId();
        List<Cart> carts = cartService.lstCart(user);
        orderService.saveOrder(carts);
        return "cart/thongbao";
    }
}
