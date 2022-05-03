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
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////

		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
//		RestHttpClientApp2.getProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp2.getProductTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http POST ��� Request : JsonSimple lib ���
//		RestHttpClientApp2.addProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 2.2 Http POST ��� Request : CodeHaus lib ���
//		RestHttpClientApp2.addProductTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 3.1 Http POST ��� Request : JsonSimple lib ���
//		RestHttpClientApp2.listProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 3.2 Http POST ��� Request : CodeHaus lib ���
//		RestHttpClientApp2.listProductTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 4.1 Http POST ��� Request : JsonSimple lib ���
//		RestHttpClientApp2.updateProductTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 4.2 Http POST ��� Request : CodeHaus lib ���
//		RestHttpClientApp2.updateProductTest_Codehaus();	
	}
	
	//1.1  Http Protocol GET Request : JsonSimple 3rd party lib ���
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
		
		System.out.println("[ Server���� ���� Data Ȯ��]");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
		public static void getProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= 	"http://127.0.0.1:8080/product/json/getProduct/10015/search";

			// HttpGet : Http Protocol �� GET ��� Request
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-Type", "application/json");
			
			// HttpResponse : Http Protocol ���� Message �߻�ȭ
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			Product product = objectMapper.readValue(jsonobj.get("product").toString(), Product.class);
			System.out.println(product);
		}
	
	//2.1 Http Protocol POST Request : JsonSimple 3rd party lib ���
		public static void addProductTest_JsonSimple() throws Exception{
			
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= 	"http://127.0.0.1:8080/product/json/addProduct";
					
			// HttpGet : Http Protocol �� POST ��� Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			JSONObject json = new JSONObject();
			json.put("prodName", "��Ʈ");
			json.put("prodDetail", "madeInUSA");
			json.put("manufDay", "2020-05-06");
			json.put("price", "10000");
			json.put("imgFile", "image");
			json.put("regDate", "2022-04-23");
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> �����б�(JSON Value Ȯ��)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);

			
		}
//2.2 Http Protocol POST Request : JsonSimple + codehaus 3rd party lib ���
		public static void addProductTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= "http://127.0.0.1:8080/product/json/addProduct";

			// HttpPost : Http Protocol �� POST ��� Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ ��� 3 : codehaus ���]
			Product product01 =  new Product();
			product01.setProdName("��Ʈ");
			product01.setProdDetail("madeInUSA");
			product01.setManufDay("2020-05-06");
			product01.setPrice(10000);
			product01.setImgFile("image");
			//product01.setRegDate("2022-04-23");
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(product01);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
			 System.out.println(product);
			}
		
		//3.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
		public static void listProductTest_JsonSimple() throws Exception{
					
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			

			JSONObject json = new JSONObject();
			
			
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> �����б�(JSON Value Ȯ��)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		}
		
		//3.2 Http Protocol POST Request : JsonSimple + codehaus 3rd party lib ���
		public static void listProductTest_Codehaus() throws Exception{
				
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/listProduct";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			Search search = new Search();
			search.setCurrentPage(1);
			
			//[ ��� 3 : codehaus ���]
			
			
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(search);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj.get("list"));
		
			

		}
		//4.1 Http Protocol POST Request : JsonSimple 3rd party lib ���
		public static void updateProductTest_JsonSimple() throws Exception{
			
			HttpClient httpClient = new DefaultHttpClient();
			
			String url= 	"http://127.0.0.1:8080/product/json/updateProduct";
					
			// HttpGet : Http Protocol �� POST ��� Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			JSONObject json = new JSONObject();
			json.put("prodNo", 10011);
			json.put("prodName", "ȯŸ");
			json.put("prodDetail", "���θ�");
			json.put("manufDay", "2022-04-26");
			json.put("price", 2000);
			json.put("imgFile", "����");
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> �����б�(JSON Value Ȯ��)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);

			
		}

		// 4.2 Http Protocol POST Request : JsonSimple + codehaus 3rd party lib ���
		public static void updateProductTest_Codehaus() throws Exception {

			// HttpClient : Http Protocol �� client �߻�ȭ
			HttpClient httpClient = new DefaultHttpClient();

			String url = "http://127.0.0.1:8080/product/json/updateProduct";

			// HttpPost : Http Protocol �� POST ��� Request
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");

			// [ ��� 3 : codehaus ���]
			Product product01 = new Product();
			product01.setProdNo(10010);
			product01.setProdName("�ݶ�");
			product01.setProdDetail("������");
			product01.setManufDay("2022-04-26");
			product01.setPrice(2000);
			product01.setImgFile("����");
			ObjectMapper objectMapper01 = new ObjectMapper();
			// Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(product01);

			System.out.println(jsonValue);

			HttpEntity httpEntity01 = new StringEntity(jsonValue, "utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);

			// ==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			// ==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();

			// ==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		
			JSONObject jsonobj = (JSONObject) JSONValue.parse(br);
			System.out.println(jsonobj);

			ObjectMapper objectMapper = new ObjectMapper();
			Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
			System.out.println(product);
		}



}
