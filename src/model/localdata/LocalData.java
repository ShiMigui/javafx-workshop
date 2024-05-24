package model.localdata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.interfaces.IEntity;

public class LocalData<E extends IEntity> {
    private Map<Integer, E> map = new HashMap<>();

    protected LocalData() {
    }

    public E get(Integer id) {
	if (id == null)
	    throw new NullPointerException("Id was null!");
	return getMap().get(id);
    }

    public void put(E obj) {
	if (obj == null)
	    throw new NullPointerException("Obj was null!");
	if (obj.getId() == null)
	    throw new NullPointerException("Id was null!");

	map.put(obj.getId(), obj);
    }

    public E remove(Integer id) {
	E obj = get(id);

	if (obj == null)
	    throw new NullPointerException("There is not an object with id = " + id);

	map.remove(id);

	return obj;
    }

    public Collection<E> getAll() {
	return map.values();
    }

    private Map<Integer, E> getMap() {
	return map;
    }
}
