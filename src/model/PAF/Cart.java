package model.PAF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cart {
	//Connect to the database
		private Connection connect() {
			
			Connection conn = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer_management","root", "");
				
				//for testing
				//System.out.println("Successfully connected");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		//Insert funds 
		public String insertCart(String CartCode, String CartName , String CartEmail , String CartAddress, String CartContact, String CartAmount) 
		{
			String output = "";
			
			try {
				Connection conn = connect();
				
				if(conn == null) {
					return "Error while connecting to the database for Inserting";
				}
				
				//create a prepared statement
				String query = "INSERT INTO cart(id, CartCode, CartName, CartEmail, CartAddress, CartContact, CartAmount)" 
				+ " values(?,?,?,?,?,?,?)";
				
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				
				//binding values
				preparedStmt.setInt(1,0);
				preparedStmt.setString(2,CartCode);
				preparedStmt.setString(3,CartName);
				preparedStmt.setString(4,CartEmail);
				preparedStmt.setString(5,CartAddress);
				preparedStmt.setString(6,CartContact);
				preparedStmt.setDouble(7,Double.parseDouble(CartAmount));
				
				//execute the statement
				preparedStmt.execute();
				conn.close();
				
				output = "Inserted successfully";
			}
			catch(Exception e) {	
				output = "Error while Inserting";
				System.err.println(e.getMessage());		
			}
			return output;
		}
		
		//Read funds in the database
		public String readCart() 
		{	
			String output = "";
			
			try {
				Connection conn = connect();
				
				if(conn == null) {	
					return "Error while connecting to the database for the reading";	
				}
				
				//prepare the html table to be displayed
				output = "<table border='1'>"
						+ "<tr>"
							+ "<th> ITEM CODE </th>"
							+ "<th> IETM NAME </th>"
							+ "<th> CUSTOMER EMAIL </th>"
							+ "<th> CUSTOMER ADDRESS </th>"
							+ "<th> CUSTOMER CONTACTNO </th>"
							+ "<th> ITEM AMOUNT </th>"
							+ "<th> UPDATE </th>"
							+ "<th> REMOVE </th>"
						+ "</tr>";
				
				String query = "SELECT * FROM cart";
		
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
		
				//iterate through the rows in the result set
				while(rs.next()) 
				{
					String CartID = Integer.toString(rs.getInt("id"));
					String CartCode = rs.getString("CartCode");
					String CartName = rs.getString("CartName");
					String CartEmail = rs.getString("CartEmail");
					String CartAddress = rs.getString("CartAddress");
					String CartContact = rs.getString("CartContact");
					String CartAmount = Double.toString(rs.getDouble("CartAmount"));
					
					//Add a row into the html table
					output += "<tr><td>" + CartCode + "</td>";
					output += "<td>" + CartName + "</td>";
					output += "<td>" + CartEmail + "</td>";
					output += "<td>" + CartAddress + "</td>";
					output += "<td>" + CartContact + "</td>";
					output += "<td>" + CartAmount + "</td>";
					
					//buttons
					output += "<td><form method='post' action='cart.jsp'>"
							+ "<input name='UPDATE' "
							+ " type='submit' value='Update' class='btn btn-warning'>"
							+ "<input name='CartID' type='hidden' "
							+ " value='" + CartID + "'>" + "</form></td>"
							
							+ "<td><form method='post' action='cart.jsp'>"
							+ "<input name='REMOVE'"
							+ " type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='CartID' type='hidden' "
							+ " value='" + CartID + "'>" + "</form></td></tr>";
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
}
