package com.kink.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface KinkDataMapper {
	
	@Insert("INSERT INTO kinks(id,name,description,category) VALUES (#{id},#{name},#{description},#{category})")
	public int createKink(@Param("id" )String id, @Param("name" )String name, @Param("description" )String description,
			@Param("category" )String category);
}
