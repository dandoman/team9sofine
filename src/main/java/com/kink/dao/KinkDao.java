package com.kink.dao;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.kink.KinkCategory;
import com.kink.entity.KinkEntity;

@AllArgsConstructor
public class KinkDao {

	public void createKink(KinkCategory category, String name, String description) {
		// TODO Auto-generated method stub
		
	}

	public List<KinkEntity> getAllKinks() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}
}
