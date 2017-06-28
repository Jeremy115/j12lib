package com.j12lib.controller;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.j12lib.service.VoideoService;

@Controller
@Scope("prototype")
public class BashController {
	
	@Resource
	VoideoService voideoService;
	
	
	@RequestMapping(value="saveVoide.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addVoide(@RequestParam Map<String, Object> map) {
		System.err.println(map);
		voideoService.saveinfo(map);
		return map;
	}
}
