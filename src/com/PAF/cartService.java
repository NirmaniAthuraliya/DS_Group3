package com.PAF;

import model.PAF.Cart;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Cart") 

public class cartService 
{
	Cart cartObj = new Cart();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCart()
	{
		return cartObj.readCart();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	public String insertCart(
			@FormParam("cartCode") String CartCode,
			@FormParam("cartName") String CartName,
			@FormParam("cartEmail") String CartEmail,
			@FormParam("cartAddress") String CartAddress,
			@FormParam("cartContact") String CartContact,
			@FormParam("cartAmount") String CartAmount)
	{
		String output = cartObj.insertCart(CartCode, CartName, CartEmail, CartAddress, CartContact, CartAmount);
		return output;
	}
	
}
