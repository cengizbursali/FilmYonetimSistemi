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
import tr.com.obss.model.Movie;

@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out = null;

	public MovieServlet() {
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

				Movie movie = db.getMovie(id);

				if (movie.getId() > 0) {
					request.setAttribute("movie", movie);
					// request.setAttribute("page_title", "Edit movie");
					jsp = "editmovie.jsp";
				} else {
					response.setStatus(404);
				}
			} else if (action.equals("create")) {
				Movie movie = new Movie();
				movie.setId(-1);
				request.setAttribute("movie", movie);
				// request.setAttribute("page_title", "Create movie");
				jsp = "editmovie.jsp";
			} else {
				ArrayList<Movie> movies = db.getMovies();
				request.setAttribute("movies", movies);
				// request.setAttribute("page_title", "List movies");
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
		Movie movie = new Movie();
		db.connect();

		if (action != null) {
			if (action.equals("Save Movie")) {
				String idStr = request.getParameter("id");
				String director_id = request.getParameter("director_id");
				String title = request.getParameter("title");
				String year = request.getParameter("year");
				String rating = request.getParameter("rating");
				String desc = request.getParameter("description");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					movie.setId(id);
				}
				int dirID = Integer.parseInt(director_id);
				movie.setDirector_id(dirID);
				movie.setTitle(title);
				int yer = Integer.parseInt(year);
				movie.setYear(yer);
				float rat = Float.parseFloat(rating);
				movie.setRating(rat);
				movie.setDescription(desc);

				if (movie.getId() > 0) {
					db.connect();
					db.updateMovie(movie);
				} else {
					db.connect();
					db.insertMovie(movie);
				}
			} else if (action.equals("Delete Movie")) {
				String idStr = request.getParameter("id");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					movie.setId(id);
				}

				if (movie.getId() > 0) {
					db.connect();
					db.deleteMovie(movie);
				}

			}
		}

		response.sendRedirect("MovieServlet?action=list.jsp");
	}

}
