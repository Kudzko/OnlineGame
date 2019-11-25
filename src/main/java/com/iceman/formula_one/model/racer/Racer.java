package com.iceman.formula_one.model.racer;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *  A class that represents a racer with unique identifier
 */
@Getter
@Setter
@EqualsAndHashCode
public class Racer  {
	private String uuid;
	private String nickName;

	public Racer(String nickName) {
		this.uuid = UUID.randomUUID().toString();
		this.nickName = nickName;
	}

}
