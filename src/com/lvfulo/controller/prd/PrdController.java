package com.lvfulo.controller.prd;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.prd.PrdAllSelect;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdBrand;
import com.lflweb.entity.prd.PrdImage;
import com.lflweb.entity.prd.PrdPackage;
import com.lflweb.entity.prd.PrdPackagedetail;
import com.lflweb.entity.prd.PrdPackageimg;
import com.lflweb.entity.prd.PrdSelectMix;
import com.lflweb.entity.prd.PrdSelectMix2;
import com.lflweb.entity.prd.PrdSelectMix3;
import com.lflweb.entity.prd.PrdType;
import com.lflweb.entity.prd.PrdTypeSelect;
import com.lvfulo.service.ord.OrdService;
import com.lvfulo.service.prd.PrdService;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Controller
@RequestMapping("/prd")
public class PrdController {
	@Autowired
	private PrdService prdservice;

	// @Autowired
	// private PrdDao prdDao;

	// @Autowired
	// private OrdDao ordDao;

	@Autowired
	private OrdService ordService;

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
	 * @author rcp
	 * @description 分页查询一类商品基础信息
	 */
	@RequestMapping(value = "/shprdbase/{prdtype}/{page}")
	@ResponseBody
	public JSONObject ShPrdBase(@PathVariable("prdtype") String prdtype, @PathVariable("page") String page) {
		this.getRtv().SetValues(false, "", "null", false);
		if (prdtype.equals("08")) {
			List<PrdPackage> lists = ShPrdPackage(page, this.getRtv());
			for (PrdPackage prdPackage : lists) {
				// 将套餐图片取出
				PrdPackageimg itemPrdPackageimg = new PrdPackageimg();
				itemPrdPackageimg.getSearch().setSearch(" a.packageid = '" + prdPackage.getPackageid() + "'");
				List<PrdPackageimg> itemPrdPackageimgs = prdservice.searchPrdPackageimg(itemPrdPackageimg, this.getRtv());
				prdPackage.setPrdPackageimg(itemPrdPackageimgs.get(0));
			}

			this.getRtv().setBean(true);
			this.getRtv().setData(MyTools.OutLists(lists, true));
			return JSONObject.fromObject(this.getRtv().toString());
		} else {
			// 创建查询和分页条件
			PrdBase itemBase = new PrdBase();
			itemBase.getSearch().setSearch("a.prdtype = '" + prdtype + "'");
			itemBase.getSearch().setStart(ToolUtils.getstart(page));
			itemBase.getSearch().setEnd(Consts.limit);

			List<PrdBase> lists = prdservice.searchPrdBase(itemBase, this.getRtv());

			// 将商品sku图片取出
			for (PrdBase prdBase : lists) {
				PrdImage itemPrdImage = new PrdImage();
				itemPrdImage.getSearch().setSearch(" a.skuid = '" + prdBase.getSkuid() + "'");
				List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
				prdBase.setPrdImage(prdImages.get(0));
			}

			this.getRtv().setBean(true);
			this.getRtv().setData(MyTools.OutLists(lists, true));
			return JSONObject.fromObject(this.getRtv().toString());
		}

	}

