package example.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Student implements Mergeable<Student> {

	@ApiModelProperty(hidden = true)
	@GeneratedValue
	@Id
	private long id;

	
	private String name;

	private int sex;

	private int age;

	@ApiModelProperty(hidden = true)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@ApiModelProperty(hidden = true)
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(name = "student_teacher", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private Set<Teacher> teachers;
	
	public Student() {
		super();
	}

	public void merge(Student student) {
		if (student == null) {
			return;
		}
		if (student.age != 0) {
			this.age = student.age;
		}
		if (StringUtils.isNotBlank(student.name)) {
			this.name = student.name;
		}
		if (student.sex != 0) {
			this.sex = student.sex;
		}
		if (address != null) {
			this.address.merge(address);
		}
	}
	

	public void breakBidirectionalRelationship() {
		if (address != null) {
			address.releaseBidirectionalRelationship();
		}
		if (teachers != null) {
			teachers.forEach(teacher -> teacher.releaseBidirectionalRelationship());
		}
	}
	
	void releaseBidirectionalRelationship() {
		this.teachers = null;
		this.address = null;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

}
