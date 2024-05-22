package model.services;

import model.daos.DaoFactory;
import model.entities.Seller;
import model.interfaces.IEntityDao;
import model.interfaces.IEntityService;

public class SellerService implements IEntityService<Seller> {
    protected SellerService() {
    }

    @Override
    public IEntityDao<Seller> getDao() {
	return DaoFactory.getSellerDao();
    }
}
