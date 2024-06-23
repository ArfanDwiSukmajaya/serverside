package co.id.mii.serverside.controller;
import co.id.mii.serverside.model.Country;
import co.id.mii.serverside.model.Employee;
import co.id.mii.serverside.model.Region;
import co.id.mii.serverside.model.User;
import co.id.mii.serverside.model.dto.request.AddRoleUserRequest;
import co.id.mii.serverside.model.dto.request.EmployeeRequest;
import co.id.mii.serverside.service.EmployeeService;
import co.id.mii.serverside.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private EmployeeService EmployeeService;
    @Autowired
    public UserController(UserService userService, EmployeeService EmployeeService) {
        this.userService = userService;
        this.EmployeeService = EmployeeService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return new ResponseEntity(userService.getById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody EmployeeRequest employee) {
        return new ResponseEntity(EmployeeService.create(employee), HttpStatus.CREATED);
    }
    @PostMapping("/addRole")
    public ResponseEntity<Employee> AddRoles(@RequestBody AddRoleUserRequest addRoleUserRequest) {
        return new ResponseEntity(userService.addRole(addRoleUserRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity(userService.update(id, user), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        return new ResponseEntity(userService.delete(id), HttpStatus.OK);
    }
}