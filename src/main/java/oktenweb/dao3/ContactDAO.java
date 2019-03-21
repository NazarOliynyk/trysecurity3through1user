package oktenweb.dao3;

import oktenweb.models3.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDAO extends JpaRepository<Contact, Integer> {
}
