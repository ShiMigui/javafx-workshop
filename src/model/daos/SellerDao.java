package model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.database.Database;
import model.entities.Department;
import model.entities.Seller;
import model.exceptions.DaoException;
import model.interfaces.IEntityDao;

public class SellerDao implements IEntityDao<Seller> {
    private static Map<Integer, Department> deps = new HashMap<>();

    protected SellerDao() {
    }

    @Override
    public Seller convert(Integer id, ResultSet rs) {
	try {
	    Seller obj = new Seller();
	    obj.setId(id);
	    obj.setName(rs.getString("nm_seller"));
	    obj.setEmail(rs.getString("nm_email"));
	    obj.setBirthDate(rs.getDate("dt_birth"));
	    obj.setSalary(rs.getDouble("vl_salary"));

	    Integer cd = rs.getInt("cd_department");
	    Department dep = deps.get(cd);
	    if (dep == null) {
		dep = DaoFactory.getDepartmentDao().convert(cd, rs);
		deps.put(cd, dep);
	    }
	    obj.setDepartment(dep);

	    return obj;
	} catch (SQLException e) {
	    throw new DaoException("Cannot convert ResultSet: " + e.getMessage());
	}
    }

    @Override
    public Seller findById(Integer id) {
	String sql = "SELECT s.nm_seller, s.nm_email, s.vl_salary, s.dt_birth, dp.* FROM SELLER s JOIN DEPARTMENT dp ON (s.cd_department = dp.cd_department) WHERE cd_Seller = ?;";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    st.setInt(1, id);

	    try (ResultSet rs = st.executeQuery()) {
		if (rs.next())
		    return convert(id, rs);
		else
		    throw new DaoException("Unexpected Error: Cannot select Seller(" + id + ")");
	    }
	} catch (SQLException e) {
	    throw new DaoException("Unexpected Error: " + e.getMessage());
	}
    }

    @Override
    public void insert(Seller obj) {
	String sql = "INSERT INTO SELLER (nm_email, nm_seller, dt_birth, vl_salary, cd_department) VALUES (?, ?, ?, ?, ?);";

	try (Connection con = Database.getConnection();
		PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	    st.setString(1, obj.getEmail());
	    st.setString(2, obj.getName());
	    st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
	    st.setDouble(4, obj.getSalary());
	    st.setInt(5, obj.getDepartment().getId());

	    if (st.executeUpdate() == 0)
		throw new DaoException("Failed to insert Seller: " + obj);

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
    public void update(Seller obj) {
	String sql = "UPDATE SELLER SET nm_seller = ?, nm_email = ?, vl_salary = ?, dt_birth = ?, cd_department = ? WHERE cd_seller = ?;";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    st.setString(1, obj.getName());
	    st.setString(2, obj.getEmail());
	    st.setDouble(3, obj.getSalary());
	    st.setDate(4, new java.sql.Date(obj.getBirthDate().getTime()));
	    st.setInt(5, obj.getDepartment().getId());
	    st.setInt(6, obj.getId());

	    if (st.executeUpdate() == 0)
		throw new DaoException("Unexpected Error: Cannot update " + obj);
	} catch (SQLException e) {
	    throw new DaoException("Unexpected Error: " + e.getMessage());
	}
    }

    @Override
    public List<Seller> findAll() {
	String sql = "SELECT s.*, dp.* FROM SELLER s JOIN DEPARTMENT dp ON (s.cd_department = dp.cd_department)";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    List<Seller> deps = new ArrayList<>();

	    try (ResultSet rs = st.executeQuery()) {
		while (rs.next())
		    deps.add(convert(rs.getInt("cd_seller"), rs));
	    }

	    return deps;
	} catch (SQLException e) {
	    throw new DaoException("Unexpected Error: " + e.getMessage());
	}
    }

    @Override
    public void removeById(Integer id) {
	String sql = "DELETE FROM SELLER WHERE cd_seller = ?;";

	try (Connection con = Database.getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
	    st.setInt(1, id);

	    if (st.executeUpdate() == 0)
		throw new DaoException("Unexpected error: Cannot delete Seller(" + id + ")!");
	} catch (SQLException e) {
	    throw new DaoException("Cannot delete Seller(" + id + "): " + e.getMessage());
	}
    }

    public Map<Integer, Department> getDepartments() {
	return deps;
    }
}
