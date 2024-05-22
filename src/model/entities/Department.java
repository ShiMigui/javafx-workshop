package model.entities;

import java.util.Objects;

import model.interfaces.IEntity;

public final class Department implements IEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public Department() {
    }

    public Department(Integer id, String name) {
	this.id = id;
	setName(name);
    }

    @Override
    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = normalize(name);
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (!(this == obj) || obj == null || getClass() != obj.getClass())
	    return false;
	Department other = (Department) obj;
	return id.equals(other.getId());
    }

    @Override
    public String toString() {
	return "Department(" + id + ") [name=" + name + "]";
    }
}
