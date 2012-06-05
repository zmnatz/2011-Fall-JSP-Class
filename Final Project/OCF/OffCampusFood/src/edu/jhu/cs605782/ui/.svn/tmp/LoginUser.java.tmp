package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;
import edu.jhu.cs605782.offcampusfood.entities.Login;

/**
 * Servlet implementation class LoginUser
 */
public class LoginUser extends AbstractOcfController {
	private static final long serialVersionUID = 1L;
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		if(request.getSession().getAttribute("currentUser")==null){
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
	
			Login login = DaoFactory.getInstance().getLoginDao().getLoginByEmail(email);
			if(login==null || !pass.equals(login.getPassword())){
				model.put("errors", "The email address and password provided is not valid.");
			} else {
				if(Login.RESTAURANT.equals(login.getLoginType())){
					request.getSession().setAttribute("currentUser",
						DaoFactory.getInstance().getRestaurantDao().getRestaurantByEmail(email));
				}else{
					request.getSession().setAttribute("currentUser",
						DaoFactory.getInstance().getUserDao().getUserByEmail(email));
				}
			}
		}
		model.put("contentPage", request.getParameter("contentPage"));
		if(model.get("contentPage")==null){
			model.put("contentPage", "welcome.jsp");
		}
		forward(request,response,model);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
