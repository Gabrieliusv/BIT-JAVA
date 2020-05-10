package lt.bit.addressbookspringdata.dao;

import java.util.List;
import lt.bit.addressbookspringdata.data.Address;
import lt.bit.addressbookspringdata.data.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Gabrielius
 */
public interface ContactDAO extends JpaRepository<Contact, Integer>{
    
    @Query(name = "Contact.findByPersonId")
    public List<Contact> findByPersonId(@Param("id") Integer id);
    
}
