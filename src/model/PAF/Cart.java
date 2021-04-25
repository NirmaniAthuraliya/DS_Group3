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

	//Insert into Cart
	public String insertCart(String prodCode, String prodName, String prodPrice, String quantity, String CusAddress, String CusContact, String CusEmail) 
	{
		String output = "";

		try {
			Connection conn = connect();

			if(conn == null) {
				return "Error while connecting to the database for Inserting";
			}

			//create a prepared statement
			String query = "insert into cart(id, prodCode, prodName, prodPrice, quantity, CusAddress, CusContact, CusEmail)" 
					+ " values(?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = conn.prepareStatement(query);

			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,prodCode);
			preparedStmt.setString(3,prodName);
			preparedStmt.setDouble(4,Double.parseDouble(prodPrice));
			preparedStmt.setString(5,quantity);
			preparedStmt.setString(6,CusAddress);
			preparedStmt.setString(7,CusContact);
			preparedStmt.setString(8,CusEmail);


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

	//Read products in the database
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
					+ "<th> PRODUCT CODE </th>"
					+ "<th> PRODUCT NAME </th>"
					+ "<th> PRODUCT PRICE </th>"
					+ "<th> QUANTITY </th>"
					+ "<th> CUSTOMER ADDRESS </th>"
					+ "<th> CUSTOMER CONTACT NO </th>"
					+ "<th> CUSTOMER EMAIL </th>"
					+ "<th> UPDATE </th>"
					+ "<th> REMOVE </th>"
					+ "</tr>";

			String query = "SELECT * FROM cart";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			//iterate through the rows in the result set
			while(rs.next()) 
			{
				String id = Integer.toString(rs.getInt("id"));
				String prodCode = rs.getString("prodCode");
				String prodName = rs.getString("prodName");
				String prodPrice = Double.toString(rs.getDouble("prodPrice"));
				String quantity = rs.getString("quantity");
				String CusAddress = rs.getString("CusAddress");
				String CusContact = rs.getString("CusContact");
				String CusEmail = rs.getString("CusEmail");

				//Add a row into the html table
				output += "<tr><td>" + prodCode + "</td>";
				output += "<td>" + prodName + "</td>";
				output += "<td>" + prodPrice + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + CusAddress + "</td>";
				output += "<td>" + CusContact + "</td>";
				output += "<td>" + CusEmail + "</td>";


				//buttons
				output += "<td><form method='post' action='cart.jsp'>"
						+ "<input name='UPDATE' "
						+ " type='submit' value='Update' class='btn btn-warning'>"
						+ "<input name='id' type='hidden' "
						+ " value='" + id + "'>" + "</form></td>"

							+ "<td><form method='post' action='cart.jsp'>"
							+ "<input name='REMOVE'"
							+ " type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='id' type='hidden' "
							+ " value='" + id + "'>" + "</form></td></tr>";
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

	//Update cart
	public String updateCart(String id, String prodCode, String prodName, String prodPrice, String quantity, String CusAddress, String CusContact, String CusEmail) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 

			// create a prepared statement
			String query = "UPDATE cart SET prodCode=?,prodName=?,prodPrice=?,quantity=?,CusAddress=?,CusContact=?,CusEmail=? WHERE id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values
			preparedStmt.setString(1, prodCode); 
			preparedStmt.setString(2, prodName);
			preparedStmt.setDouble(3, Double.parseDouble(prodPrice)); 
			preparedStmt.setString(4, quantity);
			preparedStmt.setString(5, CusAddress); 
			preparedStmt.setString(6, CusContact); 
			preparedStmt.setString(7, CusEmail); 
			preparedStmt.setInt(8, Integer.parseInt(id)); 

			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the cart."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 

	//delete products from the Cart
	public String removeCart(String id) {

		String output = "";

		try {

			Connection con = connect();

			if(con == null) {
				return "Error while connecting to the database for removing";
			}

			//create a prepared statement
			String query = " delete from cart where id = " 
					+ " ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			//binding values
			preparedStmt.setString(1,id);


			//execute the statement
			preparedStmt.execute();
			con.close();

			output = "Removed successfully";
		}
		catch(Exception e) {

			output = "Error while removing";
			System.err.println(e.getMessage());

		}

		return output;

	}
}
