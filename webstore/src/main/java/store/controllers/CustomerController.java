package store.controllers;

import org.springframework.web.bind.annotation.*;
import store.dto.CustomerDTO;
import store.dto.CustomerDTOToCreateCustomer;
import store.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import store.service.CustomerService;


@Controller("/admin")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "*/customers",method = RequestMethod.GET)
    public String getAllCustomers(Model model){
        model.addAttribute("customer", this.customerService.getAll());
        return "customerspage";
    }

    @RequestMapping(value = "*/customers/add",method = RequestMethod.GET)
    public String getAddCustomerPage(Model model){
        model.addAttribute("customer", new CustomerDTO());
        return "addpage";
    }

    @PostMapping(value = "*/customers/add")
    public String addCustomer(@ModelAttribute("customer") CustomerDTO customerDTO){
        this.customerService.add(CustomerDTOToCreateCustomer.create(customerDTO));
        return "redirect:/admin/customers";
    }

    @RequestMapping(value = "*/customers/edit/{customerID}", method = RequestMethod.GET)
    public String getEditCustomerPage(Model model, @PathVariable Integer customerID){
        Customer customer = customerService.getOne(customerID);
        model.addAttribute("editcustomer", customer);
        return "editpage";
    }

    @PostMapping(value = "*/customers/edit")
    public String editCustomer(@ModelAttribute("editcustomer") @RequestParam Customer customer){
        customerService.update(customer);
        return "redirect:/admin/customers";
    }

    @PostMapping(value = "*/customers/delete/")
    public String deleteCustomer(@ModelAttribute("customer") CustomerDTO customerDTO){
        //customerService.update(customerDTO);
        return "editpage";
    }
}
