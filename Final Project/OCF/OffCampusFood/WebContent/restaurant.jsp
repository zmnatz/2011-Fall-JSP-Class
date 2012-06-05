<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Restaurant" %>
<script> 
function formSubmit(arg1,arg2){
	with(document.frmMain){
		action='editMenu';
		switch(arg1){
		case 'View Menu':
			document.getElementById('restId').value=arg2;
			action = 'orderMenu';
			submit();
			break;
		}
	}
}
</script>
	
<p>List of restaurants in the area: <b><%=request.getParameter("city")%> </b></p> 

<div class="section rows">
<span class="sectionTitle">Restaurant Name</span>
<span class="sectionTitle right">Phone Number</span>
<% for(Restaurant r: (ArrayList<Restaurant>)request.getSession().getAttribute("result")){ %>
	<div onClick="formSubmit('View Menu',<%= r.getRestaurantId()%>)">
		<span class ="right"><%=r.getPhoneNumber()%></span>
		<b><%=r.getName() %></b><br/>
		<%=r.getAddress().getStreetAddr1()%><br/>
		<%=r.getAddress().getCity() +", " + r.getAddress().getState() + ", " + r.getAddress().getZipCode()%></span>
	</div>

<% }%>
</div>
<input type="hidden" id="restId" name="restId" value="">
