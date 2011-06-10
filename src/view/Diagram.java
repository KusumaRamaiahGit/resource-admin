package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Reservation;
import model.Resource;

import utils.ReservationDAO;
import utils.ResourceDAO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * Servlet implementation class Diagram
 */
public class Diagram extends HttpServlet {
	private static final long serialVersionUID = 1L;	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Diagram() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public static void drawDiagram(HttpServletResponse response) throws ServletException, IOException {
			
		//
		int origin=40,width = 500+3*origin, height = 500+3*origin,countRes=10,marking_size=1,sizeOfRect=(width-3*origin)/countRes;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0, width, height);
		g.setColor(Color.blue);
		g.drawLine(origin, height-origin,origin, origin);//ось абсцисс
		g.drawLine(origin, height-origin,width-origin, height-origin);//ось ординат
		g.drawLine(width-origin, height-origin, width-origin-2*marking_size, height-origin-marking_size);//стрелка для оси абсцисс
		g.drawLine(width-origin, height-origin, width-origin-2*marking_size, height-origin+marking_size);//стрелка для оси абсцисс
		g.drawLine(origin, origin, origin-marking_size, origin+2*marking_size);//стрелка для оси ординат
		g.drawLine(origin, origin, origin+marking_size, origin+2*marking_size);//стрелка для оси ординат
		g.drawString("Ресурс", width-2*origin, height-origin/2);//надпись для оси абсцисс
		g.drawString("Занятость", 0, origin/2);//надпись для оси ординат
		//разметка для оси абсцисс
		for(int i=1;i<=countRes;i++){
			g.drawLine(i*sizeOfRect+origin, width-marking_size-origin, i*sizeOfRect+origin, width+marking_size-origin);
		}
		g.dispose();
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
	}
	public HashMap<String,Integer> getDiagramData(){
		HashMap<String,Integer> m=new HashMap<String,Integer>();
		List<Resource> resources=ResourceDAO.getAllResources();
		for(Resource res:resources){
			List<Reservation> r=ReservationDAO.getReservationByResource(res);
			m.put(res.getResource_name(), ReservationDAO.getReservedTimeForResource(res));
		}		
		return m;		
	}	
}
