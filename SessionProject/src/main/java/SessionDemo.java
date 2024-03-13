
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDemo
 */
@WebServlet("/SessionDemo1")
public class SessionDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create a session object if it is already not created.
		HttpSession session = request.getSession();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		// Get session creation time.
		Calendar createTime = Calendar.getInstance();
		createTime.setTimeInMillis(session.getCreationTime());
		String formattedCreateTime = sdf.format(createTime.getTime());
		
		// Get last access time of this web page.
		Calendar lastAccessTime = Calendar.getInstance();
		lastAccessTime.setTimeInMillis(session.getLastAccessedTime());
		String formattedLastAccessTime = sdf.format(lastAccessTime.getTime());
		
		String title = "Welcome to my website";
		int visitCount = 0;
		String id = "Jack";
		// Check if this is new comer on your web page.
		if (session.isNew()) {
			session.setAttribute("userID", "Jack");
		} else {
			title = "Welcome back!";
			visitCount = (int) session.getAttribute("visitCount");
			visitCount = visitCount + 1;
			id = (String) session.getAttribute("userID");
		}
		session.setAttribute("visitCount", visitCount);

		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

		out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
				+ "<body bgcolor=\"#e5f7c0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<h2 align=\"center\">Session Infomation</h2>\n" + "<table border=\"1\" align=\"center\">\n"
				+ "<tr bgcolor=\"#eadf8c\">\n" + "<th>Session info</th><th>value</th></tr>\n" + "<tr>\n"
				+ "  <td>id</td>\n" + "  <td>" + session.getId() + "</td></tr>\n" + "<tr>\n"
				+ "  <td>Creation Time</td>\n" + "  <td>" + formattedCreateTime + "  </td></tr>\n" + "<tr>\n"
				+ "  <td>Time of Last Access</td>\n" + "  <td>" + formattedLastAccessTime + "  </td></tr>\n" + "<tr>\n"
				+ "  <td>User ID</td>\n" + "  <td>" + id + "  </td></tr>\n" + "<tr>\n" + "  <td>Number of visits</td>\n"
				+ "  <td>" + visitCount + "</td></tr>\n" + "<tr>\n" + "  <td>"
				+ "<FORM method=\"get\" action=\"http://localhost:8080/SessionProject/logout\">"
				+ "<input type=\"submit\" value=\"Log Out\"> </FORM>" + "  </td></tr>\n" + "</table>\n" +

				"</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
