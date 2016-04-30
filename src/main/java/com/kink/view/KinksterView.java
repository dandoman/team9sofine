package com.kink.view;

import com.kink.Gender;
import com.kink.Orientation;

import lombok.Data;

@Data
public class KinksterView {
	private String id;
	private String groupId;
	private String nickname;
	private Gender gender;
	private Orientation orientation;
}
