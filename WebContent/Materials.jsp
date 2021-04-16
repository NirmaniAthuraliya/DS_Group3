<%@ page import="model.PAF.Material"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

		//insert Item
		if (request.getParameter("MaterialCode") != null)
		{ 
			 Material MaterialObj = new Material(); 
			 String stsMsg = MaterialObj.insertMaterials(request.getParameter("MaterialCode"), 
			 request.getParameter("MaterialName"), 
			 request.getParameter("MaterialPrice"), 
			 request.getParameter("MaterialDesc")); 
			 session.setAttribute("statusMsg", stsMsg); 
			}
		 
 
         //Delete Item
		 if (request.getParameter("MaterialID") != null) 
		 { 
			Material MaterialObj = new Material(); 
		    String stsMsg = MaterialObj.deleteMaterial(request.getParameter("MaterialID")); 
		    session.setAttribute("statusMsg", stsMsg); 
		 }
         
		
			/*if (request.getParameter("MaterialID") != null)
			{ 
				 Material MaterialObj = new Material(); 
				 String stsMsg = MaterialObj.updateMaterial(
						 request.getParameter("MaterialID"),
			     request.getParameter("MaterialCode"), 
				 request.getParameter("MaterialName"), 
				 request.getParameter("MaterialPrice"), 
				 request.getParameter("MaterialDesc")); 
				 session.setAttribute("statusMsg", stsMsg); 
				}*/
 
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Materials Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">

</head>
<body>
    <center><h1>Materials Management</h1></center>
    
    <div class="container">
 <div class="row">
 <div class="col">
 <form method="post" action="Materials.jsp">
	 Material code: <input name="MaterialCode" type="text" class="form-control"><br> 
	 Material name: <input name="MaterialName" type="text" class="form-control"><br> 
	 Material price: <input name="MaterialPrice" type="text" class="form-control"><br> 
	 Material description: <input name="MaterialDesc" type="text" class="form-control"><br> 
	 <input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">
	</form>
	
 </div>
 </div>
</div>
    
    
	
	
    <div class="alert alert-success">
    
     <% out.print(session.getAttribute("statusMsg")); %>
    </div>
    
    
    <br>
<%
Material MaterialObj = new Material(); 
 out.print(MaterialObj.readMaterials()); 
%>
    
	

</body>
</html>