package example.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.entity.Employee;

@RestController
@RequestMapping(path = "/employees/db", produces = MediaType.APPLICATION_JSON_VALUE)
public interface EmployeeDbRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
	
}
