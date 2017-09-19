package imp;

//包不要引用错了
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

//注解@WebService不能少
@WebService
public class SayHello {

	// 该方法为客户端调用的方法，方法名任意
	public String say(String name) {
		return "Hello " + name + " , this message comes from outsideWS!";
	}

	public String requestMSS() {
		String url = "http://10.64.170.56:11000/yszx/callByInterface.do";
		System.out.println("请求MSSURL：" + url);
		String entity = this.doPost(url, "{ID:1}");
		System.out.println("MSS返回：" + entity);
		return entity;
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @return 返回请求响应的HTML
	 */
	private String doPost(String url, String queryString) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new DefaultHttpClient();
			httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
			StringEntity se = new StringEntity(queryString, "utf-8");
			se.setContentType("application/json; charset=utf-8");
			httpPost.setEntity(se);

			HttpResponse response = httpClient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				org.apache.http.HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "utf-8");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (httpPost != null)
				httpPost.abort();
			if (httpClient != null && httpClient.getConnectionManager() != null)
				httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
}
