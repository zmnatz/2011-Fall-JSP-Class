

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import Beans.RegistrationInfo;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
/**
 * Servlet implementation class GeneratePdf
 */
public class GeneratePdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		RegistrationInfo regInfo = (RegistrationInfo)request.getSession().getAttribute("regInfo");
		
		Document document = new Document();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			PdfWriter.getInstance(document, buffer);
			document.open();
			document.add(new Paragraph("Johns Hopkins Annual Software Development Seminar - Registration Receipt"));
			document.add(new Paragraph(regInfo.getName()));
			document.add(new Paragraph("You are registered as a " + regInfo.getStatus()+"."));
			PdfPTable courses = new PdfPTable(2);
			courses.setHeaderRows(1);
			courses.addCell("Your Courses");
			courses.addCell("Cost");
			final int COURSE_COST = RegistrationInfo.STATUS_FEE.get(regInfo.getStatus());
			for(String course : regInfo.getCourse()) {
				courses.addCell(course);
				courses.addCell(COURSE_COST>0 ? "$"+COURSE_COST+".00":"Free");
			}
			int totalCost = COURSE_COST * regInfo.getCourse().size();
			
			if(Boolean.TRUE.equals(regInfo.isHotel())) { 
				totalCost += 185;
				courses.addCell("Hotel Accomodation");
				courses.addCell("$185.00");
			}
			//Adds parking fee if checked
			if(Boolean.TRUE.equals(regInfo.isParking())) { 
				totalCost += 10;
				courses.addCell("Parking");
				courses.addCell("$10.00");
			}
			courses.addCell("Total:");
			courses.addCell("$"+totalCost+".00");
			document.add(courses);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		
		document.close();
		DataOutput output = new DataOutputStream(response.getOutputStream());
		byte[] bytes = buffer.toByteArray();
		response.setContentLength(bytes.length);
		for (int i = 0; i < bytes.length; i++) 
		{
		 output.writeByte(bytes[i]);
		}

	}

}
