package com.forecastGuru.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Base class for model objects. This is basically for the toString method.
 * 
 * @author Martin A. Heras
 */
public class BaseObject implements Serializable {
	private static final long serialVersionUID = 3256446889040622647L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