	/**
	 * @author rcp
	 * @description 根据prdid查询商品信息,prdid可能是商品id可能是套餐编号，根据prdkind判断
	 * 
	 */
	@RequestMapping(value = "/shprdbasedetail/{prdid}/{prdkind}")
	@ResponseBody
	public ReturnValue ShPrdBaseDetail(@PathVariable String prdid, @PathVariable String prdkind) {
		this.getRtv().OnInit();
		if (prdkind.equals("01")||prdkind.equals("03")||prdkind.equals("04")) {
			// 创建查询和分页条件
			PrdBase itemBase = new PrdBase();
			itemBase.getSearch().setSearch("a.prdid = '" + prdid + "'");
			// 这是查找单个商品，只有一条
			itemBase.getSearch().setStart(0);
			itemBase.getSearch().setEnd(20);
			List<PrdBase> lists = prdservice.searchPrdBase(itemBase, this.getRtv());
			// 将商品sku图片取出
			PrdImage itemPrdImage = new PrdImage();
			itemPrdImage.getSearch().setSearch(" a.skuid = '" + lists.get(0).getSkuid() + "'");
			List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
			lists.get(0).setPrdImage(prdImages.get(0));
			this.getRtv().setData(lists);

		} else if (prdkind.equals("02")) {

			// 首先根据packageid去套餐表中取出主表信息
			PrdPackage itemBase = new PrdPackage();
			itemBase.getSearch().setSearch(" a.packageid = '" + prdid + "'");
			itemBase.getSearch().setStart(0);
			itemBase.getSearch().setEnd(20);
			List<PrdPackage> packagemain = prdservice.searchPrdPackage(itemBase, rtv);// 套餐主表

			// 根据packageid去套餐明细表中取出套餐关联的商品信息
			PrdPackagedetail itemPrdPackagedetail = new PrdPackagedetail();
			itemPrdPackagedetail.getSearch().setSearch(" a.packageid = '" + prdid + "'");
			List<PrdPackagedetail> lists = prdservice.SearchPrdPackagedetail(itemPrdPackagedetail, this.getRtv());

			// 循环套餐关联的商品取出图片信息
			for (PrdPackagedetail prdPackagedetail : lists) {
				PrdBase itemBase2 = new PrdBase();
				itemBase2.getSearch().setSearch("a.prdid = '" + prdPackagedetail.getPrdid() + "'");
				itemBase2.getSearch().setStart(0);
				itemBase2.getSearch().setEnd(1);

				PrdBase prdbaselist = prdservice.searchPrdBase(itemBase2, this.getRtv()).get(0);

				PrdImage itemPrdImage = new PrdImage();
				itemPrdImage.getSearch().setSearch(" a.skuid = '" + prdbaselist.getSkuid() + "'");
				List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
				prdbaselist.setPrdImage(prdImages.get(0));
				prdPackagedetail.setPrdBase(prdbaselist);
			}
			packagemain.get(0).setPrdPackagedetails(lists);
			// 将套餐图片取出
			PrdPackageimg itemPrdPackageimg = new PrdPackageimg();
			itemPrdPackageimg.getSearch().setSearch(" a.packageid = '" + prdid + "'");
			List<PrdPackageimg> itemPrdPackageimgs = prdservice.searchPrdPackageimg(itemPrdPackageimg, this.getRtv());
			packagemain.get(0).setPrdPackageimg(itemPrdPackageimgs.get(0));

			this.getRtv().setData(packagemain);
		}

		return this.getRtv();
	}

	/**
	 * @author rcp
	 * @description 商品类型表查询
	 */
	@RequestMapping(value = "/shprdtype/{item}")
	@ResponseBody
	public JSONObject ShPrdType(@PathVariable("item") String item, HttpServletRequest request) {
		this.getRtv().SetValues(false, "", "null", false);
		PrdType itemBase = MyTools.GetOneFromJson(item, PrdType.class);
		List<PrdType> lists = prdservice.searchPrdType(itemBase, this.getRtv());
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return JSONObject.fromObject(this.getRtv().toString());
	}

	/**
	 * @author rcp
	 * @description 商品品牌表 1.all-查询所有商品 2.根据brandid查询商品品牌信息
	 */

	@RequestMapping(value = "/shprdbrand/{item}")
	@ResponseBody
	public ReturnValue ShPrdBrand(@PathVariable String item) {
		this.getRtv().OnInit();
		List<PrdBrand> lists = new ArrayList<PrdBrand>();// 作为接收参数
		PrdBrand itemPrdBrand = new PrdBrand();// 作为查询条件
		if (item.equals("all")) {
			lists = prdservice.searchPrdBrand(itemPrdBrand, this.getRtv());
			this.getRtv().setData(lists);
		} else {
			itemPrdBrand.getSearch().setSearch(" a.brandid = '" + item + "'");
			lists = prdservice.searchPrdBrand(itemPrdBrand, this.getRtv());
			this.getRtv().setData(lists);
		}
		return this.getRtv();
	}

	/**
	 * @author rcp
	 * @description 商品套餐查询
	 */
	public List<PrdPackage> ShPrdPackage(String page, ReturnValue rtv) {
		PrdPackage itemBase = new PrdPackage();
		itemBase.getSearch().setStart(ToolUtils.getstart(page));
		itemBase.getSearch().setEnd(20);
		List<PrdPackage> lists = prdservice.searchPrdPackage(itemBase, rtv);
		return lists;
	}

	/**
	 * @author rcp
	 * @throws UnsupportedEncodingException
	 * @description 根据keyword查询商品信息
	 * 
	 */
	@RequestMapping(value = "/shprdbasedetail/{keyword}")
	@ResponseBody
	public ReturnValue SearchPrdBase(@PathVariable String keyword) {
		this.getRtv().OnInit();
		// 创建查询和分页条件
		PrdBase itemBase = new PrdBase();
		itemBase.getSearch().setSearch("a.keyword like '%" + keyword + "%'");
		// 这是查找单个商品，只有一条
		itemBase.getSearch().setStart(0);
		itemBase.getSearch().setEnd(100);
		List<PrdBase> lists = prdservice.searchPrdBase(itemBase, this.getRtv());
		// 将商品sku图片取出
		for (PrdBase list : lists) {
			PrdImage itemPrdImage = new PrdImage();
			itemPrdImage.getSearch().setSearch(" a.skuid = '" + list.getSkuid() + "'");
			List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
			list.setPrdImage(prdImages.get(0));
		}
		this.getRtv().setData(lists);

		return this.getRtv();
	}

