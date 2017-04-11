package example.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import example.entity.Employee;

public interface EmployeeDbRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
	
}
