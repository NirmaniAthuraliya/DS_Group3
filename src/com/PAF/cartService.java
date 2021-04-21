package com.PAF;

import model.PAF.Cart;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 


//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Carts") 

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
			@FormParam("prodCode") String prodCode,
			@FormParam("prodName") String prodName,
			@FormParam("prodPrice") String prodPrice,
			@FormParam("quantity") String quantity,
			@FormParam("CusAddress") String CusAddress,
			@FormParam("CusContact") String CusContact,
			@FormParam("CusEmail") String CusEmail)
	{
		String output = cartObj.insertCart(prodCode, prodName, prodPrice, quantity, CusAddress, CusContact, CusEmail);
		return output;
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCart(String cartData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject cartObject = new JsonParser().parse(cartData).getAsJsonObject();
		
		//Read the values from the JSON object
		String id = cartObject.get("id").getAsString(); 
		String prodCode = cartObject.get("prodCode").getAsString(); 
		String prodName = cartObject.get("prodName").getAsString(); 
		String prodPrice = cartObject.get("prodPrice").getAsString();
		String quantity = cartObject.get("quantity").getAsString();
		String CusAddress = cartObject.get("CusAddress").getAsString(); 
		String CusContact = cartObject.get("CusContact").getAsString();
		String CusEmail = cartObject.get("CusEmail").getAsString();
		String output = cartObj.updateCart(id,prodCode, prodName, prodPrice, quantity, CusAddress, CusContact, CusEmail); 
		return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String removeCart(String cartData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(cartData, "", Parser.xmlParser()); 

		//Read the value from the element <id>
		String id = doc.select("id").text(); 
		String output = cartObj.removeCart(id); 
		return output; 
	}
	
}

