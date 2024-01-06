package lk.ijse.dep11.data.jpa.repository;

import lk.ijse.dep11.data.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /*
     * count     [Subject] By [Predicate]
     * find      [Subject] By [Predicate]
     * query     [Subject] By [Predicate]
     * get       [Subject] By [Predicate]
     * read      [Subject] By [Predicate]
     * */

    // SELECT * FROM employee WHERE name = ?
    List<Employee> findEmployeesByName(String name);

    // SELECT * FROM employee WHERE address = ?
    List<Employee> findEmployeesByAddress(String address);

    // SELECT * FROM employee WHERE name = ? AND address = ?
    List<Employee> findEmployeesByNameAndAddress(String name, String address);

    @Query(value = "SELECT e FROM Employee e WHERE e.name = :name AND e.address = :address")
    List<Employee> abc(@Param("name") String employeeName, String address);

    // SELECT COUNT(*) FROM employee WHERE address = ?
    long countEmployeesByAddress(String address);

    @Transactional
    @Modifying
    @Query("UPDATE Employee SET address = :newAddress WHERE address = :currentAddress")
    void updateEmployeesByAddress(String currentAddress, String newAddress);
}
