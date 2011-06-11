//author - Martynenko Viktoria
package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
	private int origin;//расстояние по оси x и y до начала координат 
	private int width;//ширина части изображения без названий
	private int height;//высота части изображения без названий
	private int marking_size;//длина для разметки на осях
	private int widthForNames;//ширина для части изображения, включающей названия данных в диаграмме
	//переменные для регулирования высоты изображения 
	private int scanningSeparation;//расстояние между названиями ресурсов
	private int fontSize;//размер шрифта для названия
	private int heightOfNameRect;//высота цветного прямоугольника для названия ресурса
	private int widthOfNameRect;//ширина цветного прямоугольника для названия ресурса
	private Graphics2D g;
	private List<Resource> resources;
	private Font f;
	private HashMap<String,Long> hm;
	private int countRes;
	private int distanceForTimeRect;//расстояние от большого прямоугольника до прямоугольника  для времени
	private int indentForTimeRect;//расстояние от строки до границы прямоугольника  для времени
	private int heightForMarking;//
	private int heightForTimeRect;//высота прямоугольника для времени
	private int widthForTimeRect;//ширина прямоугольника для времени
	private int widthOfBigRect;//ширина большого прямоугольника из диаграммы
	private int heightOfBigRect;//высота большого прямоугольника из диаграммы
	private Integer i;//номер ресурса
	private int fullWidth;//ширина всего изображения
	private int fullHeight;//высота всего изображения
	
    public Diagram() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void drawImage(HttpServletResponse response) throws ServletException, IOException {		
		setParametrsForDiagram();
		BufferedImage image = new BufferedImage(fullWidth, fullHeight, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		drawBackground();//рисуем фон, устанавливаем шрифт
		drawMarkingForAxes();//разметка для оси ординат, оси абсцисс и полосы (фон для диаграммы)
		drawAxes();//рисуем оси координат		
		drawDiagram();//рисуем диаграмму		
		g.dispose();
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
	}
	public void setParametrsForDiagram() {//установить все параметры для диаграммы
    	origin=60;
		width = 400+3*origin;
		height = 400+3*origin;
		marking_size=1;
		//переменные для регулирования высоты изображения 
		scanningSeparation=4;//расстояние между названиями ресурсов
		fontSize=14;//размер шрифта для названия
		widthForNames=widthOfNameRect+15*fontSize;
		heightOfNameRect=fontSize+5;//высота цветного прямоугольника для названия ресурса
		widthOfNameRect=30;
		//
		distanceForTimeRect=5;
		indentForTimeRect=5;		
		resources=ResourceDAO.getAllResources();
		countRes=resources.size();
		heightForMarking=(height-3*origin)/countRes;//высота полосы для маркировки по оси ординат
		widthOfBigRect=(width-3*origin)/countRes;//ширина большого прямоугольника в диаграмме
		widthForTimeRect=widthOfBigRect;//высота и ширина прямоугольника для времени
		heightForTimeRect=fontSize+2*indentForTimeRect;
		i=0;
		//увеличение высоты изображения при необходимости
		int realHeight=resources.size()*(heightOfNameRect+scanningSeparation)+2*origin;
		if(realHeight>height) {//если реальная высота превышает заданную
			height=realHeight;
		}
		//
		fullWidth=width+widthForNames+origin;
		fullHeight=height+distanceForTimeRect;    	
    }
	public HashMap<String,Long> getDiagramData(){//данные для диаграммы: зарезервированное время(имя ресурса)
		HashMap<String,Long> m=new HashMap<String,Long>();
		List<Resource> resources=ResourceDAO.getAllResources();
		for(Resource res:resources){
			List<Reservation> r=ReservationDAO.getReservationByResource(res);
			m.put(res.getResource_name(), ReservationDAO.getReservedTimeForResource(res));
		}		
		return m;		
	}
	public double scaleY() {//масштабирование по оси ординат
		Long maxtime=Collections.max(hm.values());//максимальный временной промежуток (для масштабирования)
		int i=height-3*origin;
		double scale=(double)i/(double)maxtime;
		return scale;
	}
	public Color[] colorForDiagram(){//массив цветов для диаграммы
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
		g.fillRect(0,0, fullWidth, fullHeight);//фон для всего изображения
		g.setColor(new Color(214,253,254));
		g.fillRect(width, origin, widthForNames, height-2*origin);//фон для названий ресурсов		
	}
	public void drawMarkingForAxes() {
		//разметка для оси ординат, полосы (фон для диаграммы)
		for(i=0;i<=countRes;i++) {
			if(i%2==0)
				g.setColor(new Color(194,253,254));
			else
				g.setColor(new Color(205,253,254));
			g.fillRect(origin, height-origin-(i+1)*heightForMarking, width-2*origin,heightForMarking);			
			g.setColor(Color.blue);
			g.drawLine(origin-marking_size, height-origin-(i+1)*heightForMarking, origin+marking_size, height-origin-(i+1)*heightForMarking);//разметка на оси ординат
		}
		//разметка для оси абсцисс
		g.setColor(Color.blue);
		for(i=1;i<=countRes;i++){
			g.drawLine(i*widthOfBigRect+origin, height-marking_size-origin, i*widthOfBigRect+origin, width+marking_size-origin);
			g.drawString(i.toString(), i*widthOfBigRect-widthOfBigRect/2+origin, height-origin/2);
		}		
	}
	
	public void drawAxes() {
		//рисуем оси координат
		g.setColor(Color.blue);
		g.drawLine(origin, height-origin,origin, origin);//ось абсцисс
		g.drawLine(origin, height-origin,width-origin, height-origin);//ось ординат
		g.drawLine(width-origin, height-origin, width-origin-2*marking_size, height-origin-marking_size);//стрелка для оси абсцисс
		g.drawLine(width-origin, height-origin, width-origin-2*marking_size, height-origin+marking_size);//стрелка для оси абсцисс
		g.drawLine(origin, origin, origin-marking_size, origin+2*marking_size);//стрелка для оси ординат
		g.drawLine(origin, origin, origin+marking_size, origin+2*marking_size);//стрелка для оси ординат
		g.drawString("Ресурс", width-3*origin/2, height-origin/2);//надпись для оси абсцисс
		g.drawString("Занятость за все время (в днях)", 0, origin/2);//надпись для оси ординат
	}
	public void drawDiagram() {
		//рисуем диаграмму
		hm=getDiagramData();
		double scale=scaleY();//масштабирование взависимости от максимального промежутка времени
		Long time=new Long(0);i=0;
		double d;
		Integer timeInDays;
		for(Resource r:resources) {			
			time=hm.get(r.getResource_name());
			timeInDays=(int)((double)time/1440);
			d=(double)time*scale;
			heightOfBigRect=(int)d;
			//
			//прямоугольники с занятым временем
			g.setColor(Color.white);
			g.fill3DRect(i*widthOfBigRect+origin, height-origin-heightOfBigRect-heightForTimeRect-distanceForTimeRect, widthForTimeRect, heightForTimeRect,true);
			g.setColor(new Color(0,0,150));
			g.drawString(timeInDays.toString(),i*widthOfBigRect+origin+indentForTimeRect, height-origin-heightOfBigRect-heightForTimeRect-distanceForTimeRect+fontSize);//занятость каждого ресурса						
			//
			g.setColor(colorForDiagram()[i]);//цвета больших прямоугольников			
			g.fill3DRect(i*widthOfBigRect+origin, height-origin-heightOfBigRect, widthOfBigRect, heightOfBigRect,true);//большие прямоугольники для диаграммы
			//названия ресурсов						
			g.fill3DRect(width, i*heightOfNameRect+scanningSeparation+origin, widthOfNameRect, heightOfNameRect, true);
			i++;
			g.drawString(i.toString()+". "+r.getResource_name(), width+widthOfNameRect+5, (i-1)*heightOfNameRect+scanningSeparation+origin+heightOfNameRect);//названия ресурсов
			//
		}
	}
	/*public void drawNamesOfResources() {
		int i=0;		
		for(Resource r:resources) {
			g.fill3DRect(width, i*fontSize+scanningSeparation, widthOfNameRect, heightOfNameRect, true);
			g.drawString(r.getResource_name(), width, i*heightOfNameRect+scanningSeparation);//названия ресурсов
			i++;
		}
	}*/
}
