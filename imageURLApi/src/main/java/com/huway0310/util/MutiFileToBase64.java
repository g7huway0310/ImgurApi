package com.huway0310.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class MutiFileToBase64 {

	private static final Logger LOGGER = Logger.getLogger(MutiFileToBase64.class.getName());


	//將圖片轉乘base字串
	public static String fileToBase64(MultipartFile file) throws IOException {

		InputStream inputStream = file.getInputStream();

		byte[] bytes = IOUtils.toByteArray(inputStream);

		String encoded = java.util.Base64.getEncoder().encodeToString(bytes);
		
		LOGGER.info(encoded);

		return new String(encoded);
	}

}
