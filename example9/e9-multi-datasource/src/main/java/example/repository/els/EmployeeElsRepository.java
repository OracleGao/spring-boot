package example.repository.els;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import example.entity.Employee;

public interface EmployeeElsRepository extends ElasticsearchRepository<Employee, String> {
	
}
