package com.groceryApp.entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class WebServiceAccess {
    private static final String TAG = "Database";
    String IP =  "http://localhost:8080/webservices/webapi/";
    public String signUp(String utaId,String firstname,String lastname,String password,String email,String phone,String address,String date){
        String status="begin";
        try{
            URL url = new URL(IP);
            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type",
                    "application/json");
            JSONObject user = new JSONObject();
            user.put("username",utaId);
            user.put("role","customer");
            user.put("firstname",firstname);
            user.put("lastname",lastname);
            user.put("password",password);
            user.put("email",email);
            user.put("phone",phone);
            user.put("address",address);
            user.put("dob",date);
            OutputStream out = conn.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer.write(user.toString());
            writer.close();
            out.close();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response=rd.readLine();
            if (conn.getResponseCode() == 200) {
            	
            	if(response.contains("failed")){
            		status = "User already registered";
            	}else{
                     status ="Registration successful";
            	}
            } else {
                status ="Registration failed";

            }
        }catch (Exception ex){
        }
        return status;
    }
    
    public Map<String,String> getCustomerProfile(String username){
        Map<String,String> map = new HashMap<>();
        try{
            URL url = new URL(IP+"AppUser/customer/"+username);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setAllowUserInteraction(false);
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();
            String response ;
            while((response = rd.readLine())!= null){
                responseStrBuilder.append(response);

                JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                map.put("username",jsonObject.getString("username"));
                map.put("address",jsonObject.getString("address"));
                map.put("dob",jsonObject.getString("dob"));
                map.put("email",jsonObject.getString("email"));
                map.put("firstname",jsonObject.getString("firstname"));
                map.put("lastname",jsonObject.getString("lastname"));
                map.put("phone",jsonObject.getString("phone"));

            }

            int responseCode = conn.getResponseCode();

            return map;
        }catch(Exception ex){
            return null;
        }
    }
    
    public List<Map<String,String>> getItemsList(){
        List<Map<String,String>> list = new ArrayList<>();
        try{
            URL url = new URL(IP+"item/itemList");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/plain");
            int responseCode = conn.getResponseCode();
            //Log.d("ResponseCode",responseCode+"-->");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStrBuilder = new StringBuilder();

            String response;
            
            response=rd.readLine();
           
            	System.out.println(response);
                Map<String,String> map = new HashMap<>();
                JSONArray jsonarray = new JSONArray(response);
                for(int j=0; j<jsonarray.length();j++){
                	 JSONObject jsonObject = jsonarray.getJSONObject(j);
                     map.put("itemName", jsonObject.getString("itemName"));
                     map.put("cost", jsonObject.getString("cost"));
                     map.put("itemId", jsonObject.getString("itemId"));
                     map.put("quantity", jsonObject.getString("quantity"));
                     list.add(map);
                }
               
          
            return list;
        }catch(Exception ex){
            return null;
        }
    }
    public static void main(String[] args) {
		WebServiceAccess w = new WebServiceAccess();
		/*String result = w.signUp("1001357759",
				"surendra",
				"vanteddu",
				"password",
				"surendranaidu04@gmail.com",
				"9090909090",
				"arlington",
				 "08/24/1993");
		System.out.println(result);*/
		//System.out.println(w.getCustomerProfile("q").get("dob"));
		System.out.println(w.getItemsList().get(0).get("itemName"));
	}
}