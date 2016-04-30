package com.kink.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kink.Gender;
import com.kink.Orientation;
import com.kink.exception.BadArgsException;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CreateKinksterGroupRequest implements RequestEntity {
	private String nickname;
	private Gender gender;
	private Orientation orientation;
	
	public void validate() {
		if(nickname == null){
			throw new BadArgsException("Must provide a nickname");
		}
		if(gender == null){
			throw new BadArgsException("Must provide a gender");
		}
		if(orientation == null){
			throw new BadArgsException("Must provide an orientation");
		}
	}
}
