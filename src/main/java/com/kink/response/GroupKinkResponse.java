package com.kink.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

import com.kink.entity.KinkEntity;
import com.kink.entity.KinksterEntity;
import com.kink.view.KinkBreakdownView;
import com.kink.view.KinkView;
import com.kink.view.KinkWithLevelView;
import com.kink.view.KinksterView;

@Data
public class GroupKinkResponse {

	private String requesterId;
	private List<KinkBreakdownView> kinkBreakdown;

	public static GroupKinkResponse fromCompatibleKinkMap(String requesterId,
			Map<KinksterEntity, List<KinkWithLevelView>> compatibleKinks) {

		GroupKinkResponse groupKinkResponse = new GroupKinkResponse();
		groupKinkResponse.setRequesterId(requesterId);
		List<KinkBreakdownView> map = new ArrayList<>();
		compatibleKinks.entrySet().forEach(
				entry -> {
					KinkBreakdownView v = new KinkBreakdownView();
					v.setKinkster(KinksterView.fromEntityMasked(entry.getKey()));
					v.setCompatibleKinks(entry.getValue().stream().map(k -> k.getKink()).collect(Collectors.toList()));
					map.add(v);
				});
		groupKinkResponse.setKinkBreakdown(map);
		return groupKinkResponse;
	}

}
