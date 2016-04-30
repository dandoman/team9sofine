package com.kink.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

import com.kink.entity.KinkEntity;
import com.kink.entity.KinksterEntity;
import com.kink.view.KinkView;
import com.kink.view.KinkWithLevelView;
import com.kink.view.KinksterView;

@Data
public class GroupKinkResponse {

	private String requesterId;
	private Map<KinksterView, List<KinkView>> kinkMap;

	public static GroupKinkResponse fromCompatibleKinkMap(String requesterId,
			Map<KinksterEntity, List<KinkWithLevelView>> compatibleKinks) {

		GroupKinkResponse groupKinkResponse = new GroupKinkResponse();
		groupKinkResponse.setRequesterId(requesterId);
		Map<KinksterView, List<KinkView>> map = new HashMap<>();
		compatibleKinks.entrySet().forEach(
				entry -> {
					map.put(KinksterView.fromEntity(entry.getKey()),
							entry.getValue().stream().map(k -> k.getKink())
									.collect(Collectors.toList()));
				});
		groupKinkResponse.setKinkMap(map);
		return groupKinkResponse;
	}

}
