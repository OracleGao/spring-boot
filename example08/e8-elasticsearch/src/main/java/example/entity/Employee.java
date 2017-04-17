package example.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "e8", type = "employee")
public class Employee implements Mergeable<Employee>{
	
	private String id;
	private String name;
	private int age;
	private String about;
	private List<String> interests;

	public Employee() {
		super();
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

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
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
		if (employee.interests != null && employee.interests.size() > 0) {
			this.interests.addAll(employee.interests);
		}
	}
	
}
