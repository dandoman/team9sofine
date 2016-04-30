package com.kink.entity;

import java.util.Date;

import lombok.Data;

import com.kink.Gender;
import com.kink.Orientation;

@Data
public class KinksterEntity {
	private String id;
	private String nickname;
	private Gender gender;
	private Orientation orientation;
	private Date createdAt;
}
