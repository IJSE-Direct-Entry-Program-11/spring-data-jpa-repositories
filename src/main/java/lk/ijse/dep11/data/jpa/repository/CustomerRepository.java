package lk.ijse.dep11.data.jpa.repository;

import lk.ijse.dep11.data.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    /*
    * find      [Subject] By [Predicate]
    * query     [Subject] By [Predicate]
    * get       [Subject] By [Predicate]
    * read      [Subject] By [Predicate]
    * */

    // SELECT * FROM customer WHERE contact = ?
    Optional<Customer> findCustomerByContact(String contact);

    // SELECT * FROM customer WHERE name LIKE ?
    List<Customer> queryCustomersByNameLike(String name);

}
