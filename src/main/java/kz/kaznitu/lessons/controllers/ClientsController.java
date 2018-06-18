package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Client;
import kz.kaznitu.lessons.models.Product;
import kz.kaznitu.lessons.repositories.ClientRepository;
import kz.kaznitu.lessons.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ClientsController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/admin/client/{id}")
    public String client(@PathVariable("id")int id, Model model){
        model.addAttribute("client", clientRepository.findById(id).get());
        model.addAttribute("products", productRepository.findAll());
        return "client";
    }

    @RequestMapping(value = "/admin/clients",method = RequestMethod.GET)
    public String clientsList(Model model){
        model.addAttribute("clients",clientRepository.findAll());
        return "clients";
    }
    @RequestMapping(value = "/admin/clients",method = RequestMethod.POST)
    public String clientsAdd(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int phoneNumber,
                                @RequestParam String address,
                                Model model){
        Client newClient = new Client();
        newClient.setFirstName(firstName);
        newClient.setLastName(lastName);
        newClient.setPhoneNumber(phoneNumber);
        newClient.setAddress(address);
        clientRepository.save(newClient);

        model.addAttribute("client",newClient);
        model.addAttribute("products",productRepository.findAll());
        return "redirect:/admin/client/" + newClient.getId();
    }
    @RequestMapping(value = "/admin/client/{id}/products", method = RequestMethod.POST)
    public String clientsAddProduct(@PathVariable Integer id,
                                     @RequestParam Integer productId,
                                     Model model){
        Product product = productRepository.findById(productId).get();
        Client client = clientRepository.findById(id).get();

        if(client != null){
            if(!client.hasProduct(product)){
                client.getProducts().add(product);
            }
            clientRepository.save(client);
            model.addAttribute("client",clientRepository.findById(id).get());
            model.addAttribute("product",productRepository.findAll());
            return "redirect:/admin/client/" + client.getId();
        }
        model.addAttribute("clients",clientRepository.findAll());
        return "redirect:/admin/clients";
    }
    @RequestMapping(value = "/admin/products",method = RequestMethod.GET)
    public String productsAdd(Model model){
        model.addAttribute("product",new Product());
        return "products";
    }
    @RequestMapping(value = "/admin/products", method = RequestMethod.POST)
    public String productsAdd(@ModelAttribute("product") @Valid Product product, Errors errors, Model model){
        if(errors.hasErrors()){
            return "products";
        }
        productRepository.save(product);
        return "redirect:/admin/clients";
    }
}
