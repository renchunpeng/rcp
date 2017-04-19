package com.lvfulo.controller.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.ctm.CtmAddress;
import com.lflweb.entity.mar.MarApl;
import com.lflweb.entity.mar.MarShopdetail;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdImage;
import com.lvfulo.dao.custom.CustomDao;
import com.lvfulo.service.custom.UserService;
import com.lvfulo.service.prd.PrdService;
import com.lvfulo.service.shop.ShopService;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Controller
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	private PrdService prdservice;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomDao customDao;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}
	
	/**
	 * 根据用户编号查询用户店铺入驻情况
	 * @param custid
	 * @return
	 */
	@RequestMapping(value = "/getShopYesOrNo/{custid}")
	@ResponseBody
	public JSONObject GetShopYesOrNo(@PathVariable("custid") String custid) {
		System.out.println("===验证用户是否入驻店铺====");
		this.getRtv().SetValues(false, "", "null", false);
		
		List<MarApl> shopList = shopService.GetShopApl(custid, this.getRtv());
		
		List<MarShopdetail> detailList = new ArrayList<MarShopdetail>();
		//店铺已入驻，查看店铺基础信息
		if(shopList.size()>0) {
			detailList = shopService.GetShopBase(shopList.get(0).getShopid(), this.getRtv());			
		}
		
		if(detailList.size()>0 && detailList.get(0).getAddressid()!= null) {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch("a.addressid = '" + detailList.get(0).getAddressid() + "'");
			List<CtmAddress> itemCtmAddresses = userService.SearchAddress2(itemAddress, this.getRtv());
			if (itemCtmAddresses.size() >= 1) {
				detailList.get(0).setAddress(itemCtmAddresses.get(0));
			}
		}
		
		JSONArray json = new JSONArray();
		json.add(0, MyTools.OutLists(shopList, true));
		json.add(1, MyTools.OutLists(detailList, true));
		
		this.getRtv().setBean(true);
		this.getRtv().setData(json.toString());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * 根据店铺编号查询店铺信息
	 * @param shopid
	 * @return
	 */
	@RequestMapping(value = "/getShopBase/{shopid}")
	@ResponseBody
	public JSONObject GetShopBase(@PathVariable("shopid") String shopid) {
		this.getRtv().SetValues(false, "", "null", false);
		
		List<MarShopdetail> lists = shopService.GetShopBase(shopid, this.getRtv());
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * 店铺注册
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/saveShopRegister", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject SaveShopRegister(@RequestBody String item) {
		this.getRtv().SetValues(false, "", "null", false);
		JSONObject jsonshop = JSONObject.fromObject(item);
		System.out.println("====="+jsonshop);
		
		MarApl itemShop = MyTools.GetOneFromJson(jsonshop.toString(), MarApl.class);
		shopService.SaveShopRegister(itemShop, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}

	/**
	 * 店铺信息维护
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/saveShopBase", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject SaveShopBase(@RequestBody String item) {
		this.getRtv().SetValues(false, "", "null", false);
		JSONObject jsonshop = JSONObject.fromObject(item);
		System.out.println("====="+jsonshop);
		
		MarShopdetail itemShop = MyTools.GetOneFromJson(jsonshop.toString(), MarShopdetail.class);
		shopService.SaveShopBase(itemShop, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	
	/**
	 * @description 店铺订单基础表查询
	 */
	@RequestMapping(value = "/getShopOrder/{shopid}/{page}/{status}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String GetOrdBase(@PathVariable("shopid") String shopid, @PathVariable("page") String page, @PathVariable("status") String status) {
		System.out.println("====店铺订单查询===="+shopid);
		
		this.getRtv().SetValues(false, "", "null", false);
		OrdBase itemBase = new OrdBase();
//		if (status.equals("110")) {
//			itemBase.getSearch().setSearch(" a.shopid = '" + shopid + "' ");
//		} else {
//			//itemBase.getSearch().setSearch(" a.shopid = '" + shopid + "' and a.orderstatus = '" + status + "' ");
//			itemBase.getSearch().setSearch(" a.orderstatus = '" + status + "' ");
//		}

		itemBase.getSearch().setStart(ToolUtils.getstart(page));
		itemBase.getSearch().setEnd(20);

		List<OrdBase> lists = shopService.GetOrdListByShopId(itemBase, this.getRtv());
		
		System.out.println("====店铺订单查询===="+lists.size());

		for (OrdBase ordBase : lists) {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch("a.addressid = '" + ordBase.getAddressid() + "'");
			List<CtmAddress> itemCtmAddresses = customDao.searchaddress(itemAddress);
			if (itemCtmAddresses.size()>=1) {
				ordBase.setCtmAddress(itemCtmAddresses.get(0));
			}			
		}

		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return this.getRtv().toString();
	}

	/**
	 * @description 店铺商品基础表查询
	 */
	@RequestMapping(value = "/getShopPrd/{shopid}/{page}/{status}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String GetPrdBase(@PathVariable("shopid") String shopid, @PathVariable("page") String page, @PathVariable("status") String status) {		
		System.out.println("店铺商品查询"+shopid);
		
		this.getRtv().SetValues(false, "", "null", false);
		PrdBase itemBase = new PrdBase();		
		itemBase.getSearch().setSearch(" a.shopid = '" + shopid + "' and a.prdstatus = '" + status + "' ");		
		itemBase.getSearch().setStart(ToolUtils.getstart(page));
		itemBase.getSearch().setEnd(20);

		List<PrdBase> lists = shopService.GetPrdListByShopId(itemBase, this.getRtv());
		
		// 将商品sku图片取出
					for (PrdBase prdBase : lists) {
						PrdImage itemPrdImage = new PrdImage();
						itemPrdImage.getSearch().setSearch(" a.skuid = '" + prdBase.getSkuid() + "'");
						List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
						prdBase.setPrdImage(prdImages.get(0));
					}
		
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return this.getRtv().toString();
	}
	
	/**
	 * @description 店铺名修改接口，传入的参数为shopid、shopname
	 */
	@RequestMapping(value = "/UpdateShopName", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject UpdateShopName(@RequestBody String item) {
		System.out.println("=====我要修改店铺名=====");
		JSONObject input = JSONObject.fromObject(item);
		String shopid = input.getString("shopid");
		String shopname = input.getString("shopname");
		this.getRtv().SetValues(false, "", "null", false);
		shopService.UpdateShopName(shopid, shopname, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @description 店铺介绍修改接口，传入的参数为shopid、shopdesc
	 */
	@RequestMapping(value = "/UpdateShopDesc", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject UpdateShopDesc(@RequestBody String item) {
		System.out.println("=====我要修改店描述=====");
		JSONObject input = JSONObject.fromObject(item);
		String shopid = input.getString("shopid");
		String shopdesc = input.getString("shopdesc");
		this.getRtv().SetValues(false, "", "null", false);
		shopdesc = ToolUtils.Decode(shopdesc);
		shopService.UpdateShopDesc(shopid, shopdesc, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @description 店铺退货地址修改接口，传入的参数为shopid、addressid
	 */
	@RequestMapping(value = "/UpdateReturnAddress/{shopid}/{addressid}", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject UpdateReturnAddress(@PathVariable("shopid") String shopid, @PathVariable("addressid") String addressid, HttpServletResponse response) {
		this.getRtv().SetValues(false, "", "null", false);
		shopService.UpdateReturnAddress(shopid, addressid, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/*
	 * 商品图片上传
	 */
	@RequestMapping("ShopPrdUpload")
	@ResponseBody
	@Transactional
	public ReturnValue ShopPrdUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		try {
			this.getRtv().OnInit();
			String filenameString = "";// 存储到物理地址的文件名
			String returnfilenameString = "";// 访问nginx的文件名
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						// 判断来自linux还是windows
						boolean flag = MyTools.IsLinux();
						if (flag) {
							String path1 = Consts.uploadpicurl;// 指定存储主文件夹
							String path2 = ToolUtils.GetFmtDate(new Date(), "yyyyMMdd") + "/";// 指定每天分类文件夹
							String path3 = new Date().getTime() + String.valueOf(file.getOriginalFilename());// 文件名加时间戳防止重名
							filenameString = path1 + path2 + path3;// 生成最后文件名
							// 判定文件是否存在，不存在就创建
							File newFile = new File(filenameString);
							if (!newFile.exists()) {
								newFile.mkdirs();
							}
							returnfilenameString = Consts.picurl + path2 + path3;
							file.transferTo(newFile);// 写入空文件
							System.out.println(returnfilenameString);
						} else {
							String path1 = "E:/";// 指定存储主文件夹
							String path2 = ToolUtils.GetFmtDate(new Date(), "yyyyMMdd") + "/";// 指定每天分类文件夹
							String path3 = new Date().getTime() + String.valueOf(file.getOriginalFilename());// 文件名加时间戳防止重名
							filenameString = path1 + path2 + path3;// 生成最后文件名
							// 判定文件是否存在，不存在就创建
							File newFile = new File(filenameString);
							if (!newFile.exists()) {
								newFile.mkdirs();
							}
							returnfilenameString = "E:/" + path2 + path3;
							file.transferTo(newFile);// 写入空文件
							System.out.println(returnfilenameString);
						}

					}
					// endregion
				}

			}
			this.getRtv().setSuccess(true);
			this.getRtv().setMsg(returnfilenameString);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		return this.getRtv();

	}
	
	/**
	 * 店铺商品新增
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/saveShopPrd", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject SaveShopPrd(@RequestBody String item) {
		this.getRtv().SetValues(false, "", "null", false);		
		
		JSONObject jsonPrd = JSONObject.fromObject(item);
		System.out.println("====="+jsonPrd);
		
		PrdBase itemPrd = MyTools.GetOneFromJson(jsonPrd.toString(), PrdBase.class);
	
		shopService.SaveShopPrd(itemPrd, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * 店铺商品修改
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/updateShopPrd", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject UpdateShopPrd(@RequestBody String item) {
		this.getRtv().SetValues(false, "", "null", false);		
		
		JSONObject jsonPrd = JSONObject.fromObject(item);
		System.out.println("====="+jsonPrd);
		
		PrdBase itemPrd = MyTools.GetOneFromJson(jsonPrd.toString(), PrdBase.class);
	
		shopService.UpdateShopPrd(itemPrd, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
}
