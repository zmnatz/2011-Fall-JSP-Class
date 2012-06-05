<html>
<link rel="stylesheet" href="style.css" type="text/css"></link>
<style>
.rows div {
	padding: 3px 0px 3px 0px;
	border-top-style: solid;
	border-top-width: 1px;
}
.rows .cost { float: right; }
p {padding-left: 5px}
</style>
<% 
	String status = request.getParameter("status"); 
	int courseCost = "JHU Employee".equals(status) ? 850 :
						"JHU Student".equals(status) ? 1000 : 
							"Speaker".equals(status) ? 0 : 1350;
%>
<body>
<form style="width: 510px" action="registrationForm.jsp" method="post">
<div class="header">Johns Hopkins Annual Software Development Seminar</div>
<p><b>${param.name}</b></p>
<p>You are registered as a <b>${param.email}</b>.</p>
<p>Your e-mail confirmation will be sent to <b><%= status %></b>.</p>
<div class="section rows">
	<span class="sectionTitle" style="float: right;">Cost</span>
	<span class="sectionTitle">Your Courses</span>
<%
int totalCost = 0;
//Loops through courses, adding to the total cost and displaying each row.
for(String course : request.getParameterValues("course")) {
	totalCost += courseCost;
%>
	<div><span class="cost"><%= courseCost>0 ? "$"+courseCost+".00":"Free"%></span><%= course %></div>
<%
}
//Adds hotel fee if checked
if("on".equals(request.getParameter("hotelFee"))) { 
	totalCost += 185;
%>
 	<div style="padding-top: 20px"><span class="cost">$185.00</span>Hotel Accommodation</div>
<%
}
//Adds parking fee if checked
if("on".equals(request.getParameter("parkingFee"))) { 
	totalCost += 10;
%>
 	<div style="padding-top: 20px"><span class="cost">$10.00</span>Parking Fee</div>
<%}%>
 	<div class="footer"><span class="cost">$<%= totalCost %>.00</span><span style="padding-right: 60px">Total</span></div>
</div>
<div class="footer"><input type="submit" value="Back" />
</div>
</form>
</body>
</html>