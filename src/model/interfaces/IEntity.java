package model.interfaces;

import java.io.Serializable;

public interface IEntity extends Serializable {
    Integer getId();
    
    String getName();

    default String normalize(String txt) {
	return txt.trim().replaceAll("\\s{2,}", " ");
    }
}
