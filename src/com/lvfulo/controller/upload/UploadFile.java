package com.lvfulo.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.mar.MarShopdetail;
import com.lvfulo.service.mar.MarService;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

/**
 * Created by shhao. Date: 14-9-1 Time:下午4:32
 */
@Controller
public class UploadFile {
	@Autowired
	MarService marService;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping("springUpload/{shopid}")
	@ResponseBody
	@Transactional
	public ReturnValue springUpload(HttpServletRequest request, @PathVariable String shopid) throws IllegalStateException, IOException {
		try {
			this.getRtv().OnInit();
			String filenameString = "";// 存储到物理地址的文件名
			String returnfilenameString = "";// 访问nginx的文件名
			System.out.println(shopid);
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

					// region 店铺基础信息表保存
					MarShopdetail item = new MarShopdetail();
					item.getSearch().setSearch(" a.shopid = '" + shopid + "'");
					List<MarShopdetail> lists = marService.SearchMarShopdetail(item, rtv);
					if (lists.size() < 0) {
						rtv.setMsg("不存在该店铺！");
						return rtv;
					}

					lists.get(0).setShopavator(returnfilenameString);
					lists.get(0).getDeal().setAction(DataAction.Modify.getAction());
					marService.SaveMarShopdetail(lists.get(0), rtv);

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

}