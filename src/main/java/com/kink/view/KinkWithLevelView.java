package com.kink.view;

import com.kink.Direction;
import com.kink.InterestLevel;

import lombok.Data;

@Data
public class KinkWithLevelView {
	private KinkView kink;
	private InterestLevel level;
	private Direction direction;
}
