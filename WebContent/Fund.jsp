<%@ page import = "model.PAF.Fund" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	//Insert Fund----------------------------------
	if (request.getParameter("Code") != null)
	{
		Fund fundObj = new Fund();
		String stsMsg = fundObj.insertFunds(request.getParameter("Code"),
		request.getParameter("Name"),
		request.getParameter("Email"),
		request.getParameter("Address"),
		request.getParameter("ContactNo"),
		request.getParameter("Amount"));
		session.setAttribute("statusMsg", stsMsg);
	} 

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Fund Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="row">
		 	<div class="col">
		 	
		 	<br/><br/>
		 	
			   <h2 style="text-align:center">Fund Management</h2>
			   <form method="post" action="Fund.jsp">
			   <br/>
			   		<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> FundBody Code </label>
                		<div class="col-sm-9">
				  			<input name="Code" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> FundBody Name </label>
                		<div class="col-sm-9">
				  			<input name="Name" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> FundBody Email </label>
                		<div class="col-sm-9">	
				  			<input name="Email" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> FundBody Address </label>
                		<div class="col-sm-9">
				  			<input name="Address" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> FundBody Contact No. </label>
                		<div class="col-sm-9">
			      			<input name="ContactNo" type="text" class="form-control"> 
			      		</div>
				  	</div>
				  
				  	<div class="form-group form-row">
                		<label class="col-sm-3" for="input-Name"> Fund Amount </label>
                		<div class="col-sm-9">
				  			<input name="Amount" type="text" class="form-control">
				  		</div>
				  	</div>
				  	
				  	<br/>
				  	
				  <input name="btnSubmit" class="btn btn-primary" type="submit" value="CONFIRM FUND">
			
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
		Fund fundObj = new Fund();
		out.print(fundObj.readFunds());
	%>
	
</body>
</html>