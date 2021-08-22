package com.huway0310.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huway0310.model.FormWrapper;

@Service
public class FormWrapperService {

	public FormWrapper getJson(String formInfo, List<MultipartFile> file) {

		FormWrapper formWrapper = new FormWrapper();

		System.out.println(formInfo);

		JSONObject jsonObject = new JSONObject(formInfo);

		String form1 = jsonObject.getString("form1");// 取得json字串form1內的值

		String form2 = jsonObject.getString("form2");// 取得json字串form內的值

		formWrapper.setForm1(form1);
		
		formWrapper.setForm2(form2);

		int fileCount = file.size();

		formWrapper.setFileCount(fileCount);

		return formWrapper;

	}

}
