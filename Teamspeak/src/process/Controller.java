package process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Controller",urlPatterns={"/Controller"})

/**
 * Controls assigning ranks and assigning permissions.
 * @author Martin Borstad
 *
 */
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String MASTER_PASSWORD ="";

	private static Roster r = new Roster();
	private static Process p = new Process(r);
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Scanner sc = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/firsthalf.txt"));
		while(sc.hasNextLine())
		{
			out.println(sc.nextLine());
		}
		response.getWriter().println("You got to this page the wrong way, go back and use one of the forms.");
		sc = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/secondhalf.txt"));
		while(sc.hasNextLine())
		{
			out.println(sc.nextLine());
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get a map of the request parameters
		PrintWriter out = response.getWriter();
		Scanner sc = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/firsthalf.txt"));
		while(sc.hasNextLine())
		{
			out.println(sc.nextLine());
		}
		if(request.getParameter("password") != null  && request.getParameter("rank") == null)
		{
			p.md5(request.getParameter("password"));
			p.username = request.getParameter("username");
			String s = p.grantPermission();
			out.println(s != null ? "Your permission key is: " + s : "<P>Permission has NOT been given.  Was your WN password correct or are you not a member yet? Go back to the form <A HREF=\"../Teamspeak/login.jsp\">here</A></P>");
		}
		else
		{
			boolean output = true;
			if(!request.getParameter("password").equals(MASTER_PASSWORD))
			{
				response.getWriter().println("Wrong password");
				output = false;
			}
			else if(!request.getParameter("username").replace(" ", "").equals(""))
			{
				out.println("<P>Updated");
				out.println("<A HREF=\"../Teamspeak/assign.jsp\">Return to form</A></P>");
				r.updateRank(request.getParameter("username"), request.getParameter("rank"));
			}
			else
			{
				out.println("<A HREF=\"../Teamspeak/assign.jsp\">Return to form</A></P>");
			}
			if(output && request.getParameter("output") != null && !request.getParameter("output").isEmpty())
			{
				out.println(r.toString());
			}
		}
		sc.close();
		sc = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/secondhalf.txt"));
		while(sc.hasNextLine())
		{
			out.println(sc.nextLine());
		}
		sc.close();
	}

}
