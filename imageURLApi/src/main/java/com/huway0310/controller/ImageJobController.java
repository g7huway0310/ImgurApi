package com.huway0310.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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

import io.swagger.annotations.ApiOperation;

import com.huway0310.util.CustomResponseHandler;
import com.huway0310.util.ImgUrlResponseObject;

@RestController
public class ImageJobController {

	@Autowired
	private UploadService uploadService;

	@Autowired
	private FormWrapperService formWrapperService;

	@ApiOperation("imgUrlApi上傳照片")
	@PostMapping(value = "/upload", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public FormWrapper upload(@RequestPart("formWrpper") String aFormWrapper, @RequestPart("file") List<MultipartFile> files) {

		
		
		
		
		
		System.out.println(aFormWrapper);
		
		FormWrapper formWrapper=new FormWrapper();
		
		ArrayList<ImgUrlResponseObject> imagArrayList=new ArrayList<>();
		
		if (!files.isEmpty()) {
			
			for (MultipartFile multipartFile : files) {
				String Base64Img;
				try {
					
					 Base64Img = MutiFileToBase64.fileToBase64(multipartFile);
					
					 ImgUrlResponseObject uploadImageInfo = uploadService.uploadImage(Base64Img);//得到圖片url
					
					 formWrapper = formWrapperService.getJson(aFormWrapper,files);
					 
					 System.out.println("得到圖片url"+uploadImageInfo.getLink());
					
					 imagArrayList.add(uploadImageInfo);
					 
					 
					 
					 
					 
					 
					 //不轉64
					 File convFile = new File( multipartFile.getOriginalFilename() );
				     FileOutputStream fos = new FileOutputStream( convFile );
				     fos.write( multipartFile.getBytes() );
				     fos.close();
				     
					 MultipartEntityBuilder builder = MultipartEntityBuilder.create();
					 builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
					 
					 FileBody aBody=new FileBody(convFile);	 
					 builder.addPart("image", aBody );
					 builder.addTextBody("type", "file");
					 
					 String IMGUR_URL = "https://api.imgur.com/3/image";// Imgurl_api
				     String CLIENT_ID = "c043986de856a08"; // 填入 App 的 Client ID
					 HttpPost httpPostRequest = new HttpPost(IMGUR_URL);//Post到這個網址
				     httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);// Client-ID後一定要空白

					 HttpEntity entity = builder.build();
					 httpPostRequest.setEntity(entity);
					 

					 CloseableHttpClient httpClient = HttpClientBuilder.create().build();
					 HttpResponse response = httpClient.execute(httpPostRequest);
					 
					 
					 CustomResponseHandler customResponseHandler = new CustomResponseHandler();
					 ImgUrlResponseObject handleResponse = customResponseHandler.handleResponse(response);
					 
					 
					 
				
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
