package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import store.entity.User;
import store.service.UserService;
import store.util.CustomerValidator;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes({"updateUser", "page"})
public class CustomerController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerValidator customerValidator;

    @RequestMapping("/admin/customers")
    public String getCustomers(@PageableDefault(size = 12) Pageable pageable,
                               Model model){
        model.addAttribute("page", userService.getAllUsers(pageable));
        model.addAttribute("url","/admin/customers");
        return "/customer/customers";
    }

    @RequestMapping(value = "/admin/customers/add", method = RequestMethod.POST)
    public String addCustomer(@PageableDefault(size = 12) Pageable pageable,
                              @Valid User user, BindingResult result, Model model){
        model.addAttribute("page", userService.getAllUsers(pageable));
        model.addAttribute("url","/admin/customers");
        user.setPassword2(user.getPassword());
        customerValidator.validate(user, result);
        if (result.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "/customer/customers";
        }
        userService.add(user);
        return "redirect:/admin/customers";
    }

    @RequestMapping("/admin/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/admin/customers";
    }

    @RequestMapping(value = "/admin/customers/update/{id}")
    public String getUpdateCustomer(@PathVariable Integer id, Model model ){
        User user = userService.getOneById(id);
        model.addAttribute("updateUser", user);
        return "/customer/updateCustomer";
    }

    @RequestMapping(value = "/admin/customers/update", method = RequestMethod.POST)
    public String updateCustomer(@Valid @ModelAttribute("updateUser") User user,
                                 BindingResult result, Model model, SessionStatus sessionStatus){
        if (result.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            model.addAttribute("updateUser", user);
            return "/customer/updateCustomer";
        }
        userService.update(user);
        model.addAttribute("success", 200);
        sessionStatus.setComplete();
        return "redirect:/admin/customers";
    }
}
