package com.PAF;

import model.PAF.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Fund") 

public class fundService 
{
	Fund fundObj = new Fund();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds()
	{
		return fundObj.readFunds();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFunds(
	@FormParam("fundCode") String FundCode,
	@FormParam("fundName") String FundName,
	@FormParam("fundEmail") String FundEmail,
	@FormParam("fundAddress") String FundAddress,
	@FormParam("fundContact") String FundContact,
	@FormParam("fundAmount") String FundAmount)
	{
		String output = fundObj.insertFunds(FundCode, FundName, FundEmail, FundAddress, FundContact, FundAmount);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFunds(String fundData)
	{
		//Convert the input string to a JSON object
		 JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
		 
		 //Read the values from the JSON object
		 String id = fundObject.get("id").getAsString();
		 String fundCode = fundObject.get("fundCode").getAsString();
		 String fundName = fundObject.get("fundName").getAsString();
		 String fundEmail = fundObject.get("fundEmail").getAsString();
		 String fundAddress = fundObject.get("fundAddress").getAsString();
		 String fundContact = fundObject.get("fundContact").getAsString();
		 String fundAmount = fundObject.get("fundAmount").getAsString();
		  
		 String output = fundObj.updateFunds(id, fundCode, fundName, fundEmail, fundAddress, fundContact, fundAmount);
		 return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFunds(String fundData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

		//Read the value from the element <id>
		String id = doc.select("id").text();
		
		String output = fundObj.deleteFunds(id);
		return output;
	}
	
}

