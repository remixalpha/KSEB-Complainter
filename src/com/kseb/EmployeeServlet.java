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

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String query = "insert into staff_personal_details(staff_firstname,staff_lastname,staff_email,staff_phone_no,staff_address,staff_city,staff_pincode) values(?,?,?,?,?,?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		String employeeFirstName = req.getParameter("employeeFirstName");
		String employeeLastName = req.getParameter("employeeLastName");
		String employeeEmail = req.getParameter("employeeEmail");
		String employeePhoneNo = req.getParameter("employeePhoneNo");
		String employeeAddress = req.getParameter("employeeAddress");
		String employeeCity = req.getParameter("employeeCity");
		String employeePincode = req.getParameter("employeePincode");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///kseb", "root", "Mysql@Pass");
				PreparedStatement ps = con.prepareStatement(query);) {

			ps.setString(1, employeeFirstName);
			ps.setString(2, employeeLastName);
			ps.setString(3, employeeEmail);
			ps.setString(4, employeePhoneNo);
			ps.setString(5, employeeAddress);
			ps.setString(6, employeeCity);
			ps.setString(7, employeePincode);

		

			int count = ps.executeUpdate();
			if (count == 1) {
				pw.println("<script>alert('Employee registered successfully');</script>");
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