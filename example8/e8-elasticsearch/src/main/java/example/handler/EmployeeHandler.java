package example.handler;

import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.entity.Employee;
import example.repository.EmployeeRepository;

@RestController
@RequestMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeHandler {

	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Page<Employee> onGetEmployee(@RequestParam Map<String, String> map, Pageable pageable) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if (map.containsKey("name")) {
			queryBuilder.must(QueryBuilders.matchQuery("name", map.get("name")));
		}
		if (map.containsKey("about")) {
			queryBuilder.must(QueryBuilders.matchQuery("about", map.get("about")));
		}
		if (map.containsKey("interests")) {
			queryBuilder.must(QueryBuilders.matchQuery("interests", map.get("interests")));
		}
		Page<Employee> employees = employeeRepository.search(queryBuilder, pageable);
		return employees;
	}

	/**
	 * {"name":"张三","age":25,"about":"我喜欢读书也喜欢看电影","interests":["阅读","影视"]}
	 * {"name":"李四","age":23,"about":"我爱边旅行边读书","interests":["阅读","旅游"]}
	 * {"name":"王五","age":28,"about":"我就爱呆在家里看电影","interests":["宅","影视"]}
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Employee onPostEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Employee onPutEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
		employee.setId(id);
		return employeeRepository.save(employee);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public Employee onDeleteEmployee(@PathVariable("id") String id) {
		Employee employee = employeeRepository.findOne(id);
		employeeRepository.delete(employee);
		return employee;
	}
	
}
