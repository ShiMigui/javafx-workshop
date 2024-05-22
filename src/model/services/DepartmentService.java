package model.services;

import model.daos.DaoFactory;
import model.entities.Department;
import model.interfaces.IEntityDao;
import model.interfaces.IEntityService;

public class DepartmentService implements IEntityService<Department> {
    protected DepartmentService() {
    }

    @Override
    public IEntityDao<Department> getDao() {
	return DaoFactory.getDepartmentDao();
    }
}
