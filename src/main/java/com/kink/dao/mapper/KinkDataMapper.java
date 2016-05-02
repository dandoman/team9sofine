package com.kink.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kink.entity.AcknowledgedKinkEntity;
import com.kink.entity.KinkEntity;
import com.kink.entity.KinksterEntity;

public interface KinkDataMapper {

	@Insert("INSERT INTO kinks(id,name,description,category) VALUES (#{id},#{name},#{description},#{category})")
	public int createKink(@Param("id") String id, @Param("name") String name,
			@Param("description") String description,
			@Param("category") String category);

	@Insert("INSERT INTO kinksters(id,nickname,group_id,gender,orientation) VALUES (#{id},#{nickname},#{groupId},#{gender},#{orientation})")
	public int createKinkster(@Param("id") String id,
			@Param("nickname") String nickname,
			@Param("groupId") String groupId, @Param("gender") String gender,
			@Param("orientation") String orientation);
	
	@Insert("INSERT INTO acknowledged_kinks(kink_id,kinkster_id,interest_level,direction) VALUES (#{kinkId},#{kinksterId},#{interest},#{direction})")
	public int acknowledgeKink(@Param("kinkId") String kinkId, @Param("kinksterId") String kinksterId,
			@Param("interest") String interest,
			@Param("direction") String direction);

	@Select("SELECT id,nickname,group_id as groupId, gender, orientation, created_at as createdAt FROM kinksters WHERE id = #{id}")
	public List<KinksterEntity> getKinksterById(@Param("id") String id);

	@Select("SELECT id,name,description, category, created_at as createdAt FROM kinks WHERE id = #{id}")
	public List<KinkEntity> getKinkById(@Param("id") String id);

	@Select("SELECT kink_id as kinkId, kinkster_id as kinksterId, interest_level as interest, direction, created_at as createdAt FROM acknowledged_kinks WHERE kinkster_id IN (SELECT id FROM kinksters WHERE group_id = #{id})")
	public List<AcknowledgedKinkEntity> getAckedKinksByGroupId(
			@Param("id") String id);
	
	@Select("SELECT id,name,description, category, created_at as createdAt FROM kinks ORDER BY name asc, category asc LIMIT 10 OFFSET #{offset}")
	public List<KinkEntity> getKinkByPage(int offset);
}
