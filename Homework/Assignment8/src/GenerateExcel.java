
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RegistrationInfo;

/**
 * Servlet implementation class GenerateExcel
 */
public class GenerateExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("cache-control", "no-cache");
		RegistrationInfo regInfo = (RegistrationInfo)request.getSession().getAttribute("regInfo");
		StringBuilder receipt = new StringBuilder();
		
		receipt.append("Johns Hopkins Annual Software Development Seminar - Registration Receipt\n");
		receipt.append(regInfo.getName()).append("\n");
		receipt.append("Registered As:\t").append(regInfo.getStatus()).append("\n");
		receipt.append("Your Courses\tCost\n");
		short COURSE_COST = RegistrationInfo.STATUS_FEE.get(regInfo.getStatus());
		short totalCost = (short) (COURSE_COST * regInfo.getCourse().size());
		for(String course : regInfo.getCourse())
			receipt.append(course).append("\t").append(COURSE_COST>0 ? "$"+COURSE_COST+".00":"Free").append("\n");
		if(Boolean.TRUE.equals(regInfo.isHotel())) { 
			totalCost += 185;
			receipt.append("Hotel Accommodation\t$185.00\n");
		}
		//Adds parking fee if checked
		if(Boolean.TRUE.equals(regInfo.isParking())) { 
			totalCost += 10;
			receipt.append("Parking\t$10.00\n");
		}
		receipt.append("Total:\t$").append(totalCost).append(".00\n");
		
		PrintWriter out = response.getWriter();
		out.println(receipt);
	}

}
