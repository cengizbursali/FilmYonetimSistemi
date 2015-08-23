package tr.com.obss.jss;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.com.obss.db.Database;
import tr.com.obss.model.Actor;
import tr.com.obss.model.Director;

@WebServlet("/DirectorServlet")
public class DirectorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out = null;

	public DirectorServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		String jsp = "";
		if (action == null)
			action = "list";

		Database db = new Database();
		db.connect();
		if (db.isConnected()) {
			if (action.equals("edit")) {
				String idStr = request.getParameter("id");
				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
				}

				Director director = db.getDirector(id);

				if (director.getId() > 0) {
					request.setAttribute("director", director);
					// request.setAttribute("page_title", "Edit Actor");
					jsp = "editdirector.jsp";
				} else {
					response.setStatus(404);
				}
			} else if (action.equals("create")) {
				Director director = new Director();
				director.setId(-1);
				request.setAttribute("director", director);
				// request.setAttribute("page_title", "Create Director");
				jsp = "editdirector.jsp";
			} else {
				ArrayList<Director> directors = db.getDirectors();
				request.setAttribute("directors", directors);
				// request.setAttribute("page_title", "List Directors");
				jsp = "list.jsp";
			}

			request.getRequestDispatcher(jsp).include(request, response);
		} else {
			out.write("DB connection failed!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String action = request.getParameter("action");
		Database db = new Database();
		Director director = new Director();
		db.connect();

		if (action != null) {
			if (action.equals("Save Director")) {
				String idStr = request.getParameter("id");
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String gender = request.getParameter("gender");
				String birth_date = request.getParameter("birth_date");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					director.setId(id);
				}

				director.setFirst_name(firstName);
				director.setLast_name(lastName);
				director.setGender(gender);
				java.sql.Date a = null;
				java.util.Date bd = null;
				try {
					bd = (java.util.Date) formatter.parse(birth_date);
					a = new java.sql.Date(bd.getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				director.setBirthDate(a);

				if (director.getId() > 0) {
					db.connect();
					db.updateDirector(director);
				} else {
					db.connect();
					db.insertDirector(director);
				}
			} else if (action.equals("Delete Director")) {
				String idStr = request.getParameter("id");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					director.setId(id);
				}
				if (!db.directorVsMovie(director)) {

					if (director.getId() > 0) {
						db.connect();
						db.deleteDirector(director);
					}
				} else
					out.println("Bu director bir film ile iliskilendirilmis silemezsin!");
			}
		}

		response.sendRedirect("DirectorServlet?action=list.jsp");
	}

}
