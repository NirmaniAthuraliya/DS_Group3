<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.item.Item"%>
<%
if (request.getParameter("itemID") != null)
{
	Item itemObj = new Item();
	
	String stsMsg = itemObj.insertItem(request.getParameter("itemName"), request.getParameter("Description"),request.getParameter(" inventorID"), request.getParameter("inventorName"),request.getParameter(" fundStatus"), request.getParameter("SupplyStatus"),request.getParameter(" Quantity"),request.getParameter(" price"));
	
			
			session.setAttribute("statusMsg",stsMsg);
}
if(request.getParameter("itemID") != null){
	
	String  id = request.getParameter("itemID");
	
	Item itemObj = new Item();
	
	String[] item = itemObj.getItem(id);
	
	String itemID = item[0];
	String itemName = item[1];
	String Description = item[2];
	String inventorID = item[3];
	String inventorName = item[4];
	String fundStatus = item[5];
	String SupplyStatus = item[6];
	String Quantity = item[7];
	String price = item[8];
	
	String stsMsg = itemObj.removeItem(id);
	
	session.setAttribute("statusMsg",stsMsg);
	session.setAttribute("itemID",itemID);
	session.setAttribute("itemName",itemName);
	session.setAttribute("Description",Description);
	session.setAttribute("inventorID",inventorID);
	session.setAttribute("inventorName",inventorName);
	session.setAttribute("fundStatus",fundStatus);
	session.setAttribute("SupplyStatus",SupplyStatus);
	session.setAttribute("Quantity",Quantity);
	session.setAttribute("price",price);
	
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Item Management</h1>
	<form method="post" action="items.jsp">
	Item ID:<input name="itemID" type="text" ><br>
	Item Name:<input name="itemName" type="text" ><br>
	Item Description:<input name="Description" type="text" ><br>
	Inventor ID:<input name="inventorID" type="text" ><br>
	Inventor Name:<input name="inventorName" type="text" ><br>
	Fund Status:<input name="fundStatus" type="text" ><br>
	Supply Status:<input name="SupplyStatus" type="text" ><br>
	Quantity:<input name="quantity" type="text" ><br>
	Price:<input name="price" type="text" ><br>
	<input name="btnSubmit" type="submit" value="save">
	
	</form>
	<%
		//out.print(item[0]);
		out.print(session.getAttribute("statusMsg"));
	
	%>
	<br>
	
</body>
</html>