package com.kink.request;

import lombok.Data;

import com.kink.Gender;
import com.kink.Orientation;
import com.kink.exception.BadArgsException;

@Data
public class JoinKinksterGroupRequest implements RequestEntity{
	private String nickname;
	private Gender gender;
	private Orientation orientation;
	private String groupId;
	
	@Override
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
		if(groupId == null){
			throw new BadArgsException("Must provide a group id");
		}
	}

}
