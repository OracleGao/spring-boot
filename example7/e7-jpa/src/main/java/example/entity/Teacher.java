package example.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Teacher implements Mergeable<Teacher> {
	 
	@GeneratedValue
	@Id
	private long id;
	
	private String name;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name="grade_id")
	private Grade grade;

	@ManyToMany(mappedBy = "teachers", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private Set<Student> students;
	
	public Teacher() {
		super();
	}

	public void merge(Teacher teacher) {
		if (teacher == null) {
			return;
		}
		if (StringUtils.isNotBlank(teacher.name)) {
			this.name = teacher.name;
		}
		if (teacher.grade != null) {
			this.grade.merge(teacher.grade);
		}
	}
	
	/** 
	 *  solve json serialization Infinite Recursion with jpa bi-directional relationship
	 */
	public void breakBidirectionalRelationship() {
		this.students.forEach(student -> student.releaseBidirectionalRelationship());
		this.grade.releaseBidirectionalRelationship();
	} 
	
	/** 
	 *  release jpa bi-directional relationship
	 */
	void releaseBidirectionalRelationship() {
		this.students = null;
		this.grade = null;
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
