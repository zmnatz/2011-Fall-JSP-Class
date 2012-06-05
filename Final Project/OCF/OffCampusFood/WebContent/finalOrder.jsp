<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.BasicInfo" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Restaurant" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Item" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Order" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.User" %>
<% 
   Order currentOrder = (Order)request.getSession().getAttribute("currentOrder");
   User user =  (User)request.getSession().getAttribute("currentUser");
   HashMap<String,Object> model = (HashMap<String,Object>) request.getAttribute("model");
%>
<script> 
function formSubmit(arg1){
	with(document.frmMain){
		switch(arg1){
		case 'Return Home':
			window.location = 'index.htm';
			break;
		}
	}
}
</script>

<p>The following order is complete for : <%=user.getName() %></p>
<table>
<tr >
<td VALIGN="top">
<div class="section rows" style="width: 305px;">
<span class="sectionTitle">Complete Order</span>

<%BigDecimal totalCost = new BigDecimal(0); %>
<% if(currentOrder !=null && currentOrder.getItems()!=null && !currentOrder.getItems().isEmpty()){  %>
	<% for(Item item : currentOrder.getItems()) {%>

		<%if(Boolean.TRUE.equals(item.getActive())){ %>
			<div>
			<input type="hidden" id="itemId" name="itemId" value=""/>  
			<input type="hidden" id="restId" name="restId" value=""/> 
			<span style="float: right; margin-right: 5px;">$<%= item.getPrice()%>0<br></span>
			<b><%= item.getName() %></b><br><%= item.getDescription() %>
			<% totalCost = totalCost.add(item.getPrice()); %>
		    </div>	
		<% } %>
		<% } %>  
	
		<% } %>
	
</div>
</td>
</tr>
</table>


<p>Paid with card no:<%= model.get("paidCard") %></p>
<br/> 
<p>Please save this for your record.</p>

<%   request.getSession().setAttribute("currentOrder",null);%>