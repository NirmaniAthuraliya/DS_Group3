package model.PAF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pafproject","root",""
				 );
	 }catch(Exception e){
		 e.printStackTrace();
	 }
//	 Class.forName("com.mysql.jdbc.Driver");
//	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hms",
//	 "root", "");
//	 //For testing
//	 System.out.print("Successfully connected");
//	 }
//	 catch(Exception e)
//	 {
//	 e.printStackTrace();
//	 }

	 return con;
	}
	public String insertItem(String itemId,String itemName,String Description,String inventorID,String inventorName,String fundStatus,String SupplyStatus,String Quantity,String price) {
		String output="";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database";
			}
			
		String query= "insert into items ('itemName','Description','inventorID','inventorName','fundStatus','SupplyStatus','Quantity','price')" 
			+ "values (?,?,?,?,?,?,?,?)";
		
		
		PreparedStatement preparedStmt =con.prepareStatement(query);
		
		preparedStmt.setString(1, itemId);
		preparedStmt.setString(2, itemName);
		preparedStmt.setString(3, Description);
		preparedStmt.setString(4, inventorID);
		preparedStmt.setString(5, inventorName);
		preparedStmt.setString(6, fundStatus);
		preparedStmt.setString(7, SupplyStatus);
		preparedStmt.setString(8, Quantity);
		preparedStmt.setString(9, price);
		preparedStmt.execute();
		con.close();
		
		output = "Insert Successfully";
		
			
		}catch (Exception e)
		{
			output = "Error while inserting";
			System.out.println(e.getMessage());
		}
		
		return output;
	}

	
	
	//Read items in the database
		public String readItems() 
		{	
			String output = "";
			
			try {
				Connection conn = connect();
				
				if(conn == null) {	
					return "Error while connecting to the database for the reading";	
				}
				
				//table creating
				output = "<table border='1'>"
						+ "<tr>"
							+ "<th> ITEM CODE </th>"
							+ "<th> ITEM NAME </th>"
							+ "<th> DESCRIPTION </th>"
							+ "<th> INVENTOR ID </th>"
							+ "<th> INVENTOR NAME </th>"
							+ "<th> FUND STATUS </th>"
							+ "<th> SUPPLY STATUS </th>"
							+ "<th> QUANTITY </th>"
							+ "<th> PRICE </th>"
							+ "<th> UPDATE </th>"
							+ "<th> REMOVE </th>"
						+ "</tr>";
				
				String query = "SELECT * FROM items";
		
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		
				//iterate through the rows in the result set
				while(rs.next()) 
				{
					String itemID = Integer.toString(rs.getInt("id"));
					String itemName = rs.getString("itemName");
					String Description = rs.getString("Description");
					String inventorID = rs.getString("inventorID");
					String inventorName = rs.getString("inventorName");
					String fundStatus = rs.getString("fundStatus");
					String SupplyStatus = rs.getString("SupplyStatus");
					String Quantity = rs.getString("Quantity");
					String price = rs.getString("price");
					
					//Add a row into the html table
					output += "<tr><td>" + itemID + "</td>";
					output += "<td>" + itemName + "</td>";
					output += "<td>" + Description + "</td>";
					output += "<td>" + inventorID + "</td>";
					output += "<td>" + inventorName + "</td>";
					output += "<td>" + fundStatus + "</td>";
					output += "<td>" + SupplyStatus + "</td>";
					output += "<td>" + Quantity + "</td>";
					output += "<td>" + price + "</td>";
					
					//buttons
					output += "<td><form method='post' action='Fund.jsp'>"
							+ "<input name='UPDATE' "
							+ " type='submit' value='Update' class='btn btn-warning'>"
							+ "<input name='fundID' type='hidden' "
							+ " value='" + itemID + "'>" + "</form></td>"
							
							+ "<td><form method='post' action='Fund.jsp'>"
							+ "<input name='REMOVE'"
							+ " type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='fundID' type='hidden' "
							+ " value='" + itemID + "'>" + "</form></td></tr>";
				}
				conn.close();
				
				//Complete the html table
				output += "</table>";
			}
			catch(Exception e) {	
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String updateItem(String itemId,String itemName,String Description,String inventorID,String inventorName,String fundStatus,String SupplyStatus,String Quantity,String price)
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for updating."; 
				} 
				
				String query = "UPDATE items SET funderName=?,amount=?,fundingDate=?,fundStatus=? WHERE itemID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				preparedStmt.setString(1, itemId);
				preparedStmt.setString(2, itemName);
				preparedStmt.setString(3, Description);
				preparedStmt.setString(4, inventorID);
				preparedStmt.setString(5, inventorName);
				preparedStmt.setString(6, fundStatus);
				preparedStmt.setString(7, SupplyStatus);
				preparedStmt.setString(8, Quantity);
				preparedStmt.setString(9, price);
				
				preparedStmt.execute(); 
				con.close(); 
				output = "Updated successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while updating item details."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		
		
		public String deleteItem(String itemId) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{
					return "Error while connecting to the database for deleting."; } 
				
				String query = "delete from funds where fundID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				preparedStmt.setInt(1, Integer.parseInt(itemId)); 
				
				preparedStmt.execute(); 
				con.close(); 
				output = "Deleted successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while deleting item details."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
	}

