package co.id.mii.serverside.service;
import co.id.mii.serverside.model.Employee;
import co.id.mii.serverside.model.Role;
import co.id.mii.serverside.model.User;
import co.id.mii.serverside.model.dto.request.EmailRequest;
import co.id.mii.serverside.model.dto.request.EmployeeRequest;
import co.id.mii.serverside.model.dto.response.EmployeeUser;
import co.id.mii.serverside.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private RoleService roleService;
    private JavaMailSenderService javaMailSenderService;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private ModelMapper modelMapper;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "employee not Found"));
    }

    public Employee create(EmployeeRequest employeeRequest) {
//        ModelMapper modelMapper = new ModelMapper();
//        TypeMap<EmployeeRequest,User> typeMapEmployeRequestUser = modelMapper.typeMap(EmployeeRequest.class, User.class);
//
//        typeMapEmployeRequestUser.addMappings( mapper -> {
//            mapper.map(EmployeeRequest::getUsername, User::setUsername);
//        });
//
//        TypeMap<EmployeeRequest,Employee> typeMapEmployeRequestEmployee = modelMapper.typeMap(EmployeeRequest.class, Employee.class);
//
//        typeMapEmployeRequestEmployee.addMappings( mapper -> {
//            mapper.map(EmployeeRequest::getEmail, Employee::setEmail);
//            mapper.map(EmployeeRequest::getName, Employee::setName);
//            mapper.map(EmployeeRequest::getNumber, Employee::setNumber);
//        });
//        Employee employee = modelMapper.map(employeeRequest, Employee.class);
//        User user = modelMapper.map(employeeRequest, User.class);
//        employee.setUser(user);
//        List<Role> role = new ArrayList();
//        role.add(roleService.getById(1L)); // Role User
//        user.setRoles(role);
//        user.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
//
//        user.setEmployee(employee);
//        return employeeRepository.save(employee);

//      =====================
//        Employee employee = new Employee();
//        employee.setEmail(employeeRequest.getEmail());
//        employee.setName(employeeRequest.getName());
//        employee.setNumber(employeeRequest.getNumber());
//
//        User user = new User();
//        user.setUsername(employeeRequest.getUsername());
//        user.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));// Password Encoder ke Encrypt
//        user.setIsEnabled(Boolean.TRUE);// Akun itu dikunci atau tidak?
//        user.setIsAccountLocked(Boolean.FALSE);// Digunakan untuk verifikasi Akun
//
//        List<Role> role = new ArrayList();
//        role.add(roleService.getById(1L)); // Role User
//        user.setRoles(role);
//
//        employee.setUser(user);
//        user.setEmployee(employee);
//
//        Employee e = employeeRepository.save(employee);
//
//        javaMailSenderService.sendSimpleEmail(new EmailRequest(employeeRequest.getEmail(), 
//                "Welcome Home " + employeeRequest.getUsername(), 
//                "Selamat Anda Terlah berhasil terdaftar pada program kami."));
//
//        return e;
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        User user = modelMapper.map(employeeRequest, User.class);
//        
        
        user.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));// Password Encoder ke Encrypt
        user.setIsEnabled(Boolean.TRUE);// Akun itu dikunci atau tidak?
        user.setIsAccountLocked(Boolean.FALSE);// Digunakan untuk verifikasi Akun
        List<Role> role = new ArrayList();
        role.add(roleService.getById(1L)); // Role User
        user.setRoles(role);
        employee.setUser(user);
        user.setEmployee(employee);
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employee) {
        Employee oldEmployee = getById(id);
        employee.setId(id);
        employee.setUser(oldEmployee.getUser());
        return employeeRepository.save(employee);
    }

    public Employee delete(Long id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);
        return employee;
    }

    public void findByName(String name) {
        if (employeeRepository.findByName(name).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee already exists");
        }
    }
    
//    Method dengan mapper
    public EmployeeUser employeeUser (Long id){
        Employee employee = getById(id);
        User user = userService.getById(id);
        EmployeeUser employeeUser = modelMapper.map(employee, EmployeeUser.class);
        modelMapper.map(user, employeeUser);
        return employeeUser;
        
    }

}