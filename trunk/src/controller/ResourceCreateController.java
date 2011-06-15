package controller;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Countable;
import model.Inventarable;

/**
 * Servlet implementation class ResourceCreateController
 */
public class ResourceCreateController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
	}

	/**
	 * завис
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

		ArrayList<Class> interfaces = new ArrayList<Class>();
		
		if (request.getAttribute("isCountable") != null)	
			interfaces.add(Countable.class);
		if (request.getAttribute("isInventarable") != null)
			interfaces.add(Inventarable.class);

		Object proxy = Proxy.newProxyInstance(null, (Class[])interfaces.toArray(), new TraceHandler());
	}

}

class TraceHandler implements InvocationHandler
{
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{		
		//method.invoke(arg0, arg1);
		return null;
	}
	
}
