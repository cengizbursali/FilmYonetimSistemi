<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="tr.com.obss.model.Movie"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Movie movie = (Movie) request.getAttribute("movie");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Operations</title>
</head>
<body>
	<form method="post" action="MovieServlet">
		<table cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="label">Director Id</td>
					<td><input type="text" name="director_id"
						value="<%=movie.getDirector_id()%>" /></td>
				</tr>
				<tr>
					<td class="label">Title</td>
					<td><input type="text" name="title"
						value="<%=movie.getTitle()%>" /></td>
				</tr>
				<tr>
					<td class="label">Year</td>
					<td><input type="text" name="year"
						value="<%=movie.getYear()%>" /></td>
				</tr>
				<tr>
					<td class="label">Rating</td>
					<td><input type="text" name="rating"
						value="<%=movie.getRating()%>" /></td>
				</tr>
				<tr>
					<td class="label">Description</td>
					<td><textarea name="description"><%=movie.getDescription()%></textarea>
						<input type="hidden" name="id" value="<%=movie.getId()%>" />
				</tr>

				<tr>
					<td class="label">&nbsp;</td>
					<td><input type="submit" name="action" value="Save Movie" /></td>
					<td><input type="submit" name="action" value="Delete Movie" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>