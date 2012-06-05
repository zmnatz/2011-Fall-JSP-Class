package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;
import edu.jhu.cs605782.offcampusfood.dao.RestaurantDao;
import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;


public class RestaurantSearchController extends AbstractOcfController {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		
		
		RestaurantDao rDao = DaoFactory.getInstance().getRestaurantDao();
		ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) rDao.getAllRestaurants();
		
		request.getSession().setAttribute("result", restaurants);
		model.put("contentPage", "restaurant.jsp");
		//model.put("actions", Arrays.asList(new String[] {"Search"}));
		forward(request,response,model);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}

}
