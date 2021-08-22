package com.huway0310.model;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//前端VO
public class FormWrapper {

	private String form1;
	private String form2;
	private String imageLink;
	
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

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

}
