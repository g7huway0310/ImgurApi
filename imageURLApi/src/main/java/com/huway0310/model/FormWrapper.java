package com.huway0310.model;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huway0310.util.ImgUrlResponseObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//前端VO
@ApiModel("模擬Form表單")
public class FormWrapper {
	
	
	
	
	

	@ApiModelProperty("表單1")
	private String form1;
	
	@ApiModelProperty("表單2")
	private String form2;
	
	@ApiModelProperty("上傳圖片資訊")
	private ArrayList<ImgUrlResponseObject> responseObjects;//上傳圖片資訊
	
	@ApiModelProperty("檔案數量")
	private Integer fileCount;

	//Test
	//{"form1": "Kuowei","form2" :"API"}
	
	public Integer getFileCount() {
		return fileCount;
	}

	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}

	

	public String getForm1() {
		return form1;
	}

	public void setForm1(String form1) {
		this.form1 = form1;
	}

	public String getForm2() {
		return form2;
	}

	public void setForm2(String form2) {
		this.form2 = form2;
	}

	public ArrayList<ImgUrlResponseObject> getResponseObjects() {
		return responseObjects;
	}

	public void setResponseObjects(ArrayList<ImgUrlResponseObject> responseObjects) {
		this.responseObjects = responseObjects;
	}

	
}
