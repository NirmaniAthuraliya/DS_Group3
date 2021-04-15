package model.PAF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {
    //Connect to the database
	private Connection connect() {
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund_management","root", "");
			
			//for testing
			//System.out.println("Successfully connected");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//Insert funds 
	public String insertFunds(String FundCode, String FundName , String FundEmail , String FundAddress, String FundContact, String FundAmount) 
	{
		String output = "";
		
		try {
			Connection conn = connect();
			
			if(conn == null) {
				return "Error while connecting to the database for Inserting";
			}
			
			//create a prepared statement
			String query = "INSERT INTO fund(id, fundCode, fundName, fundEmail, fundAddress, fundContact, fundAmount)" 
			+ " values(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,FundCode);
			preparedStmt.setString(3,FundName);
			preparedStmt.setString(4,FundEmail);
			preparedStmt.setString(5,FundAddress);
			preparedStmt.setString(6,FundContact);
			preparedStmt.setDouble(7,Double.parseDouble(FundAmount));
			
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
	public String readFunds() 
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
						+ "<th> FUND CODE </th>"
						+ "<th> FUND NAME </th>"
						+ "<th> FUND EMAIL </th>"
						+ "<th> FUND ADDRESS </th>"
						+ "<th> FUND CONTACTNO </th>"
						+ "<th> FUND AMOUNT </th>"
						+ "<th> UPDATE </th>"
						+ "<th> REMOVE </th>"
					+ "</tr>";
			
			String query = "SELECT * FROM fund";
	
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	
			//iterate through the rows in the result set
			while(rs.next()) 
			{
				String fundID = Integer.toString(rs.getInt("id"));
				String fundCode = rs.getString("fundCode");
				String fundName = rs.getString("fundName");
				String fundEmail = rs.getString("fundEmail");
				String fundAddress = rs.getString("fundAddress");
				String fundContact = rs.getString("fundContact");
				String fundAmount = Double.toString(rs.getDouble("fundAmount"));
				
				//Add a row into the html table
				output += "<tr><td>" + fundCode + "</td>";
				output += "<td>" + fundName + "</td>";
				output += "<td>" + fundEmail + "</td>";
				output += "<td>" + fundAddress + "</td>";
				output += "<td>" + fundContact + "</td>";
				output += "<td>" + fundAmount + "</td>";
				
				//buttons
				output += "<td><form method='post' action='Fund.jsp'>"
						+ "<input name='UPDATE' "
						+ " type='submit' value='Update' class='btn btn-warning'>"
						+ "<input name='fundID' type='hidden' "
						+ " value='" + fundID + "'>" + "</form></td>"
						
						+ "<td><form method='post' action='Fund.jsp'>"
						+ "<input name='REMOVE'"
						+ " type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='fundID' type='hidden' "
						+ " value='" + fundID + "'>" + "</form></td></tr>";
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
