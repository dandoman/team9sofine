package com.kink.entity;

import java.util.Date;

import com.kink.KinkCategory;

import lombok.Data;

@Data
public class KinkEntity {
	private String id;
	private String name;
	private String description;
	private KinkCategory category;
	private Date createdAt;
}
