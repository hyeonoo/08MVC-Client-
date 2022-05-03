package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Search;
import com.model2.mvc.service.domain.User;


public class RestHttpClientApp2 {

	public static void main(String[] args) throws Exception {
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////

		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp2.getProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp2.getProductTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http POST 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp2.addProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 2.2 Http POST 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp2.addProductTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 3.1 Http POST 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp2.listProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 3.2 Http POST 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp2.listProductTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 4.1 Http POST 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp2.updateProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 4.2 Http POST 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp2.updateProductTest_Codehaus();	
	}
	
	//1.1  Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getProductTest_JsonSimple() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/getProduct/10015/search";
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		System.out.println(httpResponse);
		System.out.println();
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server에서 받은 Data 확인]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
		public static void getProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= 	"http://127.0.0.1:8080/product/json/getProduct/10015/search";

			// HttpGet : Http Protocol 의 GET 방식 Request
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-Type", "application/json");
			
			// HttpResponse : Http Protocol 응답 Message 추상화
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			//System.out.println("[ Server 에서 받은 Data 확인 ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			Product product = objectMapper.readValue(jsonobj.get("product").toString(), Product.class);
			System.out.println(product);
		}
	
	//2.1 Http Protocol POST Request : JsonSimple 3rd party lib 사용
		public static void addProductTest_JsonSimple() throws Exception{
			
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= 	"http://127.0.0.1:8080/product/json/addProduct";
					
			// HttpGet : Http Protocol 의 POST 방식 Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			JSONObject json = new JSONObject();
			json.put("prodName", "배트");
			json.put("prodDetail", "madeInUSA");
			json.put("manufDay", "2020-05-06");
			json.put("price", "10000");
			json.put("imgFile", "image");
			json.put("regDate", "2022-04-23");
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> 내용읽기(JSON Value 확인)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);

			
		}
//2.2 Http Protocol POST Request : JsonSimple + codehaus 3rd party lib 사용
		public static void addProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= "http://127.0.0.1:8080/product/json/addProduct";

			// HttpPost : Http Protocol 의 POST 방식 Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ 방법 3 : codehaus 사용]
			Product product01 =  new Product();
			product01.setProdName("배트");
			product01.setProdDetail("madeInUSA");
			product01.setManufDay("2020-05-06");
			product01.setPrice(10000);
			product01.setImgFile("image");
			//product01.setRegDate("2022-04-23");
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(product01);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			//System.out.println("[ Server 에서 받은 Data 확인 ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
			 System.out.println(product);
			}
		
		//3.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void listProductTest_JsonSimple() throws Exception{
					
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			

			JSONObject json = new JSONObject();
			
			
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> 내용읽기(JSON Value 확인)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		}
		
		//3.2 Http Protocol POST Request : JsonSimple + codehaus 3rd party lib 사용
		public static void listProductTest_Codehaus() throws Exception{
				
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			Search search = new Search();
			search.setCurrentPage(1);
			
			//[ 방법 3 : codehaus 사용]
			
			
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(search);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj.get("list"));
		
			

		}
		//4.1 Http Protocol POST Request : JsonSimple 3rd party lib 사용
		public static void updateProductTest_JsonSimple() throws Exception{
			
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= 	"http://127.0.0.1:8080/product/json/updateProduct";
					
			// HttpGet : Http Protocol 의 POST 방식 Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			JSONObject json = new JSONObject();
			json.put("prodNo", 10011);
			json.put("prodName", "환타");
			json.put("prodDetail", "파인맛");
			json.put("manufDay", "2022-04-26");
			json.put("price", 2000);
			json.put("imgFile", "없음");
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> 내용읽기(JSON Value 확인)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);

			
		}

		// 4.2 Http Protocol POST Request : JsonSimple + codehaus 3rd party lib 사용
		public static void updateProductTest_Codehaus() throws Exception {

			// HttpClient : Http Protocol 의 client 추상화
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/product/json/updateProduct";

			// HttpPost : Http Protocol 의 POST 방식 Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

			// [ 방법 3 : codehaus 사용]
			Product product01 = new Product();
			product01.setProdNo(10010);
			product01.setProdName("콜라");
			product01.setProdDetail("맛있음");
			product01.setManufDay("2022-04-26");
			product01.setPrice(2000);
			product01.setImgFile("없음");
			ObjectMapper objectMapper01 = new ObjectMapper();
			// Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(product01);

			System.out.println(jsonValue);

			HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);

			// ==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			// ==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();

			// ==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		
			JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
			System.out.println(jsonobj);

			ObjectMapper objectMapper = new ObjectMapper();
			Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
			System.out.println(product);
		}



}
