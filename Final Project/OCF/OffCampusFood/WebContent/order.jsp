<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Item" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Order" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.dao.DaoFactory" %>
<%@ page import="java.math.BigDecimal" %>
<%
  	Order currentOrder = (Order)request.getSession().getAttribute("currentOrder");
	List<Item> menu = DaoFactory.getInstance().getItemDao()
		.getItemsByRestaurantId(currentOrder.getRestaurantId()); 
%>
<script> 
function formSubmit(arg1,arg2){
	with(document.frmMain){
		switch(arg1){
		case 'Remove':
			document.getElementById('itemId').value=arg2;
			document.getElementById('submitType').value='REMOVE';
			action = 'orderMenu2';
			submit();
			break;
		case 'Add To Order':
			document.getElementById('itemId').value=arg2;
			document.getElementById('submitType').value='ADD';
			action = 'orderMenu2';
			submit();
			break;
			
		case 'Clear Order':
			document.getElementById('submitType').value='CLEAR';
			action = 'orderMenu2';
			submit();
			break;
		case 'Checkout':
			 window.location = 'checkout';
			 break;	
		}
	}
}
</script>

<table>

<tr >
<td VALIGN="top" >
<div style="width: 305px; margin-left:0px" class="section rows" >
<span class="sectionTitle">Menu</span>
	<% for(Item item : menu) {
		if(Boolean.TRUE.equals(item.getActive())){ %><div>
			<input type="button"  value="Add To Order" style="float: right; margin-right:10px;" onClick="formSubmit('Add To Order',<%= item.getItemId()%>)"> 
			<span style="float: right; margin-right: 5px;">$<%= item.getPrice()%>0<br></span>
			<b><%= item.getName() %></b><br><%= item.getDescription() %>
		</div><% }
	} %>	
</div> 
</td>
<td VALIGN="top">
<div class="section rows" style="width: 305px;">
<span class="sectionTitle">Current Order</span>

<%	BigDecimal totalCost = new BigDecimal(0);
 	if(currentOrder !=null && currentOrder.getItems()!=null && !currentOrder.getItems().isEmpty()){
		for(Item item : currentOrder.getItems()) {
			if(Boolean.TRUE.equals(item.getActive())){ %><div>
				<input type="button"  value="Remove" style="float: right; margin-right:10px;" onClick="formSubmit('Remove',<%= item.getItemId()%>)"> 
				<span style="float: right; margin-right: 5px;">$<%= item.getPrice()%>0<br></span>
				<b><%= item.getName() %></b><br><%= item.getDescription() %>
				<% totalCost = totalCost.add(item.getPrice()); %>
		    </div><%}
		}
	}
%>	
</div>
<div style="text-align: right;margin-right: 5px;"><b>Total Cost:</b> $<%=totalCost%>0<br></div>
</td>
</tr>
</table>

<input type="hidden" id="submitType" name="submitType" value="ADD"/>
<input type="hidden" id="itemId" name="itemId" value=""/>
