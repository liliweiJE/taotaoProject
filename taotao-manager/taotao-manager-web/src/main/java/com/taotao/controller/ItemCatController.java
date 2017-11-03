package com.taotao.controller;

import java.util.List;

import org.csource.fastdfs.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.utils.fastdfs.FastDfsUtisl;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList
			(@RequestParam(value="id", defaultValue="0") Long parentId){
		List<EasyUITreeNode> itemCatList = itemCatService.getItemCatList(parentId);
		return itemCatList;
	} 
	
	@RequestMapping("/file")
	@ResponseBody
	public FileInfo getfile(){
		FileInfo file = FastDfsUtisl.getFile("group1", "rBACxVeMTKWASBg7AAl5WLU-YRY088.jpg");
		String sourceIpAddr = file.getSourceIpAddr();
		System.out.println(sourceIpAddr);
	return file;
	}
	
}
