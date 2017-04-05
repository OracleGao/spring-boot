package example.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Grade implements Mergeable<Grade> {
	
	@GeneratedValue
	@Id
	private long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "grade")
	private Set<Teacher> teachers;
	public Grade() {
		super();
	}


	@Override
	public void merge(Grade grade) {
		if(grade == null) {
			return;
		}
		if (StringUtils.isNotBlank(grade.name)) {
			this.name = grade.name;
		}
		if (grade.teachers != null && grade.teachers.size() > 0) {
			this.teachers.addAll(grade.teachers);
		}
	}
	
	public void breakBidirectionalRelationship() {
		this.teachers.forEach(teacher -> teacher.releaseBidirectionalRelationship());
	}
	
	void releaseBidirectionalRelationship() {
		this.teachers = null;
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

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
}
