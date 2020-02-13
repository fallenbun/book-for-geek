package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.dto.CustomerDTO;
import store.dto.CustomerDTOToCreateCustomer;
import store.service.CustomerService;

@Controller()
public class AuthenticationController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/signin")
    public String login() {
        return "signin";
    }

    @PostMapping(value = "/signin")
    public String login(@ModelAttribute Model model) {
        model.addAttribute("user", new CustomerDTO());
        return "welcome";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(@ModelAttribute Model model) {
        model.addAttribute("user", new CustomerDTO());
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String registerUser(@ModelAttribute("user") CustomerDTO customerDTO) {
        this.customerService.add(CustomerDTOToCreateCustomer.create(customerDTO));
        return "welcome";
    }

}