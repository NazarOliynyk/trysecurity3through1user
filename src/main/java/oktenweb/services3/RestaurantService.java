package oktenweb.services3;

import oktenweb.dao3.RestaurantDAO;
import oktenweb.models3.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantDAO restaurantDAO;

    public RestaurantService(RestaurantDAO restaurantDAO) {

        this.restaurantDAO = restaurantDAO;
    }
    public void save(Restaurant restaurant){
        if(restaurant!=null){
            restaurantDAO.save(restaurant);
        }
    }
    public List<Restaurant> findAll(){
        return restaurantDAO.findAll();
    }

    public Restaurant getOne(int id){

        return restaurantDAO.getOne(id);
    }

}
