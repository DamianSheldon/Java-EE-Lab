package com.smart.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1565378459397716905L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
