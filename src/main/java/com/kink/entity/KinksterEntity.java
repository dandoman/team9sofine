package com.kink.entity;

import java.util.Date;

import lombok.Data;

import com.kink.Gender;
import com.kink.Orientation;
import com.kink.view.KinksterView;

@Data
public class KinksterEntity {
	private String id;
	private String nickname;
	private String groupId;
	private Gender gender;
	private Orientation orientation;
	private Date createdAt;
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof KinksterEntity)) {
			return false;
		}
		KinksterEntity k = (KinksterEntity) o;
		
		return this.id.equals(k.id);
	}
}
