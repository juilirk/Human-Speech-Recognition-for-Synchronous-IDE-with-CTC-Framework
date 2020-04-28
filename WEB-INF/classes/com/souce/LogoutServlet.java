package com.souce;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogoutServlet()
    {
        super();

    }


	public void init(ServletConfig config) throws ServletException 
	{

	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			HttpSession session=request.getSession();
			System.out.println("2nd session id="+session.getId());
			session.invalidate();
			session=null;
			//req.getRequestDispatcher("index.jsp").forward(req, res);
			
			response.sendRedirect("index.jsp");
			return;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
