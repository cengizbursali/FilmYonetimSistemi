package tr.com.obss.jss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tr.com.obss.db.Database;
import tr.com.obss.model.Actor;
import tr.com.obss.model.Genre;

@WebServlet("/GenreServlet")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out = null;

	public GenreServlet() {
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

				Genre genre = db.getGenre(id);

				if (genre.getId() > 0) {
					request.setAttribute("genre", genre);
					// request.setAttribute("page_title", "Edit genre");
					jsp = "editgenre.jsp";
				} else {
					response.setStatus(404);
				}
			} else if (action.equals("create")) {
				Genre genre = new Genre();
				genre.setId(-1);
				request.setAttribute("genre", genre);
				// request.setAttribute("page_title", "Create genre");
				jsp = "editgenre.jsp";
			} else {
				ArrayList<Genre> genres = db.getGenres();
				request.setAttribute("genres", genres);
				// request.setAttribute("page_title", "List genres");
				jsp = "list.jsp";
			}

			request.getRequestDispatcher(jsp).include(request, response);
		} else {
			out.write("DB connection failed!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Database db = new Database();
		Genre genre = new Genre();
		db.connect();

		if (action != null) {
			if (action.equals("Save Genre")) {
				String idStr = request.getParameter("id");
				String name = request.getParameter("name");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					genre.setId(id);
				}

				genre.setName(name);

				if (genre.getId() > 0) {
					db.connect();
					db.updateGenre(genre);
				} else {
					db.connect();
					db.insertGenre(genre);
				}
			} else if (action.equals("Delete Genre")) {
				String idStr = request.getParameter("id");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					genre.setId(id);
				}
				if (!db.genreVsMovie(genre)) {

					if (genre.getId() > 0) {
						db.connect();
						db.deleteGenre(genre);
					}
				} else
					out.print("Bu film türü bir film ile iliskilendirilmis silemezsin!");
			}
		}

		response.sendRedirect("GenreServlet?action=list.jsp");
	}

}
