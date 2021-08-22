package com.huway0310.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomResponseHandler implements ResponseHandler {

	@Override
	public ResponseObject handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		int status = response.getStatusLine().getStatusCode();
        ResponseObject rspObject = new ResponseObject();
        rspObject.setStatusCode(status);
		
        if (status >= 200 && status < 300) {
        	
        	HttpEntity entity = response.getEntity();
        	
        	Header headerEncoding = response.getEntity().getContentEncoding();
        	
        	 Charset enocodedCharset = (headerEncoding == null) ? StandardCharsets.UTF_8 : Charsets.toCharset(headerEncoding.toString());
        	
        	if (entity != null) {
                String retSrc = EntityUtils.toString(entity,enocodedCharset); 
                // parsing JSON
                JSONObject jsonResponse = new JSONObject(retSrc); //Convert String to JSON Object
              
             
                System.out.println(retSrc);
                
                //因為IMGUR_URL回傳資料格式如下
                //{"data":{"id":"ffikQOc","title":null,"description":null,"datetime":1629615210,"type":"image\/jpeg","animated":false,"width":1200,"height":630,"size":16845,"views":0,"bandwidth":0,"vote":null,"favorite":false,"nsfw":null,"section":null,"account_url":null,"account_id":0,"is_ad":false,"in_most_viral":false,"has_sound":false,"tags":[],"ad_type":0,"ad_url":"","edited":"0","in_gallery":false,"deletehash":"YKAXXEqeRhSN5I3","name":"","link":"https:\/\/i.imgur.com\/ffikQOc.jpg"},"success":true,"status":200}
                JSONObject dataObject = (JSONObject) jsonResponse.get("data");
                System.out.println("link is " + (String) dataObject.get("link"));//上傳圖片連結
                System.out.println("response: " + dataObject.toString());
                
                rspObject.setLink((String) dataObject.get("link"));//設到物件的link屬性內
                rspObject.setId((String) dataObject.get("id"));
                rspObject.setImgType((String) dataObject.get("type"));
                
                  
             }
        		
		}
		
        return rspObject;
	}

}
