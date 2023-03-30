package org.project1_mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete")

public class deletepage extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
	
		Connection con=null;
		Statement st=null;
		PreparedStatement pst=null;
		
		String qry=" delete from  project1  where id=?";	
		try
		{
			Class.forName(UserUtility.DRIVER);
			con=DriverManager.getConnection(UserUtility.URL,UserUtility.USER,UserUtility.PASSWORD);
			pst=con.prepareStatement(qry);
			pst.setInt(1, id);		
			pst.executeUpdate();
			PrintWriter writer=resp.getWriter();
			writer.write("<html><body><h1> Deleted Successfully</h1></body></html>");
	   
		}
		 catch(ClassNotFoundException | SQLException e)
		 {
			e.printStackTrace();
		}
		finally {
			if(con!=null)
			{
				try 
				{
					con.close();
					System.out.println("Connection is closed");
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(st!=null)
			{
				try {
					st.close();
					System.out.println("Statement is closed");
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
