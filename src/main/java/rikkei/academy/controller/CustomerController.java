package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Customer;
import rikkei.academy.service.ICustomerService;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @GetMapping("/")
    public String showListCustomer(Model model) {
        model.addAttribute("listCustomer",customerService.findAll());
        return "list";
    }
    @GetMapping("/show/create")
    public String showFormCreate(Model model){
        model.addAttribute("formCreate", new Customer());
        return "create";
    }
    @PostMapping("/action/create")
    public String createCustomer(@ModelAttribute("formCreate") Customer customer,Model model){
        customerService.save(customer);
        model.addAttribute("message", "Create customer success!");
        return "create";
    }
    @GetMapping("/detail/{id}")
    public String showFormForAdd(@PathVariable("id")Long id,Model model) {
        System.out.println("id===="+id);
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/detail";
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("delete", customer);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteById(@ModelAttribute("deleteForm")Customer customer){
        customerService.deleteById(customer.getId());
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("edit",customer);
        return modelAndView;
    }
    @PostMapping("/edit")
    public String editCustomer(@ModelAttribute("edit")Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
}
