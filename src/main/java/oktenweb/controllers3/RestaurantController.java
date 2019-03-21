package oktenweb.controllers3;

import oktenweb.models3.Client;
import oktenweb.models3.Restaurant;
import oktenweb.models3.User;
import oktenweb.services3.RestaurantService;
import oktenweb.services3.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestaurantController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    UserService userService;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @PostMapping("/saveRestaurant")
    public String saveRestaurant(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("name") String name){
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userService.save(user);
        Restaurant restaurant = new Restaurant();
        restaurant.setPassword(password);
        restaurant.setUsername(username);
        restaurant.setRestaurantName(name);
        restaurantService.save(restaurant);
        return "login";
    }
}
