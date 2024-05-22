package model.daos;

public class DaoFactory {
    private static DepartmentDao departments;
    private static SellerDao sellers;

    private DaoFactory() {
    }

    public static DepartmentDao getDepartmentDao() {
	if (departments == null)
	    departments = new DepartmentDao();
	return departments;
    }

    public static SellerDao getSellerDao() {
	if (sellers == null)
	    sellers = new SellerDao();
	return sellers;
    }
}
