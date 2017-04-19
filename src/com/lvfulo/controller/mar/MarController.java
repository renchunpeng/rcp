package com.lvfulo.controller.mar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.mar.MarAdv;
import com.lflweb.entity.prd.PrdBigType;
import com.lvfulo.service.mar.MarService;

/** 
 * @author  rcp 
 * @date 创建时间：2017年3月20日 下午1:43:27 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Controller
@RequestMapping("/mar")
public class MarController {
	
	@Autowired
	private MarService marService;
	
	private ReturnValue rtv;
	
	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}
	
	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	@RequestMapping(value = "/adv")
	@ResponseBody
	public ReturnValue SearchMarAdv(){
		this.getRtv().OnInit();
		MarAdv item = new MarAdv();
		List<MarAdv>lists = marService.SearchMarAdv(item, rtv);
		rtv.setData(lists);
		return this.getRtv();
		
	}
	
	@RequestMapping(value = "/bigtype")
	@ResponseBody
	public ReturnValue SearchBigType(){
		this.getRtv().OnInit();
		PrdBigType item = new PrdBigType();
		List<PrdBigType>lists = marService.SearchPrdBigType(item, rtv);
		rtv.setData(lists);
		return this.getRtv();
		
	}


}
