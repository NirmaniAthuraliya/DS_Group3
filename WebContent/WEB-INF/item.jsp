<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.PAF.Item"%>
<%
	//Insert Fund----------------------------------
	if (request.getParameter("itemId") != null)
	{
		Item itemObj = new Item();
		String stsMsg = itemObj.insertItem(
				request.getParameter("itemId"),
				request.getParameter("itemName"),
				request.getParameter("Description"),
				request.getParameter(" inventorID"),
				request.getParameter("inventorName"),
				request.getParameter(" fundStatus"),
				request.getParameter("SupplyStatus"),
				request.getParameter(" Quantity"),
				request.getParameter(" price"));
		
		session.setAttribute("statusMsg", stsMsg);
	} 
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Item Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="row">
		 	<div class="col">
		 	
		 	<br/><br/>
		 	
			   <h2 style="text-align:center">Item Management</h2>
			   <form method="post" action="item.jsp">
			   <br/>
			   		<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Item ID </label>
                		<div class="col-sm-9">
				  			<input name="itemId" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Item Name </label>
                		<div class="col-sm-9">
				  			<input name="itemName" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Description </label>
                		<div class="col-sm-9">	
				  			<input name="Decription" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Inventor ID</label>
                		<div class="col-sm-9">
				  			<input name="inventorID" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Inventor Name </label>
                		<div class="col-sm-9">
			      			<input name="inventorName" type="text" class="form-control"> 
			      		</div>
				  	</div>
				  
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Fund Status </label>
                		<div class="col-sm-9">
				  			<input name="fundStatus" type="text" class="form-control">
				  		</div>
				  	</div>
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Supply Status </label>
                		<div class="col-sm-9">
				  			<input name="SupplyStatus" type="text" class="form-control">
				  		</div>
				  	</div>
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Quantity </label>
                		<div class="col-sm-9">
				  			<input name="Quantity" type="text" class="form-control">
				  		</div>
				  	</div>
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Price </label>
                		<div class="col-sm-9">
				  			<input name="price" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<br/>
				  	
				  <input name="btnSubmit" class="btn btn-primary" type="submit" value="ADD">
			
			   </form>
			   
			   <br/><br/><br/>
			   
			</div>
		</div>
	</div>
	
	<div class="alert alert-success">
		<% out.print(session.getAttribute("statusMsg")); %>
	</div>
	
	<br>
	
	<%
		Item itemObj = new Item();
		out.print(itemObj.readItems());
	%>
	
</body>
</html>