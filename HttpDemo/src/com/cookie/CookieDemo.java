package com.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDemo
 */
@WebServlet("/CookieDemo")
public class CookieDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		test(request,response);
	}
	public void test(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out=response.getWriter();
		out.write("上一次的访问时间是：");
		Cookie [] cookies=request.getCookies();
		for(int i=0;i<cookies.length&&cookies!=null;i++)
		{
			if(cookies[i].getName().equals("last"))
			{
				 long cookieValue = Long.parseLong(cookies[i].getValue());  
	                Date date = new Date(cookieValue);  
	                out.print(date.toLocaleString());  
			}
		}
		Cookie cookie=new Cookie("last",System.currentTimeMillis()+"");
	cookie.setMaxAge(360*30);
		//cookie.setPath("/HttpDemo");
		//将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
		response.addCookie(cookie);
	}

}
