package example.handler;

import java.util.Collection;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
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
import example.repository.db.EmployeeDbRepository;
import example.repository.els.EmployeeElsRepository;

@RestController
@RequestMapping(path = "/employees/els", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeElsHandler {

	@Autowired
	private EmployeeElsRepository employeeElsRepository;
	
	@Autowired
	private EmployeeDbRepository employeeDbRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Page<Employee> onGetEmployee(@RequestParam Map<String, String> map, Pageable pageable) {
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		if (map.containsKey("name")) {
			queryBuilder.must(QueryBuilders.matchQuery("name", map.get("name")));
		}
		if (map.containsKey("about")) {
			queryBuilder.must(QueryBuilders.matchQuery("about", map.get("about")));
		}
		RangeQueryBuilder rangeQueryBuilder = null;
		if (map.containsKey("ageLeft")) {
			rangeQueryBuilder = new RangeQueryBuilder("age");
			rangeQueryBuilder.gte(Integer.parseInt(map.get("ageLeft")));
		}
		if (map.containsKey("ageRight")) {
			if (rangeQueryBuilder == null) {
				rangeQueryBuilder = new RangeQueryBuilder("age");
			}
			rangeQueryBuilder.lte(Integer.parseInt(map.get("ageRight")));
		}
		if (rangeQueryBuilder != null) {
			queryBuilder.must(rangeQueryBuilder);
		}
		Page<Employee> employees = employeeElsRepository.search(queryBuilder, pageable);
		return employees;
	}

	/**
	 * {"name":"张三","age":25,"about":"我喜欢读书也喜欢看电影"}
	 * {"name":"李四","age":23,"about":"我爱边旅行边读书"}
	 * {"name":"王五","age":28,"about":"我就爱呆在家里看电影"}
	 * 
	 * @param employee
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Employee onPostEmployee(@RequestBody Employee employee) {
		employee.setOrigin("elasticsearch");
		return employeeElsRepository.save(employee);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Employee onPutEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
		Employee temp = employeeElsRepository.findOne(id);
		temp.merge(employee);
		return employeeElsRepository.save(temp);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public Employee onDeleteEmployee(@PathVariable("id") String id) {
		Employee employee = employeeElsRepository.findOne(id);
		employeeElsRepository.delete(employee);
		return employee;
	}
	
	@RequestMapping(method = RequestMethod.PATCH)
	public Collection<Employee> onPatchEmployee() {
		Iterable<Employee> employees = employeeElsRepository.findAll();
		return employeeDbRepository.save(employees);
	}
	
}
