package model.daos;

public class DaoFactory {
    private static DepartmentDao departments;

    private DaoFactory() {
    }

    public static DepartmentDao getDepartmentDao() {
	if (departments == null)
	    departments = new DepartmentDao();
	return departments;
    }
}
