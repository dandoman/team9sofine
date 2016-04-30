package com.kink.view;

import com.kink.Gender;
import com.kink.Orientation;
import com.kink.entity.KinksterEntity;

import lombok.Data;

@Data
public class KinksterView {
	private String id;
	private String groupId;
	private String nickname;
	private Gender gender;
	private Orientation orientation;
	
	public static KinksterView fromEntity(KinksterEntity entity) {
		KinksterView view = new KinksterView();
		view.setGender(entity.getGender());
		view.setGroupId(entity.getGroupId());
		view.setNickname(entity.getNickname());
		view.setOrientation(entity.getOrientation());
		view.setId(entity.getId());
		return view;
	}
	
	public static KinksterView fromEntityMasked(KinksterEntity entity) {
		KinksterView view = new KinksterView();
		view.setGroupId(entity.getGroupId());
		view.setNickname(entity.getNickname());
		view.setId(entity.getId());
		return view;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof KinksterView)) {
			return false;
		}
		KinksterView k = (KinksterView) o;
		
		return this.id.equals(k.id);
	}
}
