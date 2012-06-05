<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Restaurant" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.dao.DaoFactory" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Order" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.User" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Item" %>
<%@ page import="java.lang.String" %>
<%
	Restaurant restaurant = (Restaurant)request.getSession().getAttribute("currentUser");
	restaurant = DaoFactory.getInstance().getRestaurantDao()
		.getRestaurantById(restaurant.getRestaurantId());
%>
<script>
function formSubmit(arg1,arg2){
	with(document.frmMain){
		action='editOrders';
		switch(arg1){
		case 'Complete Order':
			document.getElementById('orderId').value=arg2
			return submit();
		case 'Back to Info':
			window.location = 'editInfo';
			break;	
		default:
			return;	
		}
	}
}
</script>
<% if(restaurant.getOrders()!=null && !restaurant.getOrders().isEmpty()){ %>
<div class="section rows">
	<span class="sectionTitle">Orders</span>
	<% for(Order o : restaurant.getOrders()) { %>
	<%	if(!Boolean.TRUE.equals(o.getComplete()) && !o.getItems().isEmpty()){ 
			String items = "";
			for(Item item : o.getItems())
				items += item.getName()+"<br/>";
	%>
		<div>
			<input type="button" value="Complete Order" onClick="formSubmit('Complete Order',<%= o.getOrderId()%>)"/>
			<b><%=o.getUser().getName() %></b><br/>
			<%= items %>
		</div>
	<% }} %>
</div>
<% } else { %>
	<div style="height: 200px;text-align: center;padding: 20px;">No orders at this time</div>
<% } %>
<input type="hidden" id="submitType" name="submitType" value="ADD"/>
<input type="hidden" id="orderId" name="orderId" value=""/>
