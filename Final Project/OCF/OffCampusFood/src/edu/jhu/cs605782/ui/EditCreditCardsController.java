package edu.jhu.cs605782.ui;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jhu.cs605782.offcampusfood.entities.CreditCard;
import edu.jhu.cs605782.offcampusfood.entities.User;
import edu.jhu.cs605782.offcampusfood.entities.Address;
import edu.jhu.cs605782.offcampusfood.dao.DaoFactory;
import edu.jhu.cs605782.offcampusfood.dao.CreditCardDao;

/**
 * Servlet implementation class UpdateCreditCards
 */
public class EditCreditCardsController extends AbstractOcfController implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> model = initModel(request);
		model.put("actions", getActions());
		model.put("contentPage", "editCC.jsp");
		forward(request,response,model);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) getUser(request);
		Map<String,Object> model = initModel(request);
		
		CreditCardDao dao = DaoFactory.getInstance().getCreditCardDao();
		if("REMOVE".equalsIgnoreCase(request.getParameter("submitType"))){
			Integer ccId = Integer.valueOf(request.getParameter("ccId"));
			CreditCard ccToRemove = null;
			for(CreditCard cc: user.getCreditCards())
				if(cc.getCcId().equals(ccId)){
					dao.deleteCreditCard(ccId);
					ccToRemove = cc;
					break;
				}
			if(ccToRemove!=null){
				user.getCreditCards().remove(ccToRemove);
				request.getSession().setAttribute("currentUser",user);
			}
		}else{
			CreditCard cc = new CreditCard();
			cc.setCcNumber(request.getParameter("ccNumber"));
			cc.setName(request.getParameter("name"));
			cc.setPhoneNumber(request.getParameter("phoneNumber"));
			Address address = new Address();
			address.setCity(request.getParameter("city"));
			address.setState(request.getParameter("state"));
			address.setStreetAddr1(request.getParameter("streetAddr1"));
			address.setStreetAddr2(request.getParameter("streetAddr2"));
			address.setZipCode(Integer.valueOf(request.getParameter("zipCode")));
			cc.setAddress(address);
			try{
				cc.setExpirationDate(new SimpleDateFormat("MM/yyyy").parse(
						request.getParameter("expirationDate")));
				if(user.getCreditCards()==null)
					user.setCreditCards(new ArrayList<CreditCard>());
				cc.setUserId(user.getUserId());
				user.getCreditCards().add(cc);
				dao.insertCreditCard(cc);
				request.getSession().setAttribute("currentUser",user);
			} catch (ParseException e ){
				model.put("errors", "The credit card expiration date was not valid.");
			}
		}
		
		model.put("actions", getActions());
		model.put("contentPage", "editCC.jsp");
		forward(request,response,model);
	}

	private List<String> getActions(){
		return Arrays.asList("Add Credit Card","Back to Info");
	}
}
