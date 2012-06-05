package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;
import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.entities.BasicInfo;
import edu.jhu.cs605782.offcampusfood.entities.Login;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;
import edu.jhu.cs605782.offcampusfood.entities.User;

/**
 * Servlet implementation class UpdateInfoController
 */
public class UpdateInfoController extends AbstractOcfController implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		model.put("actions",getActions(getUser(request)));
		model.put("contentPage","userInfo.jsp");
		forward(request,response,model);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicInfo user = getUser(request);
		Map<String,Object> model = initModel(request);
		
		String type = request.getParameter("loginType");
		if(user==null || user.getLogin()==null){
			if(Login.RESTAURANT.equals(type)){
				user = new Restaurant();
			}else {
				user = new User();
			}
			user.setLogin(new Login(request.getParameter("email"),
									request.getParameter("password"),
									type));
		} else if(Login.RESTAURANT.equals(user.getLogin().getLoginType())) {
			user = (Restaurant)user;
		} else {
			user = (User)user;
		}
		user.setName(request.getParameter("name"));
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		Address address = user.getAddress();
		if(address == null)
			address = new Address();
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setStreetAddr1(request.getParameter("streetAddr1"));
		address.setStreetAddr2(request.getParameter("streetAddr2"));
		try {
			address.setZipCode(Integer.valueOf(request.getParameter("zipCode")));
			user.setAddress(address);
			
			request.getSession().setAttribute("currentUser", 
					Login.RESTAURANT.equals(user.getLogin().getLoginType()) ?
						DaoFactory.getInstance().getRestaurantDao().insertOrUpdate((Restaurant)user) :
						DaoFactory.getInstance().getUserDao().insertOrUpdate((User)user));
		} catch (NumberFormatException e){
			model.put("errors", "The zip code enterred must be a number.");
		}
		model.put("actions",getActions(getUser(request)));
		model.put("contentPage","userInfo.jsp");
		forward(request,response,model);
	}
	
	private List<String> getActions(BasicInfo user){
		ArrayList<String> actions = new ArrayList<String>(Arrays.asList("Save Info","Reset"));
		if(user!=null && user.getLogin()!=null){
			if(Login.RESTAURANT.equals(user.getLogin().getLoginType()))
				actions.add("Edit Menu");
			else
				actions.add("Edit Credit Cards");
		}
		return actions;
	}

}
