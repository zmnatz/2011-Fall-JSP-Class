<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Item" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Order" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.User" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.CreditCard" %>
<%@ page import="java.util.HashMap" %>

<%
   Order currentOrder = (Order)request.getSession().getAttribute("currentOrder");
   User user = (User)request.getSession().getAttribute("currentUser");
   HashMap<String,Object> model = (HashMap<String,Object>) request.getAttribute("model");
   ArrayList<CreditCard> cards = (ArrayList<CreditCard>)user.getCreditCards();
%>
<script> 
function formSubmit(arg1,arg2,arg3){
	with(document.frmMain){
		
		switch(arg1){
		case 'Create CC':
			window.location = 'editCC';
			break;
		
		case 'Revise Order':
			window.location = 'orderMenu2';
			break;

		case 'Final Checkout':
			document.getElementById('submitType').value='FINAL';
			submit();
			break;
	
		}
	}
}
</script>

<b><p><%=model.get("restaurantName")%></p></b>
<table>
<tr >
<td VALIGN="top">
<div class="section rows" style="width: 305px;">
<span class="sectionTitle">Current Order</span>

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
<span style="float: left; margin-right: 5px;"><b>Total Cost:</b> $<%=totalCost%>0<br></span>
<br/><br/>
<%if(cards!=null){ %>
Choose From Your Credit Cards:
<select name="ccList">	
<%for(CreditCard c: cards){ %>
<option value="********<%= c.getCcNumber().substring(c.getCcNumber().length()-4,c.getCcNumber().length()) %>">********<%= c.getCcNumber().substring(c.getCcNumber().length()-4,c.getCcNumber().length()) %></option>
<%}%>
</select>
<%} 
  else{
   request.getSession().setAttribute("addCredit","true");%> 
  No Credit Cards! 
  <br/>
  <input type="button" value="Create Card" onClick="formSubmit('Create CC')"/>
 <% }%>

<input type="hidden" id="submitType" name="submitType" value="FINAL"/>