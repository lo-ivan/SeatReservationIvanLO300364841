package com.example.seatreservation.web;

import com.example.seatreservation.entities.Customer;
import com.example.seatreservation.repositories.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes({"a", "e"})
@Controller
@AllArgsConstructor

public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    static int num = 0;

    @GetMapping(path = "/index")
    public String customers(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        List<Customer> customers;

        if (keyword.isEmpty()) {

            customers = customerRepository.findAll();

        } else {

            customers = customerRepository.findCustomersByName(keyword);

        }

        model.addAttribute("listCustomers", customers);

        return "customers";
    }

    @GetMapping("/delete")
    public String delete(Long id) {

        customerRepository.deleteById(id);

        return "redirect:/index";
    }

    @GetMapping("/formCustomers")
    public String formCustomers(Model model) {

        model.addAttribute("customer", new Customer());

        return "formCustomers";
    }

    @PostMapping(path="/save")
    public String save(Model model, Customer customer, BindingResult bindingResult, ModelMap mm, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "formCustomers";
        } else {
            customerRepository.save(customer);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }

            return "redirect:index";
        }
    }

    @GetMapping("/editCustomers")
    public String editCustomers(Model model, Long id, HttpSession session) {

        num = 2;

        session.setAttribute("info", 0);

        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) throw new RuntimeException("Customer does not exist");
        model.addAttribute("customer", customer);

        return "editCustomers";
    }

    @GetMapping(path = "/")
    public String customers2(Model model, ModelMap mm, @RequestParam(name = "keyword", defaultValue = "") String keyword, HttpSession session) {

        List<Customer> customers;

        if (keyword.isEmpty()) {
            customers = customerRepository.findAll();
        } else {
            mm.put("e", 0);
            mm.put("a", 0);
            customers = customerRepository.findCustomersByName(keyword);
        }
        model.addAttribute("listCustomers", customers);

        return "customers";
    }
}