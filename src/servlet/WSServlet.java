package servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.ws.Endpoint;

import imp.SayHello;

@SuppressWarnings("serial")
public class WSServlet extends GenericServlet { 
	private final String OUTURL = "http://10.64.179.116:9090/outser/outfssvr.do";
	private final String INTFURL = "http://10.64.179.116:9090/intf/forOutser.do";
//	private final String OUTURL = "http://localhost:9086/outser/outfssvr.do";
//	private final String INTFURL = "http://localhost:9086/intf/forOutser.do";
        @Override 
        public void init(ServletConfig servletConfig) throws ServletException { 
                super.init(servletConfig); 
                System.out.println("----->>>׼������ģ���ⲿϵͳWebService����"+OUTURL); 
                //����һ��WebService 
                Endpoint.publish(OUTURL, new SayHello()); 
                System.out.println("----->>>�ѳɹ�����ģ���ⲿϵͳWebService����"+OUTURL); 
                System.out.println("----->>>׼������ģ��ӿڲ�WebService����"+INTFURL); 
                //����һ��WebService 
                Endpoint.publish(INTFURL, new SayHello()); 
                System.out.println("----->>>�ѳɹ�����ģ��ӿڲ�WebService����"+INTFURL); 
        } 

        public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException { 
                System.out.println("��Servlet�������κ�ҵ���߼����������ڷ���WS����"); 
        } 
}