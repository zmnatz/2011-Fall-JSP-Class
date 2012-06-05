package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhu.cs605782.offcampusfood.entities.BasicInfo;
import edu.jhu.cs605782.offcampusfood.entities.Order;
import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;

public abstract class AbstractOcfController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected BasicInfo getUser(HttpServletRequest request){
		return (BasicInfo)request.getSession().getAttribute("currentUser");
	}
		
	protected Map<String,Object> initModel(HttpServletRequest request){
		Map<String,Object> model = new HashMap<String,Object>();
		List<Order> orders = DaoFactory.getInstance().getOrderDao().getRecentOrders();
		model.put("streamOrders", orders);
		return model;
	}
	
	protected void forward(HttpServletRequest request, HttpServletResponse response, Map<String,Object> model) throws ServletException, IOException{
		request.setAttribute("model",model);
		System.out.println(model.get("contentPage"));
		getServletContext().getRequestDispatcher("/template.jsp?contentPage="+model.get("contentPage")).forward(request, response);
	}
}
