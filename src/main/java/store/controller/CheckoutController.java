package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import store.entity.Address;
import store.entity.Cart;
import store.entity.User;
import store.service.OrderService;
import store.util.SessionCart;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes({"user", "countries"})
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/checkout")
    public String getCheckout(@AuthenticationPrincipal User user,
                              HttpServletRequest request,
                              Model model) {
        if (SessionCart.getCartInSession(request).isEmpty()) {
            return "redirect:/accessDenied";
        }

        Map<String, String> countries = new HashMap<>();

        countries.put("BY", "Белоруссия");
        countries.put("KZ", "Казахстан");
        countries.put("RU", "Россия");
        countries.put("UA", "Украина");

        model.addAttribute("countries", countries);
        model.addAttribute("address", new Address());
        model.addAttribute("user", user);
        return "/main/checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkout(HttpServletRequest request,
                           @Valid Address address,
                           BindingResult resultAddress,
                           @Valid @ModelAttribute("user") User user,
                           BindingResult resultUser,
                           Model model) {
        if (address.getCountry().equals("none")){
            resultAddress.rejectValue("country", "","Данное поле должно быть заполнено!");
        }
        Cart cart = SessionCart.getCartInSession(request);
        if (resultUser.hasErrors() || resultAddress.hasErrors()) {
            Map<String, String> errors1 = ControllerUtils.getErrors(resultAddress);
            Map<String, String> errors2 = ControllerUtils.getErrors(resultUser);
            model.mergeAttributes(errors1);
            model.mergeAttributes(errors2);
            model.addAttribute("user", user);
            model.addAttribute("address", address);
            return "/main/checkout";
        }
        orderService.createOrder(user, cart, address);
        request.getSession().setAttribute("order", cart);
        request.getSession().removeAttribute("cart");
        return "redirect:/checkout/confirmation";
    }

    @RequestMapping("/checkout/confirmation")
    public String confirm(HttpServletRequest request,
                          Model model){
        Cart cart = (Cart) request.getSession().getAttribute("order");
        model.addAttribute("order", cart);
        request.getSession().removeAttribute("order");
        return "main/confirm";
    }
}
