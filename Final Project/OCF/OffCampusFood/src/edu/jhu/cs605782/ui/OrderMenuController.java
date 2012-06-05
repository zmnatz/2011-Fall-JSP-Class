package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;
import edu.jhu.cs605782.offcampusfood.dao.OrderDao;
import edu.jhu.cs605782.offcampusfood.dao.RestaurantDao;
import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;
import edu.jhu.cs605782.offcampusfood.entities.Order;


public class OrderMenuController extends AbstractOcfController{
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		RestaurantDao rDao = DaoFactory.getInstance().getRestaurantDao();
		Order currentOrder = (Order)request.getSession().getAttribute("currentOrder");
		
		Restaurant r = null;
		try{
			r = rDao.getRestaurantById(Integer.parseInt(request.getParameter("restId")));
		} catch(NumberFormatException e){};
		if(currentOrder==null){ 
			if(r==null){
				model.put("contentPage","welcome.jsp");
				model.put("errors", "You need to find a restaurant first.");
				forward(request,response,model);
				return;
			}else {
				currentOrder = new Order(); 
				currentOrder.setRestaurantId(r.getRestaurantId());
				request.getSession().setAttribute("currentOrder", currentOrder);
			}
		} 
		else if(!currentOrder.getRestaurantId().equals(r.getRestaurantId())){
			//new order
			currentOrder = new Order(); 
			currentOrder.setRestaurantId(r.getRestaurantId());
			request.getSession().setAttribute("currentOrder", currentOrder);
		}
		model.put("menu",r.getMenu());
		model.put("contentPage", "order.jsp");
		model.put("actions", getActions(currentOrder));
		forward(request,response,model);
		
	}
	private List<String> getActions(Order o){
		List<String> actions = new ArrayList<String>();
		if(o.getItems()!=null && !o.getItems().isEmpty()){
			actions.add("Checkout");
			actions.add("Clear Order");
		}
		return actions;
	}
	

}
