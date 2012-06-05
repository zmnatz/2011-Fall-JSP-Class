

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.RegistrationInfo;

/**
 * Servlet implementation class RegistrationConfirm
 */
public class RegistrationConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegistrationInfo regInfo = (RegistrationInfo)request.getSession().getAttribute("regInfo");
		try {
			sendEmail(regInfo);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("confirmed",Boolean.TRUE);
		getServletContext().getRequestDispatcher("/registrationReceipt.jsp").forward(request, response);
	}
	
	private void sendEmail(RegistrationInfo ri) throws AddressException, MessagingException{
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.johnshopkins.edu");
		properties.setProperty("mail.smtp.port", "25");
		
		Message message = new MimeMessage(Session.getInstance(properties));

		message.addRecipient(RecipientType.TO, new InternetAddress(ri.getEmail()));
		message.addFrom(new InternetAddress[] { new InternetAddress("zmnatza1@jhu.edu") });

		message.setSubject("Seminar Registration Confirmation");
		
		StringBuilder body = new StringBuilder("<html><body>");
		body.append("<h2>Johns Hopkins Annual Software Development Seminar</h2>");
		body.append("<b>").append(ri.getName()).append("</b><br/>");
		body.append("You are registered as a <b>").append(ri.getStatus()).append("</b><br/><br/>");
		body.append("<table><thead><tr><td>Your Courses</td><td>Price</td></tr></thead><tbody>");
		short courseCost = RegistrationInfo.STATUS_FEE.get(ri.getStatus());
		short totalCost = (short) (courseCost * ri.getCourse().size());
		for(String course : ri.getCourse())
			body.append("<tr><td>").append(course).append("</td><td>")
				.append(courseCost>0 ? "$"+courseCost+".00" : "Free")
				.append("</td></tr>");
		if(Boolean.TRUE.equals(ri.isHotel())) { 
			totalCost += 185;
			body.append("<tr><td>Hotel Accommodation</td><td>$185.00</td></tr>");
		}
		//Adds parking fee if checked
		if(Boolean.TRUE.equals(ri.isParking())) { 
			totalCost += 10;
			body.append("<tr><td>Parking</td><td>$10.00</td></tr>");
		}
		body.append("<tr><td>Total:</td><td>$").append(totalCost).append(".00</td></tr>");
		body.append("</tbody></table></body></html>");
		message.setContent(body.toString(), "text/html");
		Transport.send(message);
	}
}
