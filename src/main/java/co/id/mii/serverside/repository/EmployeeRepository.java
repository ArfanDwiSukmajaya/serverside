package co.id.mii.serverside.repository;

import co.id.mii.serverside.model.Country;
import co.id.mii.serverside.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Country> findByName(String name);

    //    JQPL for Native Query
    @Query(value = "SELECT * FROM tb_employee WHERE id = ?1", nativeQuery = true)
    List<Employee> getEmployeesByIdNative(Long id);


    @Query("SELECT e FROM Employee e WHERE e.id = ?1")
    List<Employee> getEmployeesById(Long id);


}
