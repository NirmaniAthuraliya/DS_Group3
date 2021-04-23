package com.PAF;

import model.PAF.Item;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Items")
public class itemServices {
	
	Item itemObj = new Item();

	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON) 
	public String readItems() 
	{ 
	 return itemObj.readItems(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(
	@FormParam("itemId") String itemId, 
	@FormParam("itemName") String itemName,
	@FormParam("Description") String Description, 
	@FormParam("inventorID") String inventorID,
	@FormParam("inventorID") String inventorName,
	@FormParam("fundStatus") String fundStatus, 
	@FormParam("SupplyStatus") String SupplyStatus,
	@FormParam("Quantity") String Quantity,
	@FormParam("price") String price) 
	{ 
	 String output = itemObj.insertItem(itemId,itemName,Description,inventorID,inventorName,fundStatus,SupplyStatus,Quantity,price); 
	 return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String itemId = itemObject.get("itemId").getAsString(); 
	 String itemName = itemObject.get("itemName").getAsString(); 
	 String Description = itemObject.get("Description").getAsString(); 
	 String inventorID = itemObject.get("inventorID").getAsString();
	 String inventorName = itemObject.get("inventorName").getAsString();
	 String fundStatus = itemObject.get("fundStatus").getAsString(); 
	 String SupplyStatus = itemObject.get("SupplyStatus").getAsString(); 
	 String Quantity = itemObject.get("Quantity").getAsString();
	 String price = itemObject.get("price").getAsString(); 
	 String output = itemObj.updateItem(itemId,itemName,Description,inventorID,inventorName,fundStatus,SupplyStatus,Quantity,price); 
	 return output; 
	}

	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String itemId = doc.select("itemId").text(); 
	 String output = itemObj.deleteItem(itemId); 
	return output; 
	}

}


