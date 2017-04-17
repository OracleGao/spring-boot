package example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Document(indexName = "e9", type = "employee")
public class Employee implements Mergeable<Employee>{
	
	@Id
	private String id;
	private String name;
	private int age;
	private String about;
	private String origin;
	
	public Employee() {
		super();
	}

	@Override
	public void merge(Employee employee) {
		if (StringUtils.isNoneBlank(employee.name)) {
			this.name = employee.name;
		}
		if (employee.age != 0) {
			this.age = employee.age;
		}
		if (StringUtils.isNotBlank(employee.about)) {
			this.about = employee.about;
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
}