	/**
	 * @author rcp
	 * @description 整站精选
	 */
	@RequestMapping(value = "/searchallselect")
	@ResponseBody
	public ReturnValue selectAllSelect() {
		this.getRtv().OnInit();
		PrdSelectMix prdSelectMix = new PrdSelectMix();
		// 首先取出整站精选的配置表
		PrdAllSelect item1 = new PrdAllSelect();
		List<PrdAllSelect> lists1 = ordService.searchPrdAllSelect(item1, this.getRtv());
		for (PrdAllSelect prdAllSelect : lists1) {
			if (prdAllSelect.getPrdtype().equals("08")) {
				PrdPackage itemPackage = new PrdPackage();
				itemPackage.getSearch().setSearch(" a.packageid = '" + prdAllSelect.getPrdid() + "'");
				itemPackage.getSearch().setStart(0);
				itemPackage.getSearch().setEnd(1);
				List<PrdPackage> lists = prdservice.searchPrdPackage(itemPackage, rtv);

				for (PrdPackage prdPackage : lists) {
					// 将套餐图片取出
					PrdPackageimg itemPrdPackageimg = new PrdPackageimg();
					itemPrdPackageimg.getSearch().setSearch(" a.packageid = '" + prdPackage.getPackageid() + "'");
					List<PrdPackageimg> itemPrdPackageimgs = prdservice.searchPrdPackageimg(itemPrdPackageimg, this.getRtv());
					prdPackage.setPrdPackageimg(itemPrdPackageimgs.get(0));
				}
				prdSelectMix.getPrdPackages().add(lists.get(0));
			} else {
				// 创建查询和分页条件
				PrdBase itemBase = new PrdBase();
				itemBase.getSearch().setSearch("a.prdid = '" + prdAllSelect.getPrdid() + "'");
				itemBase.getSearch().setStart(0);
				itemBase.getSearch().setEnd(1);

				List<PrdBase> lists = prdservice.searchPrdBase(itemBase, this.getRtv());

				// 将商品sku图片取出
				for (PrdBase prdBase : lists) {
					PrdImage itemPrdImage = new PrdImage();
					itemPrdImage.getSearch().setSearch(" a.skuid = '" + prdBase.getSkuid() + "'");
					List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
					prdBase.setPrdImage(prdImages.get(0));
				}

				prdSelectMix.getPrdBases().add(lists.get(0));
			}
		}
		this.getRtv().setData(prdSelectMix);
		return this.getRtv();
	}

	//region 这是废弃的首页八大类接口
//	/**
//	 * @author rcp
//	 * @description 精挑好货
//	 */
//	@RequestMapping(value = "/searchtypeselect")
//	@ResponseBody
//	public ReturnValue selectTypeSelect() {
//		this.getRtv().OnInit();
//		PrdSelectMix2 prdSelectMix2 = new PrdSelectMix2();
//		// 首先取出整站精选的配置表
//		PrdTypeSelect item1 = new PrdTypeSelect();
//		List<PrdTypeSelect> lists1 = ordService.searchPrdTypeSelect(item1, this.getRtv());
//		for (PrdTypeSelect prdAllSelect : lists1) {
//			if (prdAllSelect.getPrdtype().equals("08")) {
//				PrdPackage itemPackage = new PrdPackage();
//				itemPackage.getSearch().setSearch(" a.packageid = '" + prdAllSelect.getPrdid() + "'");
//				itemPackage.getSearch().setStart(0);
//				itemPackage.getSearch().setEnd(1);
//				List<PrdPackage> lists = prdservice.searchPrdPackage(itemPackage, rtv);
//
//				for (PrdPackage prdPackage : lists) {
//					// 将套餐图片取出
//					PrdPackageimg itemPrdPackageimg = new PrdPackageimg();
//					itemPrdPackageimg.getSearch().setSearch(" a.packageid = '" + prdPackage.getPackageid() + "'");
//					List<PrdPackageimg> itemPrdPackageimgs = prdservice.searchPrdPackageimg(itemPrdPackageimg, this.getRtv());
//					prdPackage.setPrdPackageimg(itemPrdPackageimgs.get(0));
//				}
//				prdSelectMix2.getPrdPackages().add(lists.get(0));
//			} else {
//
//				List<PrdBase> lists = mixBases(prdAllSelect.getPrdid());
//
//				String i = prdAllSelect.getPrdtype();
//
//				switch (i)
//
//				{
//
//					case "01":
//
//						prdSelectMix2.getPrdBases1().add(lists.get(0));
//
//						break;
//
//					case "02":
//
//						prdSelectMix2.getPrdBases2().add(lists.get(0));
//
//						break;
//					case "03":
//
//						prdSelectMix2.getPrdBases3().add(lists.get(0));
//
//						break;
//					case "04":
//
//						prdSelectMix2.getPrdBases4().add(lists.get(0));
//
//						break;
//					case "05":
//
//						prdSelectMix2.getPrdBases5().add(lists.get(0));
//
//						break;
//					case "06":
//
//						prdSelectMix2.getPrdBases6().add(lists.get(0));
//					case "07":
//
//						prdSelectMix2.getPrdBases7().add(lists.get(0));
//
//						break;
//
//					default:
//
//						System.out.println("default");
//
//						break;
//
//				}
//			}
//		}
//		this.getRtv().setData(prdSelectMix2);
//		return this.getRtv();
//	}
	
