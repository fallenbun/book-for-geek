package store.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import store.entity.Order;
import store.entity.User;
import store.entity.enums.OrderStatus;
import store.service.OrderService;

import java.util.HashMap;
import java.util.Map;


@Controller
@SessionAttributes("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/admin/orders/delete")
    public String deleteCustomerOrder(@RequestParam("orderId") Integer orderId){
        orderService.delete(orderId);
        return "redirect:/admin/orders";
    }

    @RequestMapping("/admin/orders/customer/{customerId}")
    public String getCustomerOrder(@PageableDefault(size = 8) Pageable pageable,
                                   @PathVariable Integer customerId,
                                   Model model){
        model.addAttribute("page", orderService.getCustomerOrders(customerId, pageable));
        model.addAttribute("url","/admin/orders/customer/" + customerId);
        return "customer/orders";
    }

    @RequestMapping("/admin/orders")
    public String getCustomerOrder(@PageableDefault(size = 8) Pageable pageable,
                                   Model model){
        model.addAttribute("page", orderService.getAllOrders(pageable));
        model.addAttribute("url","/admin/orders");
        return "customer/orders";
    }

    @RequestMapping("/admin/orders/update")
    public String getEditOrder(@RequestParam(value = "orderId", required = false) Integer orderId,
                               Model model){
        Map<OrderStatus,String> status = new HashMap<>();
        for (OrderStatus os : OrderStatus.values()){
            status.put(os, os.getTitle());
        }
        model.addAttribute("status", status);
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "/customer/orderDetails";
    }

    @RequestMapping(value = "/admin/orders/edit", method = RequestMethod.POST)
    public String editOrder(@ModelAttribute("order") Order order,
                            SessionStatus sessionStatus){
        orderService.update(order);
        sessionStatus.setComplete();
        return "redirect:/admin/orders";
    }

    @RequestMapping("/orders/order")
    public String getOrderDetails(@AuthenticationPrincipal User user,
                                  @RequestParam("orderId") Integer orderId,
                                  Model model){
        model.addAttribute("order", orderService.getCustomerOrder(user.getUserId(), orderId));
        return "/customer/orderDetails";
    }

    @RequestMapping("/orders")
    public String getCustomerOrder(@AuthenticationPrincipal User user,
                                   @PageableDefault(size = 8) Pageable pageable,
                                   Model model){
        model.addAttribute("user", user);
        model.addAttribute("page", orderService.getCustomerOrders(user.getUserId(), pageable));
        model.addAttribute("url","/orders");
        return "customer/orders";
    }

    @RequestMapping("/orders/delete")
    public String deleteCustomerOrder(@AuthenticationPrincipal User user,
                                      @RequestParam("orderId") Integer orderId){
        if (orderService.getCustomerOrder(user.getUserId(), orderId) != null) orderService.delete(orderId);
        return "redirect:/orders";
    }
}
