package co.id.mii.serverside.controller;
import co.id.mii.serverside.model.Employee;
import co.id.mii.serverside.model.dto.request.EmployeeRequest;
import co.id.mii.serverside.model.dto.response.EmployeeUser;
import co.id.mii.serverside.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody EmployeeRequest employeeRequest) {
        return new ResponseEntity(employeeService.create(employeeRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
        return new ResponseEntity(employeeService.update(id, employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id) {
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }
    
    @GetMapping("/mapper/{id}")
    public ResponseEntity<EmployeeUser> get(@PathVariable Long id){
        return new ResponseEntity(employeeService.employeeUser(id), HttpStatus.OK);
    }
    
    

}
