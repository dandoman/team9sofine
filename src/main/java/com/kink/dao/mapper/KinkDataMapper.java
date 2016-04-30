package com.kink.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kink.entity.KinksterEntity;

public interface KinkDataMapper {
	
	@Insert("INSERT INTO kinks(id,name,description,category) VALUES (#{id},#{name},#{description},#{category})")
	public int createKink(@Param("id" )String id, @Param("name" )String name, @Param("description" )String description,
			@Param("category" )String category);
	
	@Insert("INSERT INTO kinksters(id,nickname,group_id,gender,orientation) VALUES (#{id},#{nickname},#{groupId},#{gender},#{orientation})")
	public int createKinkster(@Param("id" )String id, @Param("nickname" )String nickname, @Param("groupId" )String groupId,
			@Param("gender" )String gender, @Param("orientation" )String orientation);
	
	@Select("SELECT id,nickname,group_id as groupId, gender, orientation, created_at as createdAt FROM kinksters WHERE id = #{id}")
	public List<KinksterEntity> getKinksterById(@Param("id" )String id);
}
