<%@ page import="model.PAF.Cart"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
    //Insert Cart----------------------------------
    	if (request.getParameter("prodCode") != null)
    	{
    		Cart cartObj = new Cart();
    		String stsMsg = cartObj.insertCart(request.getParameter("prodCode"),
    				                           request.getParameter("prodName"),
    				                           request.getParameter("prodPrice"),
    										   request.getParameter("quantity"),
    										   request.getParameter("CusAddress"),
    										   request.getParameter("CusContact"),
    										   request.getParameter("CusEmail"));
    		session.setAttribute("statusMsg", stsMsg);
    	} 


	//Delete product from cart
    	if (request.getParameter("cartID") != null) {
    		Cart cartObj = new Cart();
    		String stsMsg = cartObj.removeCart(request.getParameter("cartID"));
    		session.setAttribute("statusMsg", stsMsg);
    	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col">

				<br /> <br />

				<h2 style="text-align: center">My Shopping Cart</h2>
				<form method="post" action="cart.jsp">
					<br />
					<div class="form-group form-row">
						<label class="col-sm-3" for="input-Name"> Product Code </label>
						<div class="col-sm-9">
							<input name="prodCode" type="text" class="form-control">
						</div>
					</div>

					<div class="form-group form-row">
						<label class="col-sm-3" for="input-Name"> Product Name </label>
						<div class="col-sm-9">
							<input name="prodName" type="text" class="form-control">
						</div>
					</div>

					<div class="form-group form-row">
						<label class="col-sm-3" for="input-Name"> Product Price </label>
						<div class="col-sm-9">
							<input name="prodPrice" type="text" class="form-control">
						</div>
					</div>

					<div class="form-group form-row">
						<label class="col-sm-3" for="input-Name"> Quantity </label>
						<div class="col-sm-9">
							<input name="quantity" type="text" class="form-control">
						</div>
					</div>

					<div class="form-group form-row">
						<label class="col-sm-3" for="input-Name"> Customer Address
						</label>
						<div class="col-sm-9">
							<input name="cusAddress" type="text" class="form-control">
						</div>
					</div>

					<div class="form-group form-row">
						<label class="col-sm-3" for="input-Name"> Customer Contact
							No. </label>
						<div class="col-sm-9">
							<input name="CusContact" type="text" class="form-control">
						</div>
					</div>


					<div class="form-group form-row">
						<label class="col-sm-3" for="input-Name"> Customer Email </label>
						<div class="col-sm-9">
							<input name="CusEmail" type="text" class="form-control">
						</div>
					</div>


					<br /> <input name="btnSubmit" class="btn btn-primary"
						type="submit" value="Add to Cart">

				</form>


				<br /> <br /> <br />

			</div>
		</div>
	</div>

	<div class="alert alert-success">
		<% out.print(session.getAttribute("statusMsg")); %>
	</div>

	<br>

	<%
		Cart cartObj = new Cart();
		out.print(cartObj.readCart());
	%>

</body>
</html>