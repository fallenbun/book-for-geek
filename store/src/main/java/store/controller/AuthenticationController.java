package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import store.entity.User;
import store.service.UserService;
import store.util.CustomerValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes({"countries"})
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerValidator customerValidator;

    @RequestMapping(value = "/sign_in")
    public String getSignIn(@RequestParam(value = "error", required = false) String error,
                            Model model) {

        Map<String, String> countries = new HashMap<>();

        countries.put("BY", "Белоруссия");
        countries.put("KZ", "Казахстан");
        countries.put("RU", "Россия");
        countries.put("UA", "Украина");

        model.addAttribute("countries", countries);

        if (error != null)
            model.addAttribute("error", error);
        return "/customer/sign_in";
    }


    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUp(@Valid User user, BindingResult result, Model model) {
        customerValidator.validate(user, result);
        if (result.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(result);
            model.mergeAttributes(errors);
            return "/customer/sign_in";
        }
        userService.add(user);
        return "redirect:/sign_in";
    }
}
