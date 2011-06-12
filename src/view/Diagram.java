//author - Martynenko Viktoria
package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Collection;
import java.util.Collections;
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

public class Diagram extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int origin;//���������� �� ��� x � y �� ������ ��������� 
	private int width;//������ ����� ����������� ��� ��������
	private int height;//������ ����� ����������� ��� ��������
	private int marking_size;//����� ��� �������� �� ����
	private int widthForNames;//������ ��� ����� �����������, ���������� �������� ������ � ���������
	//���������� ��� ������������� ������ ����������� 
	private int scanningSeparation;//���������� ����� ���������� ��������
	private int fontSize;//������ ������ ��� ��������
	private int heightOfNameRect;//������ �������� �������������� ��� �������� �������
	private int widthOfNameRect;//������ �������� �������������� ��� �������� �������
	private Graphics2D g;
	private List<Resource> resources;
	private Font f;
	private HashMap<String,Long> hm;
	private int countRes;
	private int distanceForTimeRect;//���������� �� �������� �������������� �� ��������������  ��� �������
	private int indentForTimeRect;//���������� �� ������ �� ������� ��������������  ��� �������
	private int heightForMarking;//
	private int heightForTimeRect;//������ �������������� ��� �������
	private int widthForTimeRect;//������ �������������� ��� �������
	private int widthOfBigRect;//������ �������� �������������� �� ���������
	private int heightOfBigRect;//������ �������� �������������� �� ���������
	private Integer i;//����� �������
	private int fullWidth;//������ ����� �����������
	private int fullHeight;//������ ����� �����������
	private int shiftX;//�������� �� � ��� 3D
	private int shiftY;////�������� �� Y ��� 3D
	
    public Diagram() {
        super();
		hm=new HashMap<String,Long>();
        // TODO Auto-generated constructor stub
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void drawAllResourcesAndFullTime(HttpServletResponse response) throws ServletException, IOException {		
		setParametrsForDiagram();
		BufferedImage image = new BufferedImage(fullWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		drawBackground();//������ ���, ������������� �����
		drawMarkingForAxes();//�������� ��� ��� �������, ��� ������� � ������ (��� ��� ���������)
		drawAxes("��������� �� ��� ����� (� ����)");//������ ��� ���������
		hm=getDiagramData();//�������� ������ ��� ���������
		drawDiagram(1440);//������ ���������, ����� �� 1440	������, ����� �������� ���	
		g.dispose();
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
	}
	public void drawAllResourcesForMonth(HttpServletResponse response,Calendar startDate,Calendar endDate) throws ServletException, IOException {		
		setParametrsForDiagram();
		BufferedImage image = new BufferedImage(fullWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		drawBackground();//������ ���, ������������� �����
		drawMarkingForAxes();//�������� ��� ��� �������, ��� ������� � ������ (��� ��� ���������)
		drawAxes("��������� �� ��� ����� (� �����)");//������ ��� ���������
		hm.clear();
		hm=getDiagramData(startDate,endDate);//�������� ������ ��� ���������
		drawDiagram(60);//������ ���������, ����� �� 60	������, ����� �������� ����	
		g.dispose();
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
	}
	public void setParametrsForDiagram() {//���������� ��� ��������� ��� ���������
    	origin=60;
		width = 400+3*origin;
		height = 400+3*origin;
		marking_size=1;
		//���������� ��� ������������� ������ ����������� 
		scanningSeparation=4;//���������� ����� ���������� ��������
		fontSize=14;//������ ������ ��� ��������
		widthForNames=widthOfNameRect+15*fontSize;
		heightOfNameRect=fontSize+5;//������ �������� �������������� ��� �������� �������
		widthOfNameRect=30;
		//
		distanceForTimeRect=5;
		indentForTimeRect=5;		
		resources=ResourceDAO.getAllResources();
		countRes=resources.size();
		heightForMarking=(height-3*origin)/countRes;//������ ������ ��� ���������� �� ��� �������
		widthOfBigRect=(width-3*origin)/countRes;//������ �������� �������������� � ���������
		widthForTimeRect=widthOfBigRect;//������ � ������ �������������� ��� �������
		heightForTimeRect=fontSize+2*indentForTimeRect;
		i=0;
		//���������� ������ ����������� ��� �������������
		int realHeight=resources.size()*(heightOfNameRect+scanningSeparation)+2*origin;
		if(realHeight>height) {//���� �������� ������ ��������� ��������
			height=realHeight;
		}
		//
		fullWidth=width+widthForNames+origin;
		fullHeight=height+distanceForTimeRect;
		shiftX=10;//�������� �� � ��� 3D
		shiftY=10;////�������� �� Y ��� 3D
    }
	public HashMap<String,Long> getDiagramData(){//������ ��� ���������: ����������������� �����(��� �������)
		HashMap<String,Long> m=new HashMap<String,Long>();
		List<Resource> resources=ResourceDAO.getAllResources();
		for(Resource res:resources){
			//List<Reservation> r=ReservationDAO.getReservationByResource(res);
			m.put(res.getResource_name(), ReservationDAO.getReservedTimeForResource(res));
		}
		return m;
	}
	public HashMap<String,Long> getDiagramData(Calendar startDate,Calendar endDate){//������ ��� ���������: ����������������� �����(��� �������) �� �����
		HashMap<String,Long> m=new HashMap<String,Long>();
		List<Resource> resources=ResourceDAO.getAllResources();
		for(Resource res:resources){
			m.put(res.getResource_name(), ReservationDAO.getReservedTimeForResourceByMonth(startDate,endDate,res));
		}
		return m;
	}
	public double scaleY() {//��������������� �� ��� �������
		Long maxtime=Collections.max(hm.values());//������������ ��������� ���������� (��� ���������������)
		int i=height-3*origin;
		double scale=(double)i/(double)maxtime;
		return scale;
	}
	public Color[] colorForDiagram(){//������ ������ ��� ���������
		Color[] c=new Color[countRes];
		int red=0,green,blue;
		for(int i=0;i<countRes;i++) {
			green=30+i*30;blue=i*2;
			if(red>255)
				red=255;
			if(green>255)
				green=255;
			if(blue>255)
				blue=255;
			c[i]=new Color(red,green,blue);			
		}
		return c;
	}
	public void drawBackground() {
		f=new Font("Times New Romane", Font.PLAIN, fontSize);
		g.setFont(f);
		g.setColor(Color.white);
		g.fillRect(0,0, fullWidth, fullHeight);//��� ��� ����� �����������
		g.setColor(new Color(214,253,254));
		g.fillRect(width, origin, widthForNames, height-2*origin);//��� ��� �������� ��������		
	}
	public void drawMarkingForAxes() {
		//�������� ��� ��� �������, ������ (��� ��� ���������)
		for(i=0;i<=countRes;i++) {
			if(i%2==0)
				g.setColor(new Color(194,253,254));
			else
				g.setColor(new Color(205,253,254));
			g.fillRect(origin, height-origin-(i+1)*heightForMarking, width-2*origin,heightForMarking);			
			g.setColor(Color.blue);
			g.drawLine(origin-marking_size, height-origin-(i+1)*heightForMarking, origin+marking_size, height-origin-(i+1)*heightForMarking);//�������� �� ��� �������
		}
		//�������� ��� ��� �������
		g.setColor(Color.blue);
		for(i=1;i<=countRes;i++){
			g.drawLine(i*widthOfBigRect+origin, height-marking_size-origin, i*widthOfBigRect+origin, width+marking_size-origin);
			g.drawString(i.toString(), i*widthOfBigRect-widthOfBigRect/2+origin, height-origin/2);
		}		
	}
	
	public void drawAxes(String nameOfY) {
		//������ ��� ���������
		g.setColor(Color.blue);
		g.drawLine(origin, height-origin,origin, origin);//��� �������
		g.drawLine(origin, height-origin,width-origin, height-origin);//��� �������
		g.drawLine(width-origin, height-origin, width-origin-2*marking_size, height-origin-marking_size);//������� ��� ��� �������
		g.drawLine(width-origin, height-origin, width-origin-2*marking_size, height-origin+marking_size);//������� ��� ��� �������
		g.drawLine(origin, origin, origin-marking_size, origin+2*marking_size);//������� ��� ��� �������
		g.drawLine(origin, origin, origin+marking_size, origin+2*marking_size);//������� ��� ��� �������
		g.drawString("������", width-3*origin/2, height-origin/2);//������� ��� ��� �������
		g.drawString(nameOfY, 0, origin/2);//������� ��� ��� �������
	}
	public void drawDiagram(double timeIn) {
		//������ ���������
		//hm=getDiagramData();
		if(hm.size()!=0) {
			double scale=scaleY();//��������������� ������������ �� ������������� ���������� �������
			Long time=new Long(0);i=0;
			double d;
			Integer timeInDays;
			for(Resource r:resources) {			
				time=hm.get(r.getResource_name());
				timeInDays=(int)((double)time/timeIn);
				d=(double)time*scale;
				heightOfBigRect=(int)d;
				//
				//�������������� � ������� ��������
				int[] coordX=new int[4];//������ ��������� �� ��� � ��� ��������
				int[] coordY=new int[4];//������ ��������� �� ��� � ��� ��������
				g.setColor(Color.white);
				//draw 3d TimeRect
				g.fill3DRect(i*widthOfBigRect+origin, height-origin-heightOfBigRect-heightForTimeRect-distanceForTimeRect-shiftY, widthForTimeRect, heightForTimeRect,true);
				g.setColor(Color.green);
				g.drawRect(i*widthOfBigRect+origin, height-origin-heightOfBigRect-heightForTimeRect-distanceForTimeRect-shiftY, widthForTimeRect, heightForTimeRect);//������� ��������������
				g.setColor(Color.white);
				coordX[0]=i*widthOfBigRect+origin;coordY[0]=height-origin-heightOfBigRect-heightForTimeRect-distanceForTimeRect-shiftY;
				coordX[1]=coordX[0]+widthOfBigRect;coordY[1]=coordY[0];
				coordX[2]=coordX[1]+shiftX;coordY[2]=coordY[1]-shiftY;
				coordX[3]=coordX[0]+shiftX;coordY[3]=coordY[0]-shiftY;
				g.fillPolygon(coordX,coordY, 4);//�������������� ������
				g.setColor(Color.green);
				g.drawPolygon(coordX,coordY, 4) ;//������� ��������������� ������
				g.setColor(Color.white);
				coordX[0]=coordX[1];coordY[0]=coordY[1]+heightForTimeRect;
				coordX[3]=coordX[2];coordY[3]=coordY[2]+heightForTimeRect;
				g.fillPolygon(coordX,coordY, 4);//�������������� ������
				g.setColor(Color.green);
				g.drawPolygon(coordX,coordY, 4) ;//������� ��������������� ������
				//
				g.setColor(new Color(0,0,150));
				g.drawString(timeInDays.toString(),i*widthOfBigRect+origin+indentForTimeRect, height-origin-heightOfBigRect-heightForTimeRect-distanceForTimeRect-shiftY+fontSize);//��������� ������� �������						
				//
				g.setColor(colorForDiagram()[i]);//����� ������� ���������������			
				//draw 3d BigRect
				g.fill3DRect(i*widthOfBigRect+origin, height-origin-heightOfBigRect, widthOfBigRect, heightOfBigRect,true);//������� �������������� ��� ���������				
				g.setColor(Color.white);
				g.drawRect(i*widthOfBigRect+origin, height-origin-heightOfBigRect, widthOfBigRect, heightOfBigRect);//������� ��������������
				g.setColor(colorForDiagram()[i]);//����� ������� ���������������			
				coordX[0]=i*widthOfBigRect+origin;coordY[0]=height-origin-heightOfBigRect;
				coordX[1]=coordX[0]+widthOfBigRect;coordY[1]=coordY[0];
				coordX[2]=coordX[1]+shiftX;coordY[2]=coordY[1]-shiftY;
				coordX[3]=coordX[0]+shiftX;coordY[3]=coordY[0]-shiftY;
				g.fillPolygon(coordX,coordY, 4);//�������������� ������
				g.setColor(Color.white);
				g.drawPolygon(coordX,coordY, 4);//�������������� ������
				coordX[0]=coordX[1];coordY[0]=coordY[1]+heightOfBigRect;
				coordX[3]=coordX[2];coordY[3]=coordY[2]+heightOfBigRect;
				g.setColor(colorForDiagram()[i]);//����� ������� ���������������
				g.fillPolygon(coordX,coordY, 4);//�������������� ������
				g.setColor(Color.white);
				g.drawPolygon(coordX,coordY, 4);//�������������� ������
				g.setColor(colorForDiagram()[i]);//����� ������� ���������������
				//
				//�������� ��������						
				g.fill3DRect(width, i*heightOfNameRect+scanningSeparation+origin, widthOfNameRect, heightOfNameRect, true);
				i++;
				g.drawString(i.toString()+". "+r.getResource_name(), width+widthOfNameRect+5, (i-1)*heightOfNameRect+scanningSeparation+origin+heightOfNameRect);//�������� ��������
				//
			}			
		}		
	}
	/*public void drawNamesOfResources() {
		int i=0;		
		for(Resource r:resources) {
			g.fill3DRect(width, i*fontSize+scanningSeparation, widthOfNameRect, heightOfNameRect, true);
			g.drawString(r.getResource_name(), width, i*heightOfNameRect+scanningSeparation);//�������� ��������
			i++;
		}
	}*/
	
}
