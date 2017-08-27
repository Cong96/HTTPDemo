package com.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyServlet() {
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
	     //购买了一件物品，将这个物品存入到session中，然后将这个sessionid回写到客户机，有效时间是30分钟  
        HttpSession session = request.getSession();  
        session.setAttribute("store", "air-confication");  
        //默认携带sessionid信息的cookie字段是：JESSIONID
        Cookie cookie = new Cookie("JSESSIONID",session.getId());//把系统的session id的覆盖掉  
     //setMaxAge() 当用户关闭浏览器后，会把存在于浏览器缓存中的cookie写到磁盘中。如果不设置，存在于浏览器缓存中的cookie会在会话关闭后消失，
       cookie.setMaxAge(30*360);  
        cookie.setPath("/HttpDemo");  
        response.addCookie(cookie);  
	}

}
