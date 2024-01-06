package lk.ijse.dep11.data.jpa.repository;

import lk.ijse.dep11.data.jpa.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository repository;

    @Test
    void assertRepo() {
        assertNotNull(repository);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Kasun,  Galle",
            "Nuwan,  Panadura",
            "Ruwan,  Colombo",
            "Supun,  Galle",
            "Nimal,  Panadura",
    })
    void addSomeEmployees(String name, String address){
        Employee employee = new Employee(name, address);
        repository.save(employee);
        assertTrue(repository.existsById(employee.getId()));
    }

    @Test
    void findEmployeesByName() {
        List<Employee> employeeList = repository.findEmployeesByName("Kasun");
        assertEquals(2, employeeList.size());
        employeeList.forEach(System.out::println);
    }

    @Test
    void findEmployeesByAddress() {
        List<Employee> employeeList = repository.findEmployeesByAddress("Panadura");
        assertEquals(4, employeeList.size());
        employeeList.forEach(System.out::println);
    }

    @Test
    void findEmployeesByNameAndAddress() {
        List<Employee> employeeList = repository.findEmployeesByNameAndAddress("Nuwan","Panadura");
        assertEquals(2, employeeList.size());
        employeeList.forEach(System.out::println);
    }

    @Test
    void abc() {
        List<Employee> employeeList = repository.abc("Nuwan","Panadura");
        assertEquals(2, employeeList.size());
        employeeList.forEach(System.out::println);
    }

    @Test
    void countEmployeesByAddress() {
        long count = repository.countEmployeesByAddress("Panadura");
        assertEquals(4, count);
    }

    @Test
    void updateEmployeesByAddress() {
        repository.updateEmployeesByAddress("Galle", "Kandy");
        assertTrue(repository.findEmployeesByAddress("Galle").isEmpty());
    }
}