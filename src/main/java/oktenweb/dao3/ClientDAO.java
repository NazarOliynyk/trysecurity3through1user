package oktenweb.dao3;

import oktenweb.models3.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Integer> {
    //Client findClientByUsername(String username);
}
