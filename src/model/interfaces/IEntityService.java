package model.interfaces;

import java.util.List;

public interface IEntityService<E extends IEntity> {
    IEntityDao<E> getDao();

    default E findById(Integer id) {
	if (id == null)
	    throw new NullPointerException("Id was null!");
	return getDao().findById(id);
    }

    default E findById(E obj) {
	if (obj == null)
	    throw new NullPointerException("Entity was null!");
	return this.findById(obj.getId());
    }

    default void insert(E obj) {
	if (obj == null)
	    throw new NullPointerException("Entity was null!");

	getDao().insert(obj);
    }

    default void update(E obj) {
	if (obj == null)
	    throw new NullPointerException("Entity was null!");

	getDao().update(obj);
    }

    default List<E> findAll() {
	return getDao().findAll();
    }

    default void removeById(Integer id) {
	if (id == null)
	    throw new NullPointerException("Id was null!");
	getDao().removeById(id);
    }

    default void remove(E obj) {
	if (obj == null)
	    throw new NullPointerException("Entity was null!");

	this.removeById(obj.getId());
    }

    default void insertOrUpdate(E obj) {
	if (obj.getId() == null)
	    this.insert(obj);
	else
	    this.update(obj);
    }
}
