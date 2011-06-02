package view;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.text.*;

public class StatisticView extends HttpServlet {

    private int yPos = 40, xPos = 40, TEXT_SIZE = 24;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.setProperty("java.awt.headless", "true");
        BufferedImage buffImg = new BufferedImage(250,100,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();        
        g.draw3DRect(10, 10, 30, 30, true);
        g.setFont(new Font("Serif", Font.ITALIC,TEXT_SIZE));
        g.setColor(Color.RED);
        g.drawString("dfdfdfdf",1,1);
        g.drawLine(0, 0, 200, 200);
        //Encodes ands sends the buffered image
        ServletOutputStream sOs = response.getOutputStream();
        response.setContentType("image/jpeg");
        JPEGCodec.createJPEGEncoder(sOs).encode(buffImg);
        sOs.close();
	}

}
