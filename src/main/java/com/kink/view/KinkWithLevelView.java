package com.kink.view;

import com.kink.Direction;
import com.kink.InterestLevel;
import com.kink.dto.KinkWithAckDTO;

import lombok.Data;

@Data
public class KinkWithLevelView {
	private KinkView kink;
	private InterestLevel level;
	private Direction direction;
	
	public static KinkWithLevelView fromDTO(KinkWithAckDTO dto) {
		KinkWithLevelView view = new KinkWithLevelView();
		KinkView kView = new KinkView();
		kView.setCategory(dto.getCategory());
		kView.setDescription(dto.getDescription());
		kView.setId(dto.getId());
		kView.setName(dto.getName());
		view.setKink(kView);
		view.setDirection(dto.getDirection());
		view.setLevel(dto.getInterestLevel());
		return view;
	}
}
