

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RegistrationInfo;

/**
 * Servlet implementation class AddCourses
 */
public class AddCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationInfo regInfo = (RegistrationInfo)request.getSession().getAttribute("regInfo");
		regInfo.setHotel("on".equals(request.getParameter("hotel")));
		regInfo.setParking("on".equals(request.getParameter("parking")));
		
		StringBuilder errorMsg = new StringBuilder();
		String[] courses = request.getParameterValues("course");
		if(courses!=null)
			for(String course : courses){
				if(regInfo.getCourse().contains(course))
					errorMsg.append("<li>"+course+"</li>");
			}
		
		if(errorMsg.length()>0){
			errorMsg.insert(0,"The following courses were already added:<ul>");
			errorMsg.append("</ul>");
			request.setAttribute("errors", errorMsg);
			request.getSession().setAttribute("regInfo",regInfo);
			getServletContext().getRequestDispatcher("/registrationForm.jsp?addCourses=true").forward(request, response);
		}else{
			if(courses!=null)
				regInfo.getCourse().addAll(Arrays.asList(courses));
			request.getSession().setAttribute("regInfo",regInfo);
			getServletContext().getRequestDispatcher("/registrationReceipt.jsp").forward(request, response);
		}
		
	}

}
