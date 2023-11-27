package com.kseb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsumerServlet", urlPatterns = {"/ConsumerServlet"})
public class ConsumerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String query = "insert into consumer_details(consumer_firstname,consumer_lastname,consumer_email,consumer_phone_no,consumer_address,consumer_house_no,consumer_post_no,consumer_meter_no) values(?,?,?,?,?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		String consumerFirstName = req.getParameter("consumerFirstName");
		String consumerLastName = req.getParameter("consumerLastName");
		String consumerEmail = req.getParameter("consumerEmail");
		String consumerPhoneNo = req.getParameter("consumerPhoneNo");
		String consumerAddress = req.getParameter("consumerAddress");
		String consumerHouseNo = req.getParameter("consumerHouseNo");
		String consumerPostNo = req.getParameter("consumerPostNo");
		String consumerMeterNo = req.getParameter("consumerMeterNo");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///kseb", "root", "Mysql@Pass");
				PreparedStatement ps = con.prepareStatement(query);) {

			ps.setString(1, consumerFirstName);
			ps.setString(2, consumerLastName);
			ps.setString(3, consumerEmail);
			ps.setString(4, consumerPhoneNo);
			ps.setString(5, consumerAddress);
			ps.setString(6, consumerHouseNo);
			ps.setString(7, consumerPostNo);
			ps.setString(8, consumerMeterNo);
		

			int count = ps.executeUpdate();
			if (count == 1) {
				pw.println("<script>alert('Consumer registered successfully');</script>");
			} else {
				pw.println("<script>alert('Couldn't connect!');</script>");
			}
			
			// Redirect back to the same page
			response.sendRedirect("AdminHomePage");

		} catch (SQLException se) {
			pw.println("<h2>" + se.getMessage() + "<h2>");
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		doGet(req, response);
	}
}