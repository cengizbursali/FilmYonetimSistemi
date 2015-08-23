package tr.com.obss.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import tr.com.obss.model.Actor;
import tr.com.obss.model.Director;
import tr.com.obss.model.Genre;
import tr.com.obss.model.Movie;

public class Database {
	private boolean connected = false;
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;

	private String host = "localhost";
	private String port = "3306";
	private String schema = "jss2015istdb";
	private String user = "root";
	private String password = "cengiz-82";

	public Database() {
	}

	public void connect() {
		String connString = "jdbc:mysql://" + host + ":" + port + "/" + schema;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = (Connection) DriverManager.getConnection(connString, user, password);
			connected = true;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actors = new ArrayList<Actor>();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM actor");

			while (rs.next()) {
				Actor actor = new Actor();

				actor.setId(rs.getInt("id"));
				actor.setFirst_name(rs.getString("first_name"));
				actor.setLast_name(rs.getString("last_name"));
				actor.setGender(rs.getString("gender"));
				actor.setRating(rs.getFloat("rating"));
				actor.setBio(rs.getString("bio"));

				actors.add(actor);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return actors;
	}

	public Actor getActor(int id) {
		Actor actor = new Actor();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM actor WHERE id = " + id);

			if (rs.next()) {
				actor.setId(rs.getInt("id"));
				actor.setFirst_name(rs.getString("first_name"));
				actor.setLast_name(rs.getString("last_name"));
				actor.setGender(rs.getString("gender"));
				actor.setRating(rs.getFloat("rating"));
				actor.setBio(rs.getString("bio"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return actor;
	}

	public boolean actorVsMovie(Actor actor) {

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM movie_actor_map WHERE actor_id = " + actor.getId());
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean directorVsMovie(Director director) {
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM movie WHERE director_id = " + director.getId());
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean genreVsMovie(Genre genre) {
		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM movie_genre_map WHERE genre_id = " + genre.getId());
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public void insertActor(Actor actor) {
		String sql = "INSERT INTO actor " + " (first_name, last_name, gender, rating,bio) " + " VALUES (?,?, ?, ?, ?) ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setString(1, actor.getFirst_name());
			stmt.setString(2, actor.getLast_name());
			stmt.setString(3, actor.getGender());
			stmt.setFloat(4, actor.getRating());
			stmt.setString(5, actor.getBio());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateActor(Actor actor) {
		String sql = "UPDATE actor " + " SET first_name = ?, last_name = ?, gender = ?, rating= ?, bio=? "
				+ " WHERE id = ? ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setString(1, actor.getFirst_name());
			stmt.setString(2, actor.getLast_name());
			stmt.setString(3, actor.getGender());
			stmt.setFloat(4, actor.getRating());
			stmt.setString(5, actor.getBio());
			stmt.setInt(6, actor.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteActor(Actor actor) {

		String sql = "DELETE from actor " + " WHERE id = ? ";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setInt(1, actor.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteDirector(Director director) {
		String sql = "DELETE from director " + " WHERE id = ? ";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setInt(1, director.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteGenre(Genre genre) {
		String sql = "DELETE from genre " + " WHERE id = ? ";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setInt(1, genre.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteMovie(Movie movie) {
		String sql = "DELETE from movie " + " WHERE id = ? ";
		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setInt(1, movie.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertDirector(Director director) {
		String sql = "INSERT INTO director " + " (first_name, last_name, gender, birth_date) "
				+ " VALUES (?, ?, ?, ?) ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setString(1, director.getFirst_name());
			stmt.setString(2, director.getLast_name());
			stmt.setString(3, director.getGender());
			stmt.setDate(4, (Date) director.getBirthDate());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateDirector(Director director) {
		String sql = "UPDATE director " + " SET first_name = ?, last_name = ?, gender = ?, birth_date= ? "
				+ " WHERE id = ? ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setString(1, director.getFirst_name());
			stmt.setString(2, director.getLast_name());
			stmt.setString(3, director.getGender());
			stmt.setDate(4, (Date) director.getBirthDate());
			stmt.setInt(5, director.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Director getDirector(int id) {
		Director director = new Director();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM director WHERE id = " + id);

			if (rs.next()) {
				director.setId(rs.getInt("id"));
				director.setFirst_name(rs.getString("first_name"));
				director.setLast_name(rs.getString("last_name"));
				director.setGender(rs.getString("gender"));
				director.setBirthDate(rs.getDate("birth_date"));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return director;
	}

	public ArrayList<Director> getDirectors() {
		ArrayList<Director> directors = new ArrayList<Director>();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM director");

			while (rs.next()) {
				Director director = new Director();

				director.setId(rs.getInt("id"));
				director.setFirst_name(rs.getString("first_name"));
				director.setLast_name(rs.getString("last_name"));
				director.setGender(rs.getString("gender"));
				director.setBirthDate(rs.getDate("birth_date"));

				directors.add(director);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return directors;
	}

	public ArrayList<Movie> getMovies() {
		ArrayList<Movie> movies = new ArrayList<Movie>();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM movie");

			while (rs.next()) {
				Movie movie = new Movie();

				movie.setId(rs.getInt("id"));
				movie.setDirector_id(rs.getInt("director_id"));
				movie.setTitle(rs.getString("title"));
				movie.setYear(rs.getInt("year"));
				movie.setRating(rs.getFloat("rating"));
				movie.setDescription(rs.getString("description"));

				movies.add(movie);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return movies;
	}

	public Movie getMovie(int id) {
		Movie movie = new Movie();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM movie WHERE id = " + id);

			if (rs.next()) {
				movie.setId(rs.getInt("id"));
				movie.setDirector_id(rs.getInt("director_id"));
				movie.setTitle(rs.getString("title"));
				movie.setYear(rs.getInt("year"));
				movie.setRating(rs.getFloat("rating"));
				movie.setDescription(rs.getString("description"));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return movie;
	}

	public void updateMovie(Movie movie) {
		String sql = "UPDATE movie " + " SET director_id = ?, title = ?, year = ?, rating= ?, description=? "
				+ " WHERE id = ? ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setInt(1, movie.getDirector_id());
			stmt.setString(2, movie.getTitle());
			stmt.setInt(3, movie.getYear());
			stmt.setFloat(4, movie.getRating());
			stmt.setString(5, movie.getDescription());
			stmt.setInt(6, movie.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertMovie(Movie movie) {
		String sql = "INSERT INTO movie " + " (director_id, title, year, rating, description) "
				+ " VALUES (?, ?, ?, ?, ?) ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setInt(1, movie.getDirector_id());
			stmt.setString(2, movie.getTitle());
			stmt.setInt(3, movie.getYear());
			stmt.setFloat(4, movie.getRating());
			stmt.setString(5, movie.getDescription());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertGenre(Genre genre) {
		String sql = "INSERT INTO genre " + " (name) " + " VALUES (?) ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setString(1, genre.getName());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateGenre(Genre genre) {
		String sql = "UPDATE genre " + " SET name = ? " + " WHERE id = ? ";

		PreparedStatement stmt;

		try {
			stmt = (PreparedStatement) con.prepareStatement(sql);

			stmt.setString(1, genre.getName());
			stmt.setInt(2, genre.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Genre getGenre(int id) {
		Genre genre = new Genre();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM genre WHERE id = " + id);

			if (rs.next()) {
				genre.setId(rs.getInt("id"));
				genre.setName(rs.getString("name"));

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return genre;
	}

	public ArrayList<Genre> getGenres() {
		ArrayList<Genre> genres = new ArrayList<Genre>();

		try {
			st = (Statement) con.createStatement();
			rs = st.executeQuery("SELECT * FROM genre");

			while (rs.next()) {
				Genre genre = new Genre();

				genre.setId(rs.getInt("id"));
				genre.setName(rs.getString("name"));

				genres.add(genre);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return genres;
	}

	public boolean isConnected() {
		return connected;
	}

}
