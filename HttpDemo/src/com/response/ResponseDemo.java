package com.response;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Servlet implementation class ResponseDemo
 */
@WebServlet("/ResponseDemo")
public class ResponseDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		test1(response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		try {
//			test2(response);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}
	//这个例子很简单就是将字符串写到response容器中，然后客户机从容器中拿取数据进行显示即可，但是这里我们需要注意的，当我们在使用response.getOutputStream()这样获取一个OutputStream流的时候，我们在使用完之后，并不需要手动的去关闭，系统会自动关闭它，如果我们手动去关闭这个流的话，还会引发一些问题。
	//Content-type:这个头就是告诉浏览器以什么方式打开数据，并且指定相应的码表，具体代码如下：
	//上面我们使用字节流来进行书写数据的时候，是没有问题的，因为是将"中国"的字节数据直接写到Response容器中的，所以不会涉及到Response容器编码的问题。
	//所以说当我们在使用字符流写入数据的时候，我们一定要记得修改Response容器的编码，不然会出现乱码的
	//response.setCharacterEncoding("utf-8");  
	//response.setHeader("content-type", "text/html;charset=utf-8");  
	//上面两行代码等同于response.setContentType("text/html;charset=utf-8");  
	//因为在setContentType方法中已经调用了setCharacterEncoding方法设置了Response容器的编码了。
	public void test(HttpServletResponse response) throws IOException{
		OutputStream out=response.getOutputStream();
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		String data="中国";
		out.write(data.getBytes());
	}
	//代码顺序,先给出response.getWriter()是不对的
	public void test1(HttpServletResponse response) throws IOException{
	
		response.setHeader("Content-type", "text/html;charset=utf-8"); 
		
		PrintWriter out=response.getWriter();
		String data="中国执行";
		out.write(data);
		out.close();
	}
	public void test2(HttpServletResponse response) throws Exception{
		ServletContext servletContext=this.getServletContext();
		String path=servletContext.getRealPath("/download/奋斗.png");
		String filename=path.substring(path.lastIndexOf("\\")+1);
		filename=URLEncoder.encode(filename, "UTF-8");
		response.setHeader("content-disposition","attachment;filename="+filename);
		InputStream in=servletContext.getResourceAsStream("/download/奋斗.png");
		OutputStream out=response.getOutputStream();
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=in.read(buffer))!=-1)
		{
			out.write(buffer, 0, len);
		}
	}
}
