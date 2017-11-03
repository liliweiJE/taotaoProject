package com.taotao.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.contons.TaotaoContons;
import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.fastdfs.FastdfsClient;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService {
	
	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;

	@Override
	public PictureResult uploadFile(MultipartFile file) {
		// TODO Auto-generated method stub
		PictureResult pr=new PictureResult();
		if(file.isEmpty()){
			pr.setError(TaotaoContons.ERROR_CODE_1);
			pr.setMessage(TaotaoContons.FAIL_MESSAGE);
			return pr;
		}
		//FastDfsUtisl.uploadFile(file);
		try {
			//取图片扩展名
			String originalFilename = file.getOriginalFilename();
			//取扩展名不要“.”
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			
			pr.setError(TaotaoContons.ERROR_CODE_0);
			pr.setMessage(TaotaoContons.SUCCESS_MESSAGE);
			
			FastdfsClient fclient=new FastdfsClient("property/fdfs_client.conf");
	        
		    String uploadFileurl = fclient.uploadFile(file.getBytes(), extName);
		    
			pr.setUrl(IMAGE_SERVER_BASE_URL+uploadFileurl);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pr.setError(TaotaoContons.ERROR_CODE_1);
			pr.setMessage(TaotaoContons.FAIL_MESSAGE);
		}
		return pr;
	}
	
}
