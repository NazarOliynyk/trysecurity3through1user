package oktenweb.services3;

import oktenweb.dao3.ClientDAO;
import oktenweb.models3.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientDAO clientDAO;
    public ClientService(ClientDAO clientDAO) {

        this.clientDAO = clientDAO;
    }

    public void save(Client client){
        if(client!=null){
            clientDAO.save(client);
        }
    }
    public List<Client> findAll(){
        return clientDAO.findAll();
    }

    public Client getOne(int id){

        return clientDAO.getOne(id);
    }
}
