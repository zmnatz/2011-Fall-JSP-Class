package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;
import edu.jhu.cs605782.offcampusfood.dao.ItemDao;
import edu.jhu.cs605782.offcampusfood.entities.Item;
import edu.jhu.cs605782.offcampusfood.entities.Restaurant;
import edu.jhu.cs605782.offcampusfood.entities.User;

/**
 * Servlet implementation class UpdateCreditCards
 */
public class EditMenuController extends AbstractOcfController implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		model.put("actions", getActions());
		model.put("contentPage", "editMenu.jsp");
		forward(request,response,model);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Restaurant r = (Restaurant)getUser(request);
		Map<String,Object> model = initModel(request);
		if("REMOVE".equalsIgnoreCase(request.getParameter("submitType"))){
			Integer itemId = Integer.valueOf(request.getParameter("itemId"));
			for(Item item : r.getMenu())
				if(item.getItemId().equals(itemId)){
					item.setActive(false);
					break;
				}
		}else{
			Item item = new Item();
			item.setName(request.getParameter("name"));
			item.setPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("price"))));
			item.setDescription(request.getParameter("description"));
			item.setActive(true);
			if(r.getMenu()==null)
				r.setMenu(new ArrayList<Item>());
			r.getMenu().add(item);
		}
		request.getSession().setAttribute("currentUser", 
			DaoFactory.getInstance().getRestaurantDao().updateRestaurant(r));
		model.put("actions", getActions());
		model.put("contentPage", "editMenu.jsp");
		forward(request,response,model);
	}
	
	private List<String> getActions(){
		return Arrays.asList("Add Menu Item","Back to Info");
	}

}
