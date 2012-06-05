package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;
import edu.jhu.cs605782.offcampusfood.entities.Order;

/**
 * Servlet implementation class EditOrders
 */
public class EditOrders extends AbstractOcfController {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		model.put("actions",Arrays.asList("Back to Info"));
		model.put("contentPage","editOrders.jsp");
		forward(request,response,model);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Restaurant r = (Restaurant)getUser(request);
		Map<String,Object> model = initModel(request);
		Integer orderId = Integer.valueOf(request.getParameter("orderId"));
		for(Order o : r.getOrders())
			if(o.getOrderId().equals(orderId)){
				o.setComplete(true);
				DaoFactory.getInstance().getOrderDao().updateOrder(o);
				break;
			}
		request.getSession().setAttribute("currentUser",r);
		model.put("actions", Arrays.asList("Back to Info"));
		model.put("contentPage", "editOrders.jsp");
		forward(request,response,model);
	}
}
