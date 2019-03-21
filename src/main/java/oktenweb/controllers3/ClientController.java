package oktenweb.controllers3;

import oktenweb.dao3.ClientDAO;
import oktenweb.models3.Client;
import oktenweb.models3.User;
import oktenweb.services3.ClientService;
import oktenweb.services3.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    ClientService clientService;
    @Autowired
    UserService userService;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @PostMapping("/saveClient")
    public String saveClient(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name){
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userService.save(user);
        Client client = new Client();
        client.setPassword(password);
        client.setUsername(username);
        client.setClientName(name);
        clientService.save(client);
        return "login";
    }

}
