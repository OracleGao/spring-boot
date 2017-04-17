package example.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.criteria.Predicate;

import org.elasticsearch.common.util.CollectionUtils;
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
@RequestMapping(path = "/employees/db", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeDbHandler {

	@Autowired
	private EmployeeElsRepository employeeElsRepository;
	
	@Autowired
	private EmployeeDbRepository employeeDbRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Page<Employee> onGetEmployee(@RequestParam Map<String, String> map, Pageable pageable) {
		Page<Employee> employee =  employeeDbRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> list = new ArrayList<Predicate>();
			if (map.containsKey("ageLeft")) {
				list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age").as(Integer.class), Integer.valueOf(map.get("ageLeft"))));
			}
			if (map.containsKey("ageRight")) {
				list.add(criteriaBuilder.lessThanOrEqualTo(root.get("age").as(Integer.class), Integer.valueOf(map.get("ageRight"))));
			}
			if (map.containsKey("name")) {
				list.add(criteriaBuilder.like(root.get("name"), "%" + map.get("name") + "%"));
			}
			if (map.containsKey("about")) {
				list.add(criteriaBuilder.like(root.get("about"), "%" + map.get("about") + "%"));
			}
			if (list.size() > 1) {
				return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
			} else if (list.size() == 1) {
				return list.get(0);
			} else {
				return null;
			}
		}, pageable);
		return employee;
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
		employee.setId(UUID.randomUUID().toString());
		employee.setOrigin("database");
		return employeeDbRepository.save(employee);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Employee onPutEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
		Employee temp = employeeDbRepository.findOne(id);
		temp.merge(employee);
		return employeeDbRepository.save(temp);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public Employee onDeleteEmployee(@PathVariable("id") String id) {
		Employee employee = employeeDbRepository.findOne(id);
		employeeDbRepository.delete(employee);
		return employee;
	}
	
	@RequestMapping(method = RequestMethod.PATCH)
	public Collection<Employee> onPatchEmployee() {
		Iterable<Employee> employees = employeeDbRepository.findAll();
		return CollectionUtils.iterableAsArrayList(employeeElsRepository.save(employees));
	}
	
}
