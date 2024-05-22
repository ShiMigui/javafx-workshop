package model.interfaces;

import java.io.Serializable;

public interface IEntity extends Serializable {
    Integer getId();

    default String normalize(String txt) {
	return txt.trim().replaceAll("\\s{2,}", " ");
    }
}
