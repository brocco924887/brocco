package com.webdemo.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webdemo.util.ConnectUtil;

@SuppressWarnings("serial")
public class HelloWorldController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.��ȡ���ݿ�����ݣ�չʾ����ҳ��
		//this.dbmsg2Page(req, resp);
		//2.����ͼƬ·�������ݿ��У���ȡ���ݿ��ͼƬ·����չʾͼƬ����ҳ��
		this.dbimg2Page(req, resp);
	}

	private void dbimg2Page(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		InputStream src = ConnectUtil.queryImg(ConnectUtil.getConnection(), 
				"select * from photo where photo_no = 1");
		resp.setContentType("image/*");
        ImageInputStream iis=   ImageIO.createImageInputStream(src);
        BufferedImage bufferedImage = ImageIO.read(iis);
		ImageOutputStream out =ImageIO.createImageOutputStream(resp.getOutputStream()) ;
		ImageIO.write(bufferedImage, "JPG", out);
		out.flush();
		out.close();
	}

	/**
	 * ��ȡ���ݿ�����ݣ�չʾ����ҳ��
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void dbmsg2Page(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String msg = ConnectUtil.query(ConnectUtil.getConnection(), "select * from demo_topic where lanauge=0");
		// ���������utf8���������ص�����
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		// ����servlet��UTF-8ת��
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello World</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + msg + "</h1>");
		out.println("</body>");
		out.println("</html>");
	}

}
