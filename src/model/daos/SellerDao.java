package model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.database.Database;
import model.entities.Seller;
import model.exceptions.DaoException;
import model.interfaces.IEntityDao;
import model.localdata.LocalDataFactory;
import model.localdata.SellerLocalData;

public class SellerDao implements IEntityDao<Seller> {
    protected SellerDao() {
    }

    @Override
    public Seller convert(Integer id, ResultSet rs) {
	SellerLocalData local = LocalDataFactory.getLocalSellers();
	try {
	    String name = rs.getString("nm_seller");
	    String email = rs.getString("nm_email");
	    Date birth = rs.getDate("dt_birth");
	    Double salary = rs.getDouble("vl_salary");
	    Integer departmentId = rs.getInt("cd_department");

	    Seller obj = local.get(id);
	    if (obj == null) {
		obj = new Seller();
		obj.setId(id);
		local.put(obj);
	    }

	    obj.setName(name);
	    obj.setEmail(email);
	    obj.setBirthDate(birth);
	    obj.setSalary(salary);
	    obj.setDepartment(DaoFactory.getDepartmentDao().convert(departmentId, rs));

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

	    LocalDataFactory.getLocalSellers().remove(id);
	} catch (SQLException e) {
	    throw new DaoException("Cannot delete Seller(" + id + "): " + e.getMessage());
	}
    }
}
