package com.kink.dto;

import com.kink.Direction;
import com.kink.InterestLevel;
import com.kink.KinkCategory;

import lombok.Data;

@Data
public class KinkWithAckDTO {
	private String id;
	private String name;
	private KinkCategory category;
	private String description;
	private InterestLevel interestLevel;
	private Direction direction;
}
