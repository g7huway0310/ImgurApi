package com.huway0310.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.huway0310.util.CustomResponseHandler;
import com.huway0310.util.ImgUrlResponseObject;

@Service
public class UploadService {

	private static final Logger LOGGER = Logger.getLogger(UploadService.class);

	public static final String IMGUR_URL = "https://api.imgur.com/3/image";// Imgurl_api
	public static final String CLIENT_ID = "c043986de856a08"; // 填入 App 的 Client ID
	public static final String Token = "8018668808c11b87878a2268c4c8b61d04ba4442"; // 填入 token
	public static final String Album = "XXXX"; // 若要指定傳到某個相簿，就填入相簿的 ID

	public ImgUrlResponseObject uploadImage(String base64String) {

		ImgUrlResponseObject responseBody = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();// 使用Apache HttpClient將HTTP Request推送到伺服器
		
		
		
		HttpPost httpPostRequest = new HttpPost(IMGUR_URL);//Post到這個網址
		
		httpPostRequest.setHeader("Authorization", "Client-ID " + CLIENT_ID);// Client-ID後一定要空白

		List<NameValuePair> params = new ArrayList<NameValuePair>();//{KV 配對 參數}
		
		params.add(new BasicNameValuePair("image", base64String));//{imag,base64omfkoasmkdofmasodfmokkosdfkdam}

		
		
		
		// 處理Response
		CustomResponseHandler customResponseHandler = new CustomResponseHandler();

		try { 
			httpPostRequest.setEntity(new UrlEncodedFormEntity(params));//Post帶我們的圖片
		
			
			
			
			
			//1
			CloseableHttpResponse response = httpClient.execute(httpPostRequest);
			//2
			responseBody = customResponseHandler.handleResponse(response);
			
			//httpClient.execute(httpPostRequest, customResponseHandler);=上面1+2
			
	
			LOGGER.info(responseBody.toString());
			
			
			
			int status = responseBody.getStatusCode();
			
			if(status>=200 && status<300){
                
                LOGGER.info("Adding the imgur link for " + responseBody.getLink() + " to completed list.");
            } else {
               
                LOGGER.info("failed");
            }
			httpClient.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseBody;
	}

}
