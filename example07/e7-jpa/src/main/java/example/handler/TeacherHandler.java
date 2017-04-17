package example.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.entity.Teacher;
import example.repository.TeacherRepository;

@RestController
@RequestMapping(path = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherHandler {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<Teacher> onGet(@RequestParam Map<String, String> map, Pageable pageable){

		Page<Teacher> teachers = teacherRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> list = new ArrayList<Predicate>();
			if (map.containsKey("name")) {
				list.add(criteriaBuilder.like(root.get("name"), "%" + map.get("name") + "%"));
			}
			if (map.containsKey("grade")) {
				list.add(criteriaBuilder.like(root.get("grade").get("name"), "%" + map.get("grade") + "%"));
			}
			if (list.size() > 1) {
				return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
			} else if (list.size() == 1) {
				return list.get(0);
			} else {
				return null;
			}
		}, pageable);
		
		//solve json serialization Infinite Recursion with jpa bi-directional relationship 
		teachers.forEach(teacher -> teacher.breakBidirectionalRelationship());
		return teachers;
	}
	
}
