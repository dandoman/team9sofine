package com.kink.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.kink.Direction;
import com.kink.Gender;
import com.kink.InterestLevel;
import com.kink.KinkCategory;
import com.kink.Orientation;
import com.kink.dao.KinkDao;
import com.kink.entity.AcknowledgedKinkEntity;
import com.kink.entity.KinkEntity;
import com.kink.entity.KinksterEntity;
import com.kink.response.KinksterGroupResponse;
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
				kwl.setDirection(acked.getDirection());
				return kwl;
			}).collect(Collectors.toList());
			kinksterMap.put(k, kinks);
		});
		
		Map<String, List<KinkWithLevelView>> myKinks = (kinksterMap.get(kinkster) == null ) ? new HashMap<>() : kinksterMap.get(kinkster).stream().collect(Collectors.groupingBy(x -> x.getKink().getId()));
		kinksterMap.remove(kinkster);
		Map<KinksterEntity, List<KinkWithLevelView>> compatibleKinks = new HashMap<>();
		kinksterMap.entrySet().forEach(entry -> {
			Map<String, List<KinkWithLevelView>> theirKinks = entry.getValue().stream().collect(Collectors.groupingBy(x -> x.getKink().getId()));
			List<KinkWithLevelView> sameKinks = new ArrayList<>();
			myKinks.entrySet().forEach(kinkById -> {
				List<KinkWithLevelView> list = theirKinks.get(kinkById.getKey());
				if(list != null && !list.isEmpty()) {
					KinkWithLevelView myKink = kinkById.getValue().get(0);
					KinkWithLevelView theirKink = list.get(0);
					if(isCompatible(myKink, theirKink)) {
						sameKinks.add(myKink);
					}
				}
			});
			compatibleKinks.put(entry.getKey(), sameKinks);
		});
		
		return compatibleKinks;
	}

	public KinksterEntity createKinksterNewGroup(String nickname, Gender gender,
			Orientation orientation) {
		String id = UUID.randomUUID().toString();
		String groupId = UUID.randomUUID().toString();
		kinkDao.createKinkster(id,nickname,groupId, gender,orientation);
		return kinkDao.getKinksterById(id);
	}
	
	public KinksterEntity createKinksterExistingGroup(String nickname, Gender gender,
			Orientation orientation, String groupId){
		String id = UUID.randomUUID().toString();
		kinkDao.createKinkster(id,nickname,groupId, gender,orientation);
		return kinkDao.getKinksterById(id);
	}

	public void acknowledgeKink(String kinkId, String kinksterId,
			Direction direction, InterestLevel interest) {
		kinkDao.acknowledgeKink(kinkId,kinksterId,direction,interest);
		
	}
	
	private boolean isCompatible(KinkWithLevelView mine, KinkWithLevelView theirs) {
		if(mine.getKink().getId().equals(theirs.getKink().getId())) {
			return isCompatibleDirection(mine.getDirection(), theirs.getDirection()) && isCompatibleInterest(mine.getLevel(), theirs.getLevel());
		}
		return false;
	}

	private boolean isCompatibleInterest(InterestLevel myLevel,
			InterestLevel theirLevel) {
		switch(myLevel) {
		case OPEN:
			return InterestLevel.YES.equals(theirLevel);
		case YES:
			return InterestLevel.YES.equals(theirLevel) || InterestLevel.OPEN.equals(theirLevel);
		case NO:
			return false;
		default:
			return false;
		}
	}

	private boolean isCompatibleDirection(Direction myDirection,
			Direction theirDirection) {
		
		switch (myDirection) {
			case BOTH :
				return true;
			case GIVING :
				if(Direction.BOTH.equals(theirDirection) || Direction.RECEIVING.equals(theirDirection)){
					return true;
				} else {
					return false;
				}
			case RECEIVING : 
				if(Direction.BOTH.equals(theirDirection) || Direction.GIVING.equals(theirDirection)){
					return true;
				} else {
					return false;
				}
			default:
				return false;
		}
	}
}
