//author - Martynenko Viktoria
package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Resource;
import model.Client;

import utils.ClientDAO;
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
	private int scanningSeparation;//���������� ����� ���������� ��������
	private int fontSize;//������ ������ ��� ��������
	private int heightOfNameRect;//������ �������� �������������� ��� �������� �������
	private int widthOfNameRect;//������ �������� �������������� ��� �������� �������
	private Graphics2D g;
	private List<Resource> resources;
	private Font f;
	private HashMap<String,Long> hm;
	private HashMap<String,Integer> hmPie;
	private int countRes;
	private int distanceForTimeRect;//���������� �� �������� �������������� �� ��������������  ��� �������
	private int indentForTimeRect;//���������� �� ������ �� ������� ��������������  ��� �������
	private int heightForMarking;//
	private int heightForTimeRect;//������ �������������� ��� �������
	private int widthForTimeRect;//������ �������������� ��� �������
	private int widthOfBigRect;//������ �������� �������������� �� ���������
	private int heightOfBigRect;//������ �������� �������������� �� ���������
	private int fullWidth;//������ ����� �����������
	private int fullHeight;//������ ����� �����������
	private int shiftX;//�������� �� � ��� 3D
	private int shiftY;////�������� �� Y ��� 3D
	private Calendar startDate;
	private Calendar endDate;
	private int widthCircle;
	private int heightCircle;
	private int heightCylinder;
	private int shadow;//�������� �� � ��� ����
	
	public Diagram() {
        super();
		hm=new HashMap<String,Long>();
		hmPie=new HashMap<String,Integer>();
    }
    
    public Diagram(int month,int year) {
        super();
		hm=new HashMap<String,Long>();hmPie=new HashMap<String,Integer>();
		startDate=new GregorianCalendar(year,month,1,0,0,0);
		if(month==11) {
			month=0;
			year++;
		}
		else
			month++;
		endDate=new GregorianCalendar(year,month,1,0,0,0);
    }
   
	public void drawAllResourcesAndFullTime(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
		setParametrsForDiagram();
		BufferedImage image = new BufferedImage(fullWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		drawBackground();//������ ���, ������������� �����
		drawMarkingForAxes();//�������� ��� ��� �������, ��� ������� � ������ (��� ��� ���������)
		drawAxes("��������� �� ��� ����� (� ����)");//������ ��� ���������
		hm=getDiagramData();//�������� ������ ��� ���������
		drawDiagram(86400000);//������ ��������� (� ����)
		g.dispose();
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
		sos.close();
	}
	public void drawAllResourcesForMonth(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
		setParametrsForDiagram();
		BufferedImage image = new BufferedImage(fullWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		drawBackground();//������ ���, ������������� �����
		drawMarkingForAxes();//�������� ��� ��� �������, ��� ������� � ������ (��� ��� ���������)
		drawAxes("��������� �� ��� ����� (� �����)");//������ ��� ���������
		//hm.clear();
		hm=getDiagramDataForMonth();//�������� ������ ��� ���������
		drawDiagram(3600000);//������ ��������� (� �����)
		g.dispose();
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
		sos.close();
	}
	public void drawUserStatistic(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		setParametrsForDiagram();
		origin=16;
		//��������� ������ �����������
		List<Client> clients=ClientDAO.getAllClients();		
		if(clients.size()<2) {
			fullWidth=(clients.size()+2)*origin+clients.size()*widthCircle+widthForNames+shadow;
			fullHeight=heightCircle+2*origin+heightCylinder;
		}
		else {
			int rows;
			if(clients.size()%2==0)
				rows=clients.size()/2;
			else
				rows=1+clients.size()/2;
			fullWidth=5*origin+2*widthCircle+widthForNames+shadow;
			fullHeight=(heightCircle+heightCylinder)*rows+origin*(rows+1);
		}
		//
		BufferedImage image = new BufferedImage(fullWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, fullWidth, fullHeight);
		g.setColor(new Color(214,253,254));
		g.fillRect(fullWidth-widthForNames-origin, origin, widthForNames, fullHeight-2*origin);//��� ��� �������� ��������
		Integer i=0;
		for(Resource r:resources) {
			//�������� ��������
			g.setColor(colorForPieChart()[i]);//�����
			width=fullWidth-origin-widthForNames;
			g.fill3DRect(width, i*heightOfNameRect+scanningSeparation+origin, widthOfNameRect, heightOfNameRect, true);
			i++;
			g.drawString(i.toString()+". "+r.getResource_name(), width+widthOfNameRect+5, (i-1)*heightOfNameRect+scanningSeparation+origin+heightOfNameRect);//�������� ��������
			//
		}
		int i1=0;
		for(Client c:clients) {
			//hmPie.clear();
			hmPie=getDiagramDataForClient(c);
			drawPieChart(c,i1);//������ ���������
			i1++;
		}		
		g.dispose();
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
		sos.close();
	}
	public void setParametrsForDiagram() {//���������� ��� ��������� ��� ���������
    	origin=60;
		width = 400+3*origin;
		height = 400+3*origin;
		marking_size=1;
		widthCircle=250;
		heightCircle=120;
		heightCylinder=40;
		shadow=50;
		//���������� ��� ������������� ������ ����������� 
		scanningSeparation=4;//���������� ����� ���������� ��������
		fontSize=14;//������ ������ ��� ��������
		widthOfNameRect=30;
		widthForNames=widthOfNameRect+13*fontSize;
		heightOfNameRect=fontSize+5;//������ �������� �������������� ��� �������� �������		
		//
		distanceForTimeRect=5;
		indentForTimeRect=5;		
		resources=ResourceDAO.getAllResources();
		countRes=resources.size();
		heightForMarking=(height-3*origin)/countRes;//������ ������ ��� ���������� �� ��� �������
		widthOfBigRect=(width-3*origin)/countRes;//������ �������� �������������� � ���������
		widthForTimeRect=widthOfBigRect;//������ � ������ �������������� ��� �������
		heightForTimeRect=fontSize+2*indentForTimeRect;
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
			m.put(res.getResource_name(), ReservationDAO.getReservedTimeForResource(res));
		}
		return m;
	}
	public HashMap<String,Long> getDiagramDataForMonth(){//������ ��� ���������: ����������������� �����(��� �������) �� �����
		HashMap<String,Long> m=new HashMap<String,Long>();
		List<Resource> resources=ResourceDAO.getAllResources();
		for(Resource res:resources){
			m.put(res.getResource_name(), ReservationDAO.getReservedTimeForResourceByMonth(startDate,endDate,res));			
		}
		return m;
	}
	public HashMap<String,Integer> getDiagramDataForClient(Client c){//������ ��� ���������: ����������������� �����(��� �������) �� �����
		HashMap<String,Integer> m=new HashMap<String,Integer>();
		List<Resource> resources=ResourceDAO.getAllResources();
		Long l=new Long(0);
		double d;
		Integer timeInDays;
		for(Resource res:resources){
			l=ReservationDAO.getReservedTimeByResourceAndClient(res,c);
			d=(double)l/86400000;
			timeInDays=(int)d;
			m.put(res.getResource_name(), timeInDays);			
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
	
	
	public Color[] colorForPieChart(){//������ ������ ��� ���������
		Color[] c=new Color[countRes];
		int red=0,green,blue;
		for(int i=0;i<countRes;i++) {
			red=255-i*30;
			green=0;blue=255-i*55;
			if(red>255)
				red=255;
			if(green>255)
				green=255;
			if(blue>255)
				blue=255;
			if(red<0) {
				red=100;
				blue=255-i*5;
				green=255-i*5;
			}
			if(green<0)
				green=255;
			if(blue<0) {
				blue=255-i*10;
				green=i*20;
			}
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
		for(int i=0;i<=countRes;i++) {
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
		for(Integer i=1;i<=countRes;i++){
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
			Long time=new Long(0);Integer i=0;
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
				//if(heightOfBigRect>0) {
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
					g.drawPolygon(coordX,coordY, 4);//�������������� ������-�������
					coordX[0]=coordX[1];coordY[0]=coordY[1]+heightOfBigRect;
					coordX[3]=coordX[2];coordY[3]=coordY[2]+heightOfBigRect;
					g.setColor(colorForDiagram()[i]);//����� ������� ���������������				
					g.fillPolygon(coordX,coordY, 4);//�������������� ������
					g.setColor(Color.white);
					g.drawPolygon(coordX,coordY, 4);//�������������� ������-�������
					g.setColor(colorForDiagram()[i]);//����� ������� ���������������
					//
				//}				
				//�������� ��������						
				g.fill3DRect(width, i*heightOfNameRect+scanningSeparation+origin, widthOfNameRect, heightOfNameRect, true);
				i++;
				g.drawString(i.toString()+". "+r.getResource_name(), width+widthOfNameRect+5, (i-1)*heightOfNameRect+scanningSeparation+origin+heightOfNameRect);//�������� ��������
				//
			}			
		}		
	}
	public void drawPieChart(Client c,int position) {
		f=new Font("Times New Romane", Font.PLAIN, fontSize);
		g.setFont(f);
		double d=(double)position/2;
		int row=(int)d;
		int column=position%2;
		int sumTime=0;
		for(int time:hmPie.values()) {
			sumTime+=time;
		}
		int[] angles=angle(sumTime);	
		g.setColor(new Color(146,57,242));//���� ����� �������
		g.drawString(c.getLogin(), origin+column*(origin+widthCircle), origin-origin/4+row*(origin+heightCircle+heightCylinder));//��� �������
		
		int countNotNullAngles=0;
		for(int i=0;i<angles.length;i++){
			if(angles[i]!=0)
				countNotNullAngles++;
			if(countNotNullAngles>1)
				break;
		}
		int startAngle=0;
		int x=origin+column*(origin+widthCircle);
		int y=origin+row*(origin+heightCircle+heightCylinder);
		for(int i=0;i<angles.length;i++) {
			g.setColor(new Color(171,182,186));//���� ����
			g.fillArc(x+shadow, y+heightCylinder, widthCircle, heightCircle, startAngle, angles[i]);//����
			if(countNotNullAngles>1){//�������� ������, ���� ���-�� �������� � ��������� ������, ��� 1
				g.setColor(Color.white);				
				g.fillArc(origin+column*(origin+widthCircle)+shadow, origin+row*(origin+heightCircle+heightCylinder)+heightCylinder, widthCircle, heightCircle, startAngle, 4);//���������� ����� ����
			}
			startAngle+=angles[i];
		}
		startAngle=0;
		for(int i=0;i<angles.length;i++) {
			//System.out.println("client "+c.getLogin()+" res "+r.getResource_name()+" angles "+angles[i]);
			g.setColor(colorForPieChart()[i]);//�����
			g.fillArc(x, y, widthCircle, heightCircle, startAngle, angles[i]);//������� ����� ��������
			g.setColor(Color.white);
			if(countNotNullAngles>1){//�������� ������, ���� ���-�� �������� � ��������� ������, ��� 1
				g.fillArc(origin+column*(origin+widthCircle), origin+row*(origin+heightCircle+heightCylinder), widthCircle, heightCircle, startAngle, 4);//���������� ������� ����� ��������
			}			
			startAngle+=angles[i];
		}
		g.fillOval(x+widthCircle/2-10, y+heightCircle/2-5,20,10);//���������� �����
	}
	public int[] angle(int sumTime) {//������ ������� �����, ������������ ����������������� ����� ��� ������� (��� ������-�� �������) 
		int[] angles=new int[resources.size()];
		int i=0;int sum=0;int time; double ang=0;
		for(Resource r:resources) {
			time=hmPie.get(r.getResource_name());
			ang=360*(double)time/(double)sumTime;
			angles[i]=(int)ang;
			sum+=angles[i];
			i++;
		}		
		if((sum<360) &&(sum!=0))
			for(i=0;i<angles.length;i++) {
				if(angles[i]!=0) {
					angles[i]+=360-sum;//���������� �����, ������� ����� �������� ��� ����������
					break;
				}
			}
		return angles;
	}
	/*public Color[] colorForPieChart(){//������ ������ ��� ���������
	Color[] c=new Color[countRes];
	int red=0,green=0,blue=0;
	for(int i=0;i<countRes;i++) {
		if(i==0) {
			red=155;green=1;blue=214;
		}
		else 
			if(i==1) {
				red=0;green=255;blue=4;
			}
			else
				if(i==2) {
					red=18;green=30;blue=254;
				}
				else
					if(i==3) {
						red=245;green=27;blue=223;
					}
					else
						if(i==4) {
							red=219;green=53;blue=82;
						}
						else
							if(i==5) {
								red=220;green=69;blue=84;
							}
							else
								if(i==6) {
									red=47;green=52;blue=242;
								}
								else
									if(i==7) {
										red=35;green=232;blue=237;
									}
									else
										green=30+i*30;blue=i*2;
		if((red>255) ||(red<0))
			red=100;
		if((green>255) ||(green<0))
			green=100;
		if((blue>255) ||(blue<0))
			blue=100;		
		c[i]=new Color(red,green,blue);			
	}
	return c;
}*/
}

