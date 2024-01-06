package lk.ijse.dep11.data.jpa.repository;

import com.github.javafaker.Faker;
import lk.ijse.dep11.data.jpa.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional
@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    void assertRepo() {
        assertNotNull(repository);
    }

    @Test
    void saveCustomer(){
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer(faker.regexify("C\\d{3}"), faker.name().firstName(), faker.regexify("0\\d{2}-\\d{7}"));
            repository.save(customer);
            assertTrue(repository.existsById(customer.getId()));
        }
    }

    @Test
    void findCustomerByContact() {
        Optional<Customer> optCustomer = repository.findCustomerByContact("053-9594606");
        assertTrue(optCustomer.isPresent());
        System.out.println(optCustomer.orElseThrow());
    }

    @Test
    void queryCustomersByNameLike() {
        List<Customer> customerList1 = repository.queryCustomersByNameLike("K%");
        assertEquals(3, customerList1.size());
        List<Customer> customerList2 = repository.queryCustomersByNameLike("Ke%");
        assertEquals(2, customerList2.size());
        List<Customer> customerList3 = repository.queryCustomersByNameLike("C%");
        assertEquals(2, customerList3.size());
        List<Customer> customerList4 = repository.queryCustomersByNameLike("A%");
        assertTrue(customerList4.isEmpty());
    }
}