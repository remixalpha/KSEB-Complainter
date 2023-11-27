package com.kseb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminHomePageServlet", urlPatterns = {"/UniqueAdminHomePageServlet"})
public class AdminHomePageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
// Department & Designation
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    
        // Forward the request to the AdminHomePage.html page
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminHomePage.html");
        dispatcher.forward(request, response);
    }


}




   