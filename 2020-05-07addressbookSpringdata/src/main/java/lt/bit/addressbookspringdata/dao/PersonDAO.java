package lt.bit.addressbookspringdata.dao;

import java.util.List;
import lt.bit.addressbookspringdata.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Gabrielius
 */
public interface PersonDAO extends JpaRepository<Person, Integer>{
    
    @Query("select p from Person p where p.firstName like concat('%', :filter, '%') or p.lastName like concat('%', :filter, '%') order by p.firstName")
    public List<Person> filteredList(@Param("filter") String filter);
    
    @Query("select p from Person p order by p.firstName")
    public List<Person> orderedList();
    
    @Query(name = "Person.findAll")
    public List<Person> orderedByLastName();
    
}
