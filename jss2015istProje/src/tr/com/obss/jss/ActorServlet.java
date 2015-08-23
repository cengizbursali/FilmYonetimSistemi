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

@WebServlet("/ActorServlet")
public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out = null;

	public ActorServlet() {
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

				Actor actor = db.getActor(id);

				if (actor.getId() > 0) {
					request.setAttribute("actor", actor);
					// request.setAttribute("page_title", "Edit Actor");
					jsp = "editactor.jsp";
				} else {
					response.setStatus(404);
				}
			} else if (action.equals("create")) {
				Actor actor = new Actor();
				actor.setId(-1);
				request.setAttribute("actor", actor);
				// request.setAttribute("page_title", "Create Actor");
				jsp = "editactor.jsp";
			} else {
				ArrayList<Actor> actors = db.getActors();
				request.setAttribute("actors", actors);
				// request.setAttribute("page_title", "List Actors");
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
		Actor actor = new Actor();
		db.connect();

		if (action != null) {
			if (action.equals("Save Actor")) {
				String idStr = request.getParameter("id");
				String firstName = request.getParameter("first_name");
				String lastName = request.getParameter("last_name");
				String gender = request.getParameter("gender");
				String rating = request.getParameter("rating");
				String bio = request.getParameter("bio");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					actor.setId(id);
				}

				actor.setFirst_name(firstName);
				actor.setLast_name(lastName);
				actor.setGender(gender);
				float rat = Float.parseFloat(rating);
				actor.setRating(rat);
				actor.setBio(bio);

				if (actor.getId() > 0) {
					db.connect();
					db.updateActor(actor);
				} else {
					db.connect();
					db.insertActor(actor);
				}
			} else if (action.equals("Delete Actor")) {

				String idStr = request.getParameter("id");

				int id = 0;

				if (idStr != null) {
					id = Integer.parseInt(idStr);
					actor.setId(id);
				}
				if (!db.actorVsMovie(actor)) {

					if (actor.getId() > 0) {
						db.connect();
						db.deleteActor(actor);
					}

				} else
					out.print("Bu actor bir film ile iliskilendirilmis silemezsin!");

			}
		}

		response.sendRedirect("ActorServlet?action=list.jsp");
	}

}
