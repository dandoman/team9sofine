package com.kink.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kink.KinkCategory;
import com.kink.exception.BadArgsException;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateKinkRequest implements RequestEntity {
	private KinkCategory category;
	private String name;
	private String description;

	public void validate() {
		if (category == null) {
			throw new BadArgsException("Must provide a kink category");
		}
		if (name == null) {
			throw new BadArgsException("Must provide a name");
		}
		if (description == null) {
			throw new BadArgsException("Must provide a description");
		}
	}
}
