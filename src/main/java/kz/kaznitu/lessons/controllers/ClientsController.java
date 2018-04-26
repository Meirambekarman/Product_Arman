package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Client;
import kz.kaznitu.lessons.models.Collection;
import kz.kaznitu.lessons.repositories.ClientRepository;
import kz.kaznitu.lessons.repositories.CollectionRepository;
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
    private CollectionRepository collectionRepository;

    @RequestMapping("/admin/client/{id}")
    public String client(@PathVariable("id")int id, Model model){
        model.addAttribute("client", clientRepository.findById(id).get());
        model.addAttribute("collections",collectionRepository.findAll());
        return "admin/client";
    }

    @RequestMapping(value = "/admin/clients",method = RequestMethod.GET)
    public String clientsList(Model model){
        model.addAttribute("clients",clientRepository.findAll());
        return "admin/clients";
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
        model.addAttribute("collections",collectionRepository.findAll());
        return "redirect:admin/client/" + newClient.getId();
    }
    @RequestMapping(value = "/admin/client/{id}/collections", method = RequestMethod.POST)
    public String clientsAddCollection(@PathVariable Integer id,
                                     @RequestParam Integer collectionId,
                                     Model model){
        Collection collection = collectionRepository.findById(collectionId).get();
        Client client = clientRepository.findById(id).get();

        if(client != null){
            if(!client.hasCollection(collection)){
                client.getCollections().add(collection);
            }
            clientRepository.save(client);
            model.addAttribute("client",clientRepository.findById(id).get());
            model.addAttribute("collection",collectionRepository.findAll());
            return "redirect:admin/client/" + client.getId();
        }
        model.addAttribute("clients",clientRepository.findAll());
        return "redirect:admin/clients";
    }
    @RequestMapping(value = "/admin/collections",method = RequestMethod.GET)
    public String collectionsAdd(Model model){
        model.addAttribute("collection",new Collection());
        return "admin/collections";
    }
    @RequestMapping(value = "/admin/collections", method = RequestMethod.POST)
    public String collectionsAdd(@ModelAttribute("collection") @Valid Collection collection, Errors errors, Model model){
        if(errors.hasErrors()){
            return "admin/collections";
        }
        collectionRepository.save(collection);
        return "redirect:admin/clients";
    }
}
