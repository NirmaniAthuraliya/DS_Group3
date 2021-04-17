package com.PAF;

import model.PAF.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
	
}

