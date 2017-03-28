package example.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

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

import example.entity.Student;
import example.repository.StudentRepository;

@RestController
@RequestMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentHandler {

	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * {"name":"小李", "age": 20, "sex": 1, "address": { "address":"立山区", "zipCode": "20010" }}
	 * {"name":"小王", "age": 17, "sex": 2, "address": { "address":"铁西区", "zipCode": "20011" }}
	 * @param student
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student onPost(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Page<Student> onGet(@RequestParam Map<String, String> map, Pageable pageable) {
		return studentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> list = new ArrayList<Predicate>();
			if (map.containsKey("ageLeft")) {
				list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age").as(Integer.class), new Integer(map.get("ageLeft"))));
			}
			if (map.containsKey("ageRight")) {
				list.add(criteriaBuilder.lessThanOrEqualTo(root.get("age").as(Integer.class), new Integer(map.get("ageRight"))));
			}
			if (map.containsKey("name")) {
				list.add(criteriaBuilder.like(root.get("name"), "%" + map.get("name") + "%"));
			}
			if (map.containsKey("sex")) {
				list.add(criteriaBuilder.equal(root.get("sex"), new Integer(map.get("sex"))));
			}
			if (map.containsKey("address")) {
				list.add(criteriaBuilder.like(root.get("address").get("address"), "%" + map.get("address") + "%"));
			}
			if (list.size() > 1) {
				return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
			} else if (list.size() == 1) {
				return list.get(0);
			} else {
				return null;
			}
		}, pageable);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Student onPut(@PathVariable("id") long id, @RequestBody Student student) {
		Student temp = studentRepository.findOne(id);
		temp.merge(student);
		studentRepository.save(temp);
		return temp;
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void onDelete(@PathVariable("id") long id){
		studentRepository.delete(id);
	}
	
}
