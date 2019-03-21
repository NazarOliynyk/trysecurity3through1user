package oktenweb.controllers3;

import oktenweb.dao3.UserDAO;
import oktenweb.models3.Client;
import oktenweb.models3.Contact;
import oktenweb.models3.Restaurant;
import oktenweb.models3.User;
import oktenweb.services3.ClientService;
import oktenweb.services3.ContactService;
import oktenweb.services3.RestaurantService;
import oktenweb.services3.UserService;
import oktenweb.services3.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserDAO userDAO;
    @Autowired
    private ContactService contactService;
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    ClientService clientService;


    @GetMapping({"/", "/goToIndexPage"})
    public String index(){
        return "index";
    }

    @GetMapping("/restaurants")
    public String restaurants(){
        return "restaurant";
    }

    @GetMapping("/clients")
    public String clients(){
        return "client";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    String name = "";
    String className = "";
    String username = "";



    @PostMapping("/successURL")
    public String successURL(Model model){

                Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth.getName());

        List<Client> clients = clientService.findAll();
        for (Client client : clients) {
            if (client.getUsername().equals(auth.getName())){
                className = "CLIENT";
                username = client.getUsername();
                name = client.getClientName();
                break;
            }
        }
        List<Restaurant> restaurants = restaurantService.findAll();
        for (Restaurant restaurant : restaurants) {
            if(restaurant.getUsername().equals(auth.getName())){
                className = "RESTAURANT";
                username = restaurant.getUsername();
                name = restaurant.getRestaurantName();
                break;
            }
        }

        model.addAttribute("className", className);
        model.addAttribute("username", username);
        model.addAttribute("name", name);

        return "securedPage";
    }

    @GetMapping("/saveContact")
    public String saveContact(Model model,
                              @RequestParam("username") String username,
                              @RequestParam("contactName") String contactName,
                              @RequestParam("email") String email){

        String usernameCurrent ="";
        Contact contact = new Contact();
        List<Client> clients = clientService.findAll();
        for (Client client : clients) {
            if (client.getUsername().equals(username)){
                contact.setContactName(contactName);
                contact.setEmail(email);
                contact.setClient(client);
                contactService.save(contact);
                className = "CLIENT";
                usernameCurrent = username;

                name = client.getClientName();
                break;
            }
        }

        List<Restaurant> restaurants = restaurantService.findAll();
        for (Restaurant restaurant : restaurants) {
            if(restaurant.getUsername().equals(username)){
                contact.setContactName(contactName);
                contact.setEmail(email);
                contact.setRestaurant(restaurant);
                contactService.save(contact);
                className = "RESTAURANT";
                usernameCurrent = username;

                name = restaurant.getRestaurantName();
                break;
            }
        }
        model.addAttribute("className", className);
        model.addAttribute("username", usernameCurrent);
        model.addAttribute("name", name);

        return "securedPage";
    }
}
