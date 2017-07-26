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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	@ApiOperation(value="添加学生信息", notes="添加学生信息")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Student onPost(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@ApiOperation(value="查询学生信息", notes="根据查询条件查询获取学生信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ageLeft", value = "年龄左闭区间", paramType = "query", required = false, dataType = "int"),
            @ApiImplicitParam(name = "ageRight", value = "年龄右闭区间", paramType = "query", required = false, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "学生姓名模糊匹配", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "学生性别，0：女，1：男", paramType = "query", required = false, dataType = "int"),
            @ApiImplicitParam(name = "address", value = "学生地址信息模糊匹配", paramType = "query", required = false, dataType = "String"),
    })
	@RequestMapping(method = RequestMethod.GET)
	public Page<Student> onGet(@ApiParam(hidden = true) @RequestParam(required = false) Map<String, String> map, Pageable pageable) {
		Page<Student> students =  studentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
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
		students.forEach(student -> student.breakBidirectionalRelationship());
		return students;
	}

	@ApiOperation(value="修改学生信息")
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Student onPut(@PathVariable("id") long id, @RequestBody Student student) {
		Student temp = studentRepository.findOne(id);
		temp.merge(student);
		studentRepository.save(temp);
		return temp;
	}
	
	@ApiOperation(value="删除学生信息")
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void onDelete(@PathVariable("id") long id){
		studentRepository.delete(id);
	}
	
}
