import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RegistrationInfo;

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
		RegistrationInfo regInfo = new RegistrationInfo();
		Map<String,String[]> params = request.getParameterMap();
		
		//Validate the request, make sure all required fields are present.
		String[][] reqFields = new String[][] {{"name","Name"},{"email","E-Mail"},
				{"course","Courses"},{"status","Employement Status"}};
		
		StringBuilder errors = new StringBuilder();
		for(String[] field : reqFields){
			if(!params.containsKey(field[0]))
				errors.append("<li>"+field[1]+"</li>");
		}
		for(Map.Entry<String,String[]> entry : (Set<Map.Entry<String,String[]>>)params.entrySet()){
			String field = entry.getKey();
			String clsField = field.substring(0,1).toUpperCase()+field.substring(1);
			String[] vals = entry.getValue();
			try {
				Field fieldObject = RegistrationInfo.class.getDeclaredField(field);
				Method setter = RegistrationInfo.class.getMethod("set"+clsField,fieldObject.getType());
				Object value;
				if(Boolean.class.equals(fieldObject.getType())){
					value = Arrays.asList("CHECKED","TRUE","ON").contains(vals[0].toUpperCase());
				}else if(Set.class.equals(fieldObject.getType())){
					value = new TreeSet<String>();
					((Set<String>)value).addAll(Arrays.asList(vals));
				} else{
					value = vals[0];
				}
				setter.invoke(regInfo,value);
			}catch (SecurityException e1) { } 
			catch (NoSuchFieldException e1) {} 
			catch (NoSuchMethodException e) {} 
			catch (IllegalArgumentException e) {} 
			catch (IllegalAccessException e) {}
			catch (InvocationTargetException e) {}
		}
		request.getSession().setAttribute("regInfo",regInfo);
		
		//An error occurred, return to the form with an error message.
		if(errors.length()>0){
			errors.insert(0, "The following fields are required. Please include them and resubmit:<ul>");
			errors.append("</ul>");
			request.setAttribute("errors", errors.toString());
			getServletContext().getRequestDispatcher("/registrationForm.jsp").forward(request, response);
		} 
		//No errors, continue to the receipt.
		else
			getServletContext().getRequestDispatcher("/registrationReceipt.jsp").forward(request, response);
	}
}
