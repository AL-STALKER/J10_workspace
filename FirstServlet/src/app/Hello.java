package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListDepo test = new ListDepo();
		ArrayList<DepoBase> list = test.getList();
		long result = 0;
		
		String str = (String)request.getParameter("lday");
		if (str != null) {
			result = Long.valueOf(str);
		}
		test.read("Depo.txt");
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.print("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
	    out.print("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
	    out.print("<title>Deposits</title><link rel=\"stylesheet\" type=\"text/css\" href=\"depo.css\" />");
	    out.print("</head><body>");
	    out.print("<h1>Deposit List </h1>");
	    out.print("<table border=\"1\" cellspacing=\"0\">");
	    out.print("<tr><th>Start</th><th>Long</th><th>Sum</th><th>Rate</th></tr>");
	    for (DepoBase depo: list) {
	    	if(depo.getDayLong() > result) {
	    		out.print("<tr><td>" + depo.getStartDate() + "</td><td>" + depo.getDayLong() + "</td><td>" + depo.getSum() + "</td><td>" + depo.getInterestRate() + "</td></tr>");
	    	}
	    	//out.print("<tr><td>14.11.2012</td><td>40</td><td>10000.00</td><td>11.5</td></tr>");
	    }
	    out.print("</table>");
	    out.print("</body></html>");
	    request.getParameter("lday");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
