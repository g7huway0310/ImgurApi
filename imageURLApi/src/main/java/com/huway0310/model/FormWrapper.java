package com.huway0310.model;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huway0310.util.ImgUrlResponseObject;

//前端VO
public class FormWrapper {

	private String form1;
	
	private String form2;
	
	private ArrayList<ImgUrlResponseObject> responseObjects;//上傳圖片資訊
	
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
