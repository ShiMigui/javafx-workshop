package model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.database.Database;
import model.entities.Department;
import model.exceptions.DaoException;
import model.interfaces.IEntityDao;
import model.localdata.DepartmentLocalData;
import model.localdata.LocalDataFactory;

public final class DepartmentDao implements IEntityDao<Department> {
    protected DepartmentDao() {
    }

    @Override
    public Department convert(Integer id, ResultSet rs) {
	DepartmentLocalData local = LocalDataFactory.getLocalDepartments();
	try {	    
	    String name = rs.getString("nm_department");
	    
	    Department obj = local.get(id);
	    if(obj == null) {
		obj = new Department();
		obj.setId(id);
		local.put(obj);
	    }
	    
	    obj.setName(name);
	    
	    return obj;
	} catch (SQLException e) {
	    throw new DaoException("Cannot convert ResultSet: " + e.getMessage());
	}
    }

    @Override
    public Department findById(Integer id) {
	String sql = "SELECT nm_department FROM DEPARTMENT WHERE cd_department = ?;";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    st.setInt(1, id);

	    try (ResultSet rs = st.executeQuery()) {
		if (rs.next())
		    return convert(id, rs);
		else
		    throw new DaoException("Unexpected Error: Cannot select department(" + id + ")");
	    }
	} catch (SQLException e) {
	    throw new DaoException("Unexpected Error: " + e.getMessage());
	}
    }

    @Override
    public void insert(Department obj) {
	String sql = "INSERT INTO DEPARTMENT (nm_department) VALUES (?);";

	try (Connection con = Database.getConnection();
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    st.setString(1, obj.getName());

	    if (st.executeUpdate() == 0)
		throw new DaoException("Failed to insert department: " + obj);

	    try (ResultSet rs = st.getGeneratedKeys()) {
		if (rs.next())
		    obj.setId(rs.getInt(1));
		else
		    throw new DaoException("Unexpected Error: Cannot insert " + obj);
	    }
	} catch (SQLException e) {
	    throw new DaoException("Unexpected Error: " + e.getMessage());
	}
    }

    @Override
    public void update(Department obj) {
	String sql = "UPDATE DEPARTMENT SET nm_department = ? WHERE cd_department = ?;";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    st.setString(1, obj.getName());
	    st.setInt(2, obj.getId());

	    if (st.executeUpdate() == 0)
		throw new DaoException("Unexpected Error: Cannot update " + obj);
	} catch (SQLException e) {
	    throw new DaoException("Unexpected Error: " + e.getMessage());
	}
    }

    @Override
    public List<Department> findAll() {
	String sql = "SELECT cd_department, nm_department FROM DEPARTMENT;";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    List<Department> deps = new ArrayList<>();

	    try (ResultSet rs = st.executeQuery()) {
		while (rs.next())
		    deps.add(convert(rs.getInt("cd_department"), rs));
	    }

	    return deps;
	} catch (SQLException e) {
	    throw new DaoException("Unexpected Error: " + e.getMessage());
	}
    }

    @Override
    public void removeById(Integer id) {
	String sql = "DELETE FROM DEPARTMENT WHERE cd_department = ?;";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    st.setInt(1, id);

	    if (st.executeUpdate() == 0)
		throw new DaoException("Unexpected error: Cannot delete department(" + id + ")!");
	    
	    LocalDataFactory.getLocalDepartments().remove(id);
	} catch (SQLException e) {
	    throw new DaoException("Cannot delete department(" + id + "): " + e.getMessage());
	}
    }
}
