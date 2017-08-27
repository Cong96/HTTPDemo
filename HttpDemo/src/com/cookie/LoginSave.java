package com.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginSave
 */
@WebServlet("/LoginSave")
public class LoginSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginSave() {
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
//		Cookie[] cookies=request.getCookies();
//		String name = null;
//		String password=null;
//		for(int i=0;i<cookies.length;i++)
//		{
//			if(cookies[i].getName().equals("name"))
//			{
//				name=cookies[i].getValue();
//			}
//			if(cookies[i].getName().equals("password"))
//			{
//				password=cookies[i].getValue();
//			}
//		}
//		if(name!=null&&password!=null)
//		{
//			request.getRequestDispatcher("/welcome.jsp").forward(request, response);
//			return;
//		}
		String isFirst=request.getParameter("flag");
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		//数据库逻辑处理，判断是否正确,如果正确则加入cookies中
		boolean flag=true;
		//如果不正确，跳转到失败页面，return
		//
		
		System.out.println(isFirst);
		System.out.println(password);
		if(flag=true&&isFirst.equals("first"))
		{
		Cookie cookie1=new Cookie("username",name);
		Cookie cookie2=new Cookie("password",password);
		cookie1.setMaxAge(360*60*48);
		cookie2.setMaxAge(360*60*48);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
	
		}
		if(flag=true&&!isFirst.equals("first"))
		{
			request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
		}
		}
	public void test(HttpServletRequest request,HttpServletResponse response){
		
	}
}
