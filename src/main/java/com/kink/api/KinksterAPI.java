package com.kink.api;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kink.entity.KinkEntity;
import com.kink.entity.KinksterEntity;
import com.kink.logic.KinkLogic;
import com.kink.request.AcknowledgeKinkRequest;
import com.kink.request.JoinKinksterGroupRequest;
import com.kink.request.CreateKinksterGroupRequest;
import com.kink.response.GroupKinkResponse;
import com.kink.response.JoinGroupResponse;
import com.kink.response.KinksterGroupResponse;
import com.kink.view.KinkWithLevelView;
import com.kink.view.KinksterView;

@Controller
@Log4j
@RequestMapping("/api/kinkster")
public class KinksterAPI {
	
	@Autowired
	@Setter
	private KinkLogic kinkLogic; 
	
	@ApiOperation(value = "createGroup")
	@RequestMapping(value = "/create-group", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public KinksterGroupResponse createGroup(@RequestBody CreateKinksterGroupRequest r){
		r.validate();
		log.info("Create group request: " + r);
		KinksterEntity k = kinkLogic.createKinkster(r.getNickname(),r.getGender(),r.getOrientation());
		KinksterGroupResponse resp = new KinksterGroupResponse();
		resp.setGroupId(k.getGroupId());
		resp.setKinkster(KinksterView.fromEntity(k));
		return resp;
	}
	
	@ApiOperation(value = "joinGroup")
	@RequestMapping(value = "/join-group", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public JoinGroupResponse joinGroup(@RequestBody JoinKinksterGroupRequest r){
		r.validate();
		log.info("Join group request: " + r);
		return null;
	}
	
	@ApiOperation(value = "acknowledgeKink")
	@RequestMapping(value = "/acknowledge-kink", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public void acknowledgeKink(@RequestBody AcknowledgeKinkRequest r){
		r.validate();
		log.info("Acknowledge kink request: " + r);
	}
	
	@ApiOperation(value = "getGroupKinks")
	@RequestMapping(value = "/{id}/group-kinks", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public GroupKinkResponse getGroupKinks(@PathParam(value = "id") String kinksterId){
		Map<KinksterEntity, List<KinkWithLevelView>> compatibleKinks = kinkLogic.computeCompatibleKinks(kinksterId);
		return GroupKinkResponse.fromCompatibleKinkMap(kinksterId, compatibleKinks);
	}
}
