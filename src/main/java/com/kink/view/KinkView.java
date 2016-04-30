package com.kink.view;

import com.kink.KinkCategory;
import com.kink.entity.KinkEntity;

import lombok.Data;

@Data
public class KinkView {
	private String id;
	private String name;
	private String description;
	private KinkCategory category;
	
	public static KinkView fromEntity(KinkEntity entity) {
		KinkView view = new KinkView();
		view.setId(entity.getId());
		view.setName(entity.getName());
		view.setCategory(entity.getCategory());
		view.setDescription(entity.getDescription());
		return view;
	}
	
}
