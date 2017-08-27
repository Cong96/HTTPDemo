package com.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 但是这里我们需要注意的，当我们在使用response.getOutputStream()这样获取一个OutputStream流的时候，我们在使用完之后，
 * 并不需要手动的去关闭，系统会自动关闭它，如果我们手动去关闭这个流的话，还会引发一些问题。 Servlet implementation class
 * HttpServletDemo
 */
@WebServlet("/HttpServletDemo")
public class HttpServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public HttpServletDemo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			test1(response);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void test1(HttpServletResponse response) {
		// 设置服务器响应的状态码200表示成功 404表示请求路径错误 500一般是代码错误
		// 302 （临时移动） 服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。
		// 如果客户端发出POST请求后，收到服务端的302状态码，那么不能自动的向新的URI发送重复请求，必须跟用户确认是否该重发，因为第二次POST时，环境可能已经发生变化（嗯，POST方法不是幂等的），POST操作会不符合用户预期。但是，很多浏览器（user
		// agent我描述为浏览器以方便介绍）在这种情况下都会把POST请求变为GET请求。
		// HTTP1.1在介绍302时说，如果客户端发出非GET、HEAD请求后，收到服务端的302状态码，那么就不能自动的向新URI发送重复请求，除非得到用户的确认。（又是-,-）但是，很多浏览器都把302当作303处理了（注意，303是HTTP1.1才加进来的，其实从HTTP1.0进化到HTTP1.1，浏览器什么都没动），它们获取到HTTP响应报文头部的Location字段信息，并发起一个GET请求。
		// http://www.cnblogs.com/cswuyg/p/3871976.html
		response.setStatus(302);
		// 用一个给定的名称和域设置响应头。如果响应头已经被设置，新的值将覆盖当前的值。

		response.setHeader("Location", "/aa/index.html");

	}

	/**
	 * @Title: test2
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param response
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @throws 压缩之前的数据大小和压缩之后的数据大小是6倍
	 *             ，这个效率很高的，这个压缩头字段的作用很大，
	 *             对于大型的网站像新浪，搜狐这样的门户网站，他们的首页很复杂的，所以这里必须要做压缩输出：
	 *             有两个原因：1.压缩数据之后返回给客户机很明显能够提高该页面的访问性能。
	 *             2.能够减少费用(压缩数据少了，出口流量就少了，这样运营商收费就会减少)
	 */
	public void test2(HttpServletResponse response) throws IOException {
		String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		System.out.println("压缩前的数据长度：" + data.length());
		ByteArrayOutputStream bout = new ByteArrayOutputStream();// 底层流
		GZIPOutputStream gout = new GZIPOutputStream(bout);// 包装流一般有缓冲，没有把缓冲区写满，不会写到底层流
		gout.write(data.getBytes());
		gout.close();// 等于刷新操作，将包装流中的信息刷新
		byte[] zip = bout.toByteArray();
		System.out.println("压缩后的数据长度：" + zip.length);
		// 通知浏览器数据采用压缩格式
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Content-Length", zip.length + "");// 表明长度

		response.getOutputStream().write(zip);// 压缩数据写给浏览器
		response.getOutputStream().close();

	}

	public void test3(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type", "image/jpeg");
		InputStream in = this.getServletContext().getResourceAsStream("/1.png");
		int len = 0;
		byte[] buffer = new byte[1024];
		OutputStream out = response.getOutputStream();
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();

	}

	public void test4(HttpServletResponse response) throws IOException {
		// 填充的值为:3;url="http://www.baidu.com"表示3s之后跳转到http://www.baidu.com页面
		// 如果没有http://www.baidu.com的话，只是在该页面进行刷新
		response.setHeader("refresh", "3;url=\"http://www.baidu.com\"");
		// response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-type", "text/html;charset=utf-8");
		String data = "科比布莱恩特";
		// OutputStream out=response.getOutputStream();
		// out.write(data.getBytes());
		// out.close();
		PrintWriter out = response.getWriter();
		out.write(data);
		out.close();
	}

	public void test5(HttpServletResponse response) throws IOException {
		response.setHeader("content-disposition", "attachment;filename=1.png");
		OutputStream out = response.getOutputStream();
		InputStream in = this.getServletContext().getResourceAsStream("/1.png");
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();

	}
}
