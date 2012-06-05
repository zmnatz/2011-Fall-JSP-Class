import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationSubmit
 */
public class RegistrationSubmit extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Validate the request, make sure all required fields are present.
		String[][] reqFields = new String[][] {{"name","Name"},{"email","E-Mail"},
				{"course","Courses"},{"status","Employement Status"}};
		StringBuilder errors = new StringBuilder();
		for(String[] field : reqFields){
			String[] vals = request.getParameterValues(field[0]);
			if(vals==null || vals.length<1 || vals[0].trim().length()<1)
				errors.append("<li>"+field[1]+"</li>");
		}

		//An error occurred, return to the form with an error message.
		if(errors.length()>0){
			errors.insert(0, "The following fields are required. Please include them and resubmit:<ul>");
			errors.append("</ul>");
			request.setAttribute("errors", errors.toString());
			getServletContext().getRequestDispatcher("/registrationForm.jsp").forward(request, response);
		} 
		//No errors, continue to the receipt.
		else {
			getServletContext().getRequestDispatcher("/registrationReceipt.jsp").forward(request, response);
		}
	}
}
