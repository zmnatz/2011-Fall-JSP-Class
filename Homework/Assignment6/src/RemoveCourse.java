

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RegistrationInfo;

/**
 * Servlet implementation class RemoveCourse
 */
public class RemoveCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationInfo regInfo = (RegistrationInfo)request.getSession().getAttribute("regInfo");
		regInfo.getCourse().remove(request.getParameter("course"));
		request.getSession().setAttribute("regInfo",regInfo);
		getServletContext().getRequestDispatcher("/registrationReceipt.jsp").forward(request, response);
	}

}
