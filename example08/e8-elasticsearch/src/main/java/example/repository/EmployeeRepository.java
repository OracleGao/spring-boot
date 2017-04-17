package example.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import example.entity.Employee;

public interface EmployeeRepository	extends ElasticsearchRepository<Employee, String> {
	
}
