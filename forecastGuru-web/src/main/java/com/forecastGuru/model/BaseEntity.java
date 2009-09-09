package com.forecastGuru.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for persisted model objects.
 * 
 * @author Martin A. Heras
 */
@MappedSuperclass
public class BaseEntity extends BaseObject {

	private static final long serialVersionUID = 7073323727620014069L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Gets the identifier.
	 * 
	 * @return the identifier.
	 */
	public long getId() {
		return id;
	}
}
