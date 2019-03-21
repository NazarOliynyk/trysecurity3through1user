package oktenweb.dao3;

import oktenweb.models3.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantDAO extends JpaRepository<Restaurant, Integer> {
    //Restaurant findRestaurantByName(String name);
}