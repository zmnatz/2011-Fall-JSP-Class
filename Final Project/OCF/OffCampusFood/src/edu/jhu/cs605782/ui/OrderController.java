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

import edu.jhu.cs605782.offcampusfood.entities.BasicInfo;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;
import edu.jhu.cs605782.offcampusfood.entities.Item;
import edu.jhu.cs605782.offcampusfood.entities.Order;
import edu.jhu.cs605782.offcampusfood.dao.RestaurantDao;
import edu.jhu.cs605782.offcampusfood.dao.OrderDao;
import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;


public class OrderController extends AbstractOcfController{

	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Map<String,Object> model = initModel(request);
		model.put("contentPage", "order.jsp");
		model.put("actions", getActions());
		forward(request,response,model);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request,response);
		
		Map<String,Object> model = initModel(request);

		//Grab current order
		Order currentOrder = (Order)request.getSession().getAttribute("currentOrder");
		String submitType = request.getParameter("submitType");
		Restaurant r = DaoFactory.getInstance().getRestaurantDao().
			getRestaurantById(currentOrder.getRestaurantId());
		
		//Add or remove that item
		if (submitType.equals("CLEAR")){ 
			Integer restId = currentOrder.getRestaurantId();
			currentOrder =  new Order();
			currentOrder.setRestaurantId(restId);
		}else{
			//Find the item 
			Integer itemId = Integer.parseInt(request.getParameter("itemId"));
			Item itemToDo = DaoFactory.getInstance().getItemDao().getItemById(itemId);
			if(currentOrder.getItems()==null){ 
				currentOrder.setItems(new ArrayList<Item>());
			}
			if(submitType.equals("ADD")){ 
				currentOrder.getItems().add(itemToDo);
			}
			else if(submitType.equals("REMOVE")){ 
				currentOrder.getItems().remove(itemToDo);
			}
		}
		request.getSession().setAttribute("currentOrder", currentOrder);
		model.put("menu", r.getMenu());
		model.put("contentPage", "order.jsp");
		model.put("actions", getActions());
		forward(request,response,model);

	}

	private List<String> getActions(){
		return Arrays.asList("Checkout","Clear Order");
	}
}
