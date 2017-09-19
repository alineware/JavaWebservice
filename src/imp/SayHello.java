package imp;

//����Ҫ���ô���
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

//ע��@WebService������
@WebService
public class SayHello {

	// �÷���Ϊ�ͻ��˵��õķ���������������
	public String say(String name) {
		return "Hello " + name + " , this message comes from outsideWS!";
	}

	public String requestMSS() {
		String url = "http://10.64.170.56:11000/yszx/callByInterface.do";
		System.out.println("����MSSURL��" + url);
		String entity = this.doPost(url, "{ID:1}");
		System.out.println("MSS���أ�" + entity);
		return entity;
	}

	/**
	 * ִ��һ��HTTP POST���󣬷���������Ӧ��HTML
	 * 
	 * @param url
	 *            �����URL��ַ
	 * @param params
	 *            ����Ĳ�ѯ����,����Ϊnull
	 * @return ����������Ӧ��HTML
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