	//endregion

	
/**
 * @author rcp
 * @description 精挑好货
 */
	@RequestMapping(value = "/searchtypeselect")
	@ResponseBody
	public ReturnValue selectTypeSelect() {
		this.getRtv().OnInit();
		// 首先取出整站精选的配置表
		PrdTypeSelect item1 = new PrdTypeSelect();
		List<PrdTypeSelect> lists1 = ordService.searchPrdTypeSelect(item1, this.getRtv());
		PrdSelectMix3 prdSelectMix3 = new PrdSelectMix3();
		//对数据进行分类
		for (PrdTypeSelect prdAllSelect : lists1) {

			String i = prdAllSelect.getPrdtype();

			switch (i)

			{

				case "01":

					prdSelectMix3.getPrdBases1().add(prdAllSelect);

					break;

				case "02":

					prdSelectMix3.getPrdBases2().add(prdAllSelect);

					break;
				case "03":

					prdSelectMix3.getPrdBases3().add(prdAllSelect);

					break;
				case "04":

					prdSelectMix3.getPrdBases4().add(prdAllSelect);

					break;
				case "05":

					prdSelectMix3.getPrdBases5().add(prdAllSelect);

					break;
				case "06":

					prdSelectMix3.getPrdBases6().add(prdAllSelect);
					
					break;
				case "07":

					prdSelectMix3.getPrdBases7().add(prdAllSelect);

					break;

				default:

					System.out.println("default");

					break;

			}
		
	}
		
		rtv.setSuccess(true);
		rtv.setData(prdSelectMix3);
		return rtv;
	}
	
	public List<PrdBase> mixBases(String prdid) {
		// 创建查询和分页条件
		PrdBase itemBase = new PrdBase();
		itemBase.getSearch().setSearch("a.prdid = '" + prdid + "'");
		itemBase.getSearch().setStart(0);
		itemBase.getSearch().setEnd(1);

		List<PrdBase> lists = prdservice.searchPrdBase(itemBase, this.getRtv());

		// 将商品sku图片取出
		for (PrdBase prdBase : lists) {
			PrdImage itemPrdImage = new PrdImage();
			itemPrdImage.getSearch().setSearch(" a.skuid = '" + prdBase.getSkuid() + "'");
			List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
			prdBase.setPrdImage(prdImages.get(0));
		}

		return lists;
	}

	/**
	 * @author rcp
	 * @description 商品预售查询 ,01查询预售商品，02查询新品上架
	 */
	@RequestMapping(value = "/searchpreprd/{flag}/{page}")
	@ResponseBody
	public ReturnValue SearchPrePrd(@PathVariable String flag,@PathVariable String page) {
		this.getRtv().OnInit();
		// 创建查询和分页条件
		PrdBase itemBase = new PrdBase();
		if (flag.equals("01")) {
			itemBase.getSearch().setSearch("a.prdkind = '04'");
		} else if (flag.equals("02")) {
			String newprddateString = ToolUtils.formatdateString(7);
			itemBase.getSearch().setSearch("a.createdate >= '" + newprddateString + "'");
		}

		// 这是查找单个商品，只有一条
		itemBase.getSearch().setStart(ToolUtils.getstart(page));
		itemBase.getSearch().setEnd(20);
		List<PrdBase> lists = prdservice.searchPrdBase(itemBase, this.getRtv());
		// 将商品sku图片取出
		for (PrdBase list : lists) {
			PrdImage itemPrdImage = new PrdImage();
			itemPrdImage.getSearch().setSearch(" a.skuid = '" + list.getSkuid() + "'");
			List<PrdImage> prdImages = prdservice.searchPrdimage(itemPrdImage, this.getRtv());
			list.setPrdImage(prdImages.get(0));
		}
		this.getRtv().setData(lists);

		return this.getRtv();
	}
}
