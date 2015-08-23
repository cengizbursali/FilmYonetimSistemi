<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="tr.com.obss.model.Actor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Actor actor = (Actor) request.getAttribute("actor");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Actor Operations</title>
</head>
<body>

	<form method="post" action="ActorServlet">
		<table cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="label">First Name</td>
					<td><input type="text" name="first_name"
						value="<%=actor.getFirst_name()%>" /></td>
				</tr>
				<tr>
					<td class="label">Last Name</td>
					<td><input type="text" name="last_name"
						value="<%=actor.getLast_name()%>" /></td>
				</tr>
				<tr>
					<td class="label">Gender</td>
					<td><select name="gender">

							<option value="M">male</option>
							<option value="F">female</option>
					</select></td>
				</tr>
				<tr>
					<td class="label">Rating</td>
					<td><input type="text" name="rating"
						value="<%=actor.getRating()%>" /></td>
				</tr>
				<tr>
					<td class="label">Biography</td>
					<td><textarea name="bio"><%=actor.getBio()%></textarea> <input
						type="hidden" name="id" value="<%=actor.getId()%>" /></td>
				</tr>
				<tr>
					<td class="label">&nbsp;</td>
					<td><input type="submit" name="action" value="Save Actor" />
						<input type="submit" name="action" value="Delete Actor" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>