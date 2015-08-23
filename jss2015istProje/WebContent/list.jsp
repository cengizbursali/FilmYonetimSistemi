<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="tr.com.obss.model.*"%>
<%@ page import="tr.com.obss.db.Database"%>
<%@ page import="tr.com.obss.jss.ActorServlet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Database db = new Database();
	db.connect();
	ArrayList<Actor> actors = db.getActors();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Film Yönetim Sistemi</title>
<style>
table {
	margin: 20px;
}

table td {
	border: 1px solid #0000FF;
	padding: 4px;
}

table thead td {
	font-weight: bold;
	background: #FFEEA9
}

div {
	margin: 20px;
}
</style>
</head>
<body>
	<div>
		<h2>All of List</h2>
	</div>

	<div>
		<h3>Actor</h3>
	</div>
	<table cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Gender</td>
				<td>Rating</td>
				<td>Biography</td>
			</tr>
		</thead>

		<tbody>
			<%
				for (Actor act : actors) {
			%>
			<tr>
				<td><a href="ActorServlet?action=edit&id=<%=act.getId()%>"
					title="Click here to edit or delete"><%=act.getFirst_name()%></a></td>
				<td><%=act.getLast_name()%></td>
				<td><%=act.getGender()%></td>
				<td><%=act.getRating()%></td>
				<td><%=act.getBio()%></td>

				<%
					}
				%>
			
		</tbody>
	</table>

	<div>
		<a href="ActorServlet?action=create"
			title="Click here to insert new actor">Insert Actor</a>
	</div>
	<%
		db.connect();
		ArrayList<Director> directors = db.getDirectors();
	%>

	<div>
		<h3>Director</h3>
	</div>
	<table cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Gender</td>
				<td>Birth Date</td>
			</tr>
		</thead>

		<tbody>
			<%
				for (Director dir : directors) {
			%>
			<tr>
				<td><a href="DirectorServlet?action=edit&id=<%=dir.getId()%>"
					title="Click here to edit or delete"><%=dir.getFirst_name()%></a></td>
				<td><%=dir.getLast_name()%></td>
				<td><%=dir.getGender()%></td>
				<td><%=dir.getBirthDate()%></td>


				<%
					}
				%>
			
		</tbody>
	</table>

	<div>
		<a href="DirectorServlet?action=create"
			title="Click here to insert new director">Insert Director</a>
	</div>

	<%
		db.connect();
		ArrayList<Movie> movies = db.getMovies();
	%>
	<div>
		<h3>Movie</h3>
	</div>
	<table cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td>Director Id</td>
				<td>Title</td>
				<td>Year</td>
				<td>Rating</td>
				<td>Description</td>
			</tr>
		</thead>

		<tbody>
			<%
				for (Movie dir : movies) {
			%>
			<tr>
				<td><a href="MovieServlet?action=edit&id=<%=dir.getId()%>"
					title="Click here to edit or delete"><%=dir.getDirector_id()%></a></td>
				<td><%=dir.getTitle()%></td>
				<td><%=dir.getYear()%></td>
				<td><%=dir.getRating()%></td>
				<td><%=dir.getDescription()%></td>


				<%
					}
				%>
			
		</tbody>
	</table>

	<div>
		<a href="MovieServlet?action=create"
			title="Click here to insert new movie">Insert Movie</a>
	</div>
	<%
		db.connect();
		ArrayList<Genre> genres = db.getGenres();
	%>

	<div>
		<h3>Genre</h3>
	</div>
	<table cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td>Name</td>
			</tr>
		</thead>

		<tbody>
			<%
				for (Genre gnr : genres) {
			%>
			<tr>
				<td><a href="GenreServlet?action=edit&id=<%=gnr.getId()%>"
					title="Click here to edit or delete"><%=gnr.getName()%></a></td>


				<%
					}
				%>
			
		</tbody>
	</table>

	<div>
		<a href="GenreServlet?action=create"
			title="Click here to insert new genre">Insert Genre</a>
	</div>
	<form Action=giris.jsp>
		<input type="submit" value="log out">
	</form>


</body>
</html>