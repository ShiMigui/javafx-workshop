package model.entities;

import java.util.Date;
import java.util.Objects;

import model.interfaces.IEntity;

public final class Seller implements IEntity {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String email;
    private String name;
    private Date birthDate;
    private Double salary;

    private Department department;

    @Override
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = normalize(email);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    public Double getSalary() {
	return salary;
    }

    public void setSalary(Double salary) {
	this.salary = salary;
    }

    public Department getDepartment() {
	return department;
    }

    public void setDepartment(Department department) {
	this.department = department;
    }
    
    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (!(this == obj) || obj == null || getClass() != obj.getClass())
	    return false;
	Seller other = (Seller) obj;
	return id.equals(other.getId());
    }

    @Override
    public String toString() {
	return "Seller(" + id + ") [email=" + email + ", name=" + name + ", birthDate=" + birthDate + ", salary="
		+ salary + ", department=" + department + "]";
    }
}
