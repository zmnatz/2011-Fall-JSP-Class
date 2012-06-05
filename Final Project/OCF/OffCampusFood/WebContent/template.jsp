<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.BasicInfo" %>
<%@ page import="edu.jhu.cs605782.offcampusfood.entities.Order" %>
<%
	HashMap<String,Object> model = (HashMap<String,Object>) request.getAttribute("model");
	BasicInfo currentUser = (BasicInfo)request.getSession().getAttribute("currentUser");
	List<Order> orders = (List<Order>)model.get("streamOrders");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<title>OffCampusFood.com</title>
<LINK  REL=StyleSheet href="style.css"/>
</head>
<script>
function checkParam(fieldId){
	var field = document.getElementById(fieldId);
	if(field){
		if(field.value=='' || field.value==undefined){
			document.getElementById(fieldId+"Error").innerHTML = "This field cannot be blank.";
			return false;
		} else {
			document.getElementById(fieldId+"Error").innerHTML = "";
			return true;
		}
	}
	return true;
}
</script>
<body style="width: 955px; background-color: #E8E8E8;">
	<div id="header">
		<a href="index.htm" style="width: 700px;height:100%;display:block; float: left;"></a>
		<form id="login" name="login" method = "post" action="<%=currentUser==null ? "login" : "logout" %>">
			<div style="height: 60px">
<% if(currentUser==null) { %>
				<div><label>Email:</label><input type="text" name="email" SIZE="15"/></div>
				<div><label>Password:</label><input type="password" name="password" SIZE="15"/></div>
			</div>
			<input type="button" value="Register" onClick="window.location = 'register';"/> 
			<input type="submit" value="Login"/>
<% } else { %>
				<div>Welcome to Off Campus Food <%= currentUser.getName() %></div>
			</div>
			<input type="button" value="Edit Info" onClick="window.location = 'editInfo'"/>
			<% if(currentUser.getLogin().getLoginType().equals("RESTAURANT")) { %>
				<input type="button" value="Orders" onClick="window.location = 'editOrders';" />
			<% } else if(request.getSession().getAttribute("currentOrder")!=null){ %>
				<input type="button" value="Checkout" onClick="window.location = 'checkout';" />
			<% } %>
			<input type="submit" value="Logout"/>
<% } %>		
		</form>
	</div>
	<div class="error"><%= model.get("errors")==null ? "" : model.get("errors")%></div>
	<div id="body">
		<div id="streamer"><h1>Who's Hungry?</h1>
		<% for(Order o : orders) { %>
			<div><%= o.getUser().getName() + " wants a " + o.getItems().get(0).getName() %></div>
		<% } %>

		</div>
		<form id="frmMain" name="frmMain" action="" method="post">
		<div id="content">
<%if(model.get("actions")!=null) {%>
			<div class="toolbar-top">
<%	for(String action : (Collection<String>)model.get("actions")){
		%><input type="button" value="<%= action %>" onClick="formSubmit('<%=action%>');"/><%		
	}%>
			</div>
<%}%>
			<c:import url="${param.contentPage}"/>
<%if(model.get("actions")!=null) {%>
			<div class="toolbar-bottom">
<%	for(String action : (Collection<String>)model.get("actions")){
		%><input type="button" value="<%= action %>" onClick="formSubmit('<%=action%>');"/><%		
	}%>
			</div>
<%}%>
		</div>
		</form>
	</div>
	<div id="footer">Some text will go here</div>
</body>
</html>