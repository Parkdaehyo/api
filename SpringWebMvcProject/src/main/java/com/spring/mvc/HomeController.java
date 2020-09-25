package com.spring.mvc;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.helpers.IOUtils;
import org.json.JSONObject;
import org.json.XML;
//import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/land",  method = RequestMethod.GET)
	public String land() {
		
		return "ApiTest";
		
	}
	
	//@RequestMapping(value = "/api" , method = RequestMethod.GET )
	//public void apitest(HttpServletRequest request, HttpServletResponse response,
	//@RequestParam String numOfRows, @RequestParam String pageNo) throws IOException
	/*
	@RequestMapping("api")
	@ResponseBody
	public HashMap<String, Object> apitest(@RequestParam(value="api", required=false) String api,
						@RequestParam("numOfRows") String numOfRows, 
						@RequestParam("pageNo") String pageNo,
						HttpServletRequest request, HttpServletResponse response) throws IOException {
		
			//@RequestParam String pageNo
			request.setCharacterEncoding("utf-8");
	        response.setContentType("text/html; charset=utf-8");
	        
	        String addr = "http://apis.data.go.kr/1611000/nsdi/UpUbplfcSttService/attr/getUpUbplfcSttAttrList?ServiceKey=";
	        String serviceKey = "3iOrxz5OIYvrdMICLIAi0nua9VaH6ikWDCE1E%2B2amNNVTI%2B7h2FizOJnzF%2FRQjkrm6x%2FzNOLBGekey07KUwj8A%3D%3D";
	        String parameter = "";
//	        serviceKey = URLEncoder.encode(serviceKey,"utf-8");
	        PrintWriter out = response.getWriter();

	        parameter = parameter + "&" + "numOfRows=10";
	        parameter = parameter + "&" + "pageNo=1";
	        
	        addr = addr + serviceKey + parameter;
	        URL url = new URL(addr);
	        
	        InputStream in = url.openStream(); 
	        
	        ByteArrayOutputStream bos1 = new  ByteArrayOutputStream();
	        IOUtils.copy(in, bos1);
	        in.close();
	        bos1.close();
	        
	        String mbos = bos1.toString("UTF-8");
	        
	        byte[] b = mbos.getBytes("UTF-8");
	        String s = new String(b, "UTF-8");
	        out.println(s);

	        JSONObject json = new JSONObject();
	        json.put("data", s);
	        
	        HashMap<String, Object> resultMap = new HashMap<String, Object>();
	        
	        resultMap.put("resultList", json);
	        
	        return resultMap;
	        
		
	}
	*/
	//////////////////////////////////////////////////////////
	
	   /**
	    * 1. MethodName        : roundInfo
	    * 2. ClassName         : CommonController
	    * 3. Commnet           : 로또 해당회차 정보 조회 by 동행복권 Api
	    * 4. 작성자                       : ApplePie
	    * 5. 작성일                       : 2020. 4. 20. 오후 3:47:12
	    * @return void
	    */
		@ResponseBody
		@RequestMapping(value = "/api", method=RequestMethod.POST)
		public Map<String, Object> apitest(/*@RequestParam(value="api", required=false) String api,*/
							//@RequestParam("cnt") int cnt,
							@RequestParam("numOfRows") String numOfRows, 
							@RequestParam("pageNo") String pageNo,
							HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			Map<String, Object> map = new HashMap<String, Object>();
		
			
			map = apiFunction(numOfRows, pageNo, request, response);
			//map.put("cnt", cnt);
			
			return map;
			
	   }
		
		
		public Map<String, Object> apiFunction(String numOfRows, String pageNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			/*
			@ResponseBody
		    @RequestMapping(value = "/futureBoxListAjax", method=RequestMethod.POST)
		   public Map<String, Object> futureBoxListAjax
		   (HttpServletRequest request,@RequestParam HashMap<String, Object> hashMap, HttpSession httpSession) throws Exception {
			
			*/
	      Map<String, Object> map = new HashMap<String, Object>();
	      
	      
	      try {
	    	  /*
	           URLConnection   httpConn   = null;
	           BufferedReader   brd         = null;
	           PrintStream      pout      = null;
	           
	           StringBuffer sb = new StringBuffer();
	            //String post_msg = sb.append("method=").append("getLottoNumber").append("&drwNo=").append(drwNo).toString();
	            String lottoUrl = "http://apis.data.go.kr/1611000/nsdi/UpUbplfcSttService/attr/getUpUbplfcSttAttrList?ServiceKey=3iOrxz5OIYvrdMICLIAi0nua9VaH6ikWDCE1E%2B2amNNVTI%2B7h2FizOJnzF%2FRQjkrm6x%2FzNOLBGekey07KUwj8A%3D%3D&numOfRows=10&pageNo=1";
	            httpConn = new URL(lottoUrl).openConnection();
	            httpConn.setDoOutput(true);
	            httpConn.setUseCaches(false);
	            pout = new PrintStream (httpConn.getOutputStream(),false,"utf-8");
	            //pout.print(post_msg);
	            pout.flush();
	            
	            brd = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"utf-8"));
	            
	            sb.setLength(0);
	            String tmpStr = null;
	            while( (tmpStr=brd.readLine())!= null ) {
	                sb.append(tmpStr);
	            }
	           String sbString = sb.toString();
	           */
	    	  
	    	  	request.setCharacterEncoding("utf-8");
		        response.setContentType("text/html; charset=utf-8");
		        
		        String addr = "http://apis.data.go.kr/1611000/nsdi/UpUbplfcSttService/attr/getUpUbplfcSttAttrList?ServiceKey=";
		        String serviceKey = "3iOrxz5OIYvrdMICLIAi0nua9VaH6ikWDCE1E%2B2amNNVTI%2B7h2FizOJnzF%2FRQjkrm6x%2FzNOLBGekey07KUwj8A%3D%3D";
		        String parameter = "";
//		        serviceKey = URLEncoder.encode(serviceKey,"utf-8");
		        //PrintWriter out = response.getWriter();

		        parameter = parameter + "&" + "numOfRows=10";
		        parameter = parameter + "&" + "pageNo=1";
		        
		        addr = addr + serviceKey + parameter;
		        URL url = new URL(addr);
		        
		        InputStream in = url.openStream(); 
		        
		        ByteArrayOutputStream bos1 = new  ByteArrayOutputStream();
		        IOUtils.copy(in, bos1);
		        in.close();
		        bos1.close();
		        
		        String xmlString = bos1.toString("UTF-8");
		        
		       JSONObject json = XML.toJSONObject(xmlString);
		       
		       String jsonStr = String.valueOf(json);
		       
		       int cnt = 0;
			
		       cnt++;
		       
		       map = new ObjectMapper().readValue(jsonStr, HashMap.class);
		       
		       //
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return map;
			
		}
		
	   
	   
	   /////////////////////////////////////////////////////////////
	
}
