package example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Address implements Mergeable<Address>, Bidirectional {

	@GeneratedValue
	@Id
	private long id;

	private String address;

	private String zipCode;
	
	@OneToOne(cascade = CascadeType.REFRESH, mappedBy = "address")
	private Student student;

	public Address() {
		super();
	}
	
	public void merge(Address address) {
		if (StringUtils.isNotBlank(address.address)) {
			this.address = address.address;
		}
		if (StringUtils.isNotBlank(address.zipCode)) {
			this.zipCode = address.zipCode;
		}
	}
	

	public void breakBidirectionalRelationship() {
		student.releaseBidirectionalRelationship();
	}
	
	public void releaseBidirectionalRelationship() {
		this.student = null;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
