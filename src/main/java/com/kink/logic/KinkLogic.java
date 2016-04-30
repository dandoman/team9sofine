package com.kink.logic;

import org.springframework.beans.factory.annotation.Autowired;

import com.kink.dao.KinkDao;

import lombok.Data;

@Data
public class KinkLogic {
	
	@Autowired
	private KinkDao kinkDao;
}
