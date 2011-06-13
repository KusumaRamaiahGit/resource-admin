//author - Martynenko Viktoria
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StatisticController
 */
public class StatisticController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatisticController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("User") != null) {
			RequestDispatcher dispatch = request
					.getRequestDispatcher("statistic.jsp");
			dispatch.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * BufferedImage image= new BufferedImage(500,500,
		 * BufferedImage.TYPE_INT_RGB); Graphics g=image.getGraphics();
		 * g.setColor(Color.GREEN); g.drawString("dfdfdfdf",1,1);
		 * g.draw3DRect(10, 10, 30, 30, true); FileOutputStream fos=new
		 * FileOutputStream("C:/graph1.jpg"); //FileOutputStream fos=new
		 * FileOutputStream("/img/graph1.jpg"); JPEGImageEncoder encoder=
		 * JPEGCodec.createJPEGEncoder(fos); encoder.encode(image);
		 */
		/*
		 * System.setProperty("java.awt.headless", "true"); BufferedImage
		 * buffImg = new BufferedImage(250,100,BufferedImage.TYPE_INT_RGB);
		 * Graphics2D g = buffImg.createGraphics(); g.draw3DRect(10, 10, 30, 30,
		 * true); g.setFont(new Font("Serif", Font.ITALIC,14));
		 * g.setColor(Color.RED); g.drawString("dfdfdfdf",1,1); g.drawLine(0, 0,
		 * 200, 200); //Encodes ands sends the buffered image
		 * ServletOutputStream sOs = response.getOutputStream();
		 * response.setContentType("image/jpeg");
		 * JPEGCodec.createJPEGEncoder(sOs).encode(buffImg); sOs.close();
		 */
		RequestDispatcher dispatch = request
				.getRequestDispatcher("statistic.jsp");
		dispatch.forward(request, response);

	}

}
