<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="tr.com.obss.model.Genre"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Genre genre = (Genre) request.getAttribute("genre");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Genre Operations</title>
</head>
<body>
	<form method="post" action="GenreServlet">
		<table cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="label">Name</td>
					<td><input type="text" name="name"
						value="<%=genre.getName()%>" /></td>
					<input type="hidden" name="id" value="<%=genre.getId()%>" />

				</tr>

				<tr>
					<td class="label">&nbsp;</td>
					<td><input type="submit" name="action" value="Save Genre" />
						<input type="submit" name="action" value="Delete Genre" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>