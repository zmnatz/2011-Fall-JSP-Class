package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;
import edu.jhu.cs605782.offcampusfood.entities.BasicInfo;
import edu.jhu.cs605782.offcampusfood.entities.Login;
import edu.jhu.cs605782.offcampusfood.entities.Order;
import edu.jhu.cs605782.offcampusfood.entities.User;

public class CheckoutController extends AbstractOcfController{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		Order currentOrder = (Order)request.getSession().getAttribute("currentOrder");
		BasicInfo currentUser = getUser(request);
		if(currentOrder==null || currentOrder.getItems()==null || currentOrder.getItems().isEmpty()){
			 model.put("errors", "Order Empty! Must add something to order.");
	         model.put("contentPage", "welcome.jsp");
		} else if(currentUser==null){ 
            model.put("errors", "Must be logged in as user to checkout! Log in as user and return to this page.");
            model.put("contentPage", "order.jsp");
	    } else if(Login.RESTAURANT.equals(currentUser.getLogin().getLoginType())){ 
            model.put("errors", "Must be logged in as user to checkout! Log in as user and return to this page.");
            model.put("contentPage", "order.jsp");
            model.put("actions", Arrays.asList("Checkout","Clear Order"));
	    } else{
	    	model.put("restaurantName", DaoFactory.getInstance().getRestaurantDao().
	    			getRestaurantById(currentOrder.getRestaurantId()).getName());
	    	model.put("contentPage", "checkout.jsp");
	    	model.put("actions", getActions());
	    }
		forward(request,response,model);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	doGet(request,response);
		
		Map<String,Object> model = initModel(request);
		Order currentOrder = (Order)request.getSession().getAttribute("currentOrder");
		User user = (User)request.getSession().getAttribute("currentUser");

		currentOrder.setUserId(user.getUserId());
		DaoFactory.getInstance().getOrderDao().insertOrder(currentOrder);

		model.put("paidCard",request.getParameter("ccList"));
		model.put("contentPage", "finalOrder.jsp");
		model.put("actions", Arrays.asList("Return Home"));
		forward(request,response,model);
	}
	
	private List<String> getActions(){
		return Arrays.asList("Final Checkout", "Revise Order");
	}

}
