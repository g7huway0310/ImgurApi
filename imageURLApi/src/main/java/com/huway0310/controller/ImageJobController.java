package com.huway0310.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.huway0310.model.FormWrapper;
import com.huway0310.service.FormWrapperService;
import com.huway0310.service.UploadService;
import com.huway0310.util.MutiFileToBase64;
import com.huway0310.util.ImgUrlResponseObject;

@RestController
public class ImageJobController {

	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private FormWrapperService formWrapperService;

	@PostMapping(value = "/upload", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public FormWrapper upload(@RequestPart("formWrpper") String aFormWrapper, @RequestPart("file") List<MultipartFile> file) {

		System.out.println(aFormWrapper);
		
		FormWrapper formWrapper=new FormWrapper();
		
		ArrayList<ImgUrlResponseObject> imagArrayList=new ArrayList<>();
		
		if (!file.isEmpty()) {
			
			for (MultipartFile multipartFile : file) {
				String Base64Img;
				try {
					 Base64Img = MutiFileToBase64.fileToBase64(multipartFile);
					
					 ImgUrlResponseObject uploadImageInfo = uploadService.uploadImage(Base64Img);//得到圖片url
					
					 formWrapper = formWrapperService.getJson(aFormWrapper,file);
					 
					 System.out.println("得到圖片url"+uploadImageInfo.getLink());
					
					 imagArrayList.add(uploadImageInfo);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//轉乘base64圖片
				
			}
			
			formWrapper.setResponseObjects(imagArrayList);
			
			
		}else {
			
			System.out.println("空的mutilfile");
		}
		
		
		return formWrapper;
	
	
	}

}
