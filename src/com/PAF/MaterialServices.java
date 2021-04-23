package com.PAF;

import model.PAF.Material; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Materials") 
public class MaterialServices {
	
	Material MaterialObj = new Material(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readMaterial() 
	 { 
	 return  MaterialObj.readMaterials();
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertMaterials(@FormParam("MaterialCode") String MaterialCode, 
	 @FormParam("MaterialName") String MaterialName, 
	 @FormParam("MaterialPrice") String MaterialPrice, 
	 @FormParam("MaterialDesc") String MaterialDesc) 
	{ 
	 String output = MaterialObj.insertMaterials(MaterialCode, MaterialName, MaterialPrice, MaterialDesc); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteMaterial(String MaterialData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(MaterialData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String MaterialID = doc.select("MaterialID").text(); 
	 String output = MaterialObj.deleteMaterial(MaterialID); 
	return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateMaterial(String MaterialData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject MaterialObject = new JsonParser().parse(MaterialData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String MaterialID = MaterialObject.get("MaterialID").getAsString(); 
	 String MaterialCode = MaterialObject.get("MaterialCode").getAsString(); 
	 String MaterialName = MaterialObject.get("MaterialName").getAsString(); 
	 String MaterialPrice = MaterialObject.get("MaterialPrice").getAsString(); 
	 String MaterialDesc = MaterialObject.get("MaterialDesc").getAsString();
	 
	 String output = MaterialObj.updateMaterial(MaterialID, MaterialCode, MaterialName, MaterialPrice, MaterialDesc);
	 
	return output; 
	}

	
	

}
