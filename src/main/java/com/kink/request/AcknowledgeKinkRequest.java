package com.kink.request;

import com.kink.InterestLevel;
import com.kink.exception.BadArgsException;

import lombok.Data;

@Data
public class AcknowledgeKinkRequest implements RequestEntity{

	private String kinksterId;
	private String kinkId;
	private InterestLevel interest;
	
	@Override
	public void validate() {
		if(kinksterId == null) {
			throw new BadArgsException("Must provide a kinkster id");
		}
		if(kinkId == null) {
			throw new BadArgsException("Must provide a kink id");
		}
		if(interest == null) {
			throw new BadArgsException("Must provide an interest level");
		}
	}

}
