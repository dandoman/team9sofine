package com.kink.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.kink.InterestLevel;
import com.kink.dao.KinkDao;
import com.kink.entity.AcknowledgedKinkEntity;
import com.kink.entity.KinkEntity;
import com.kink.entity.KinksterEntity;
import com.kink.view.KinkView;
import com.kink.view.KinkWithLevelView;

import lombok.Data;

@Data
public class KinkLogic {
	
	@Autowired
	private KinkDao kinkDao;

	//Can be done way better
	//Absolutely terrible
	public Map<KinksterEntity, List<KinkWithLevelView>> computeCompatibleKinks(
			String kinksterId) {
		KinksterEntity kinkster = kinkDao.getKinksterById(kinksterId);
		List<AcknowledgedKinkEntity> ackedKinks = kinkDao.getAcknowledgedKinksByGroupId(kinkster.getGroupId());
		Map<String, List<AcknowledgedKinkEntity>> collectedAcks = ackedKinks.stream().collect(Collectors.groupingBy(AcknowledgedKinkEntity::getKinksterId));
		Map<KinksterEntity, List<KinkWithLevelView>> kinksterMap = new HashMap<>();
		collectedAcks.entrySet().forEach(entry -> {
			KinksterEntity k = kinkDao.getKinksterById(entry.getKey());
			List<KinkWithLevelView> kinks = entry.getValue().stream().map(acked -> {
				KinkEntity ke = kinkDao.getKinkById(acked.getKinkId());
				KinkWithLevelView kwl = new KinkWithLevelView();
				kwl.setKink(KinkView.fromEntity(ke));
				kwl.setLevel(acked.getInterest());
				return kwl;
			}).collect(Collectors.toList());
			kinksterMap.put(k, kinks);
		});
		
		List<KinkWithLevelView> myKinks = kinksterMap.get(kinkster).stream().filter(k -> InterestLevel.OPEN.equals(k.getLevel()) || InterestLevel.YES.equals(k.getLevel())).collect(Collectors.toList());
		Map<KinksterEntity, List<KinkWithLevelView>> compatibleKinks = new HashMap<>();
		kinksterMap.entrySet().forEach(entry -> {
			List<KinkWithLevelView> localKinks = entry.getValue().stream().filter(k -> InterestLevel.OPEN.equals(k.getLevel()) || InterestLevel.YES.equals(k.getLevel())).collect(Collectors.toList());
			List<KinkWithLevelView> sameKinks = new ArrayList<>();
			for (KinkWithLevelView myKink : myKinks) {
				for (KinkWithLevelView theirKink : localKinks) {
					if(myKink.getKink().getId().equals(theirKink.getKink().getId())) {
						sameKinks.add(theirKink);
					}
				}
			}
			compatibleKinks.put(entry.getKey(), sameKinks);
		});
		
		return compatibleKinks;
	}
}
