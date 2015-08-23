<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="tr.com.obss.model.Director"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Director director = (Director) request.getAttribute("director");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Director Operations</title>
</head>
<body>
	<form method="post" action="DirectorServlet">
		<table cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td class="label">First Name</td>
					<td><input type="text" name="first_name"
						value="<%=director.getFirst_name()%>" /></td>
				</tr>
				<tr>
					<td class="label">Last Name</td>
					<td><input type="text" name="last_name"
						value="<%=director.getLast_name()%>" /></td>
				</tr>
				<tr>
					<td class="label">Gender</td>
					<td><select name="gender">

							<option value="M">male</option>
							<option value="F">female</option>
					</select></td>
				</tr>
				<tr>
					<td class="label">Birth Date(yyyy-MM-dd)</td>
					<td><input type="text" name="birth_date"
						value="<%=director.getBirthDate()%>" /> <input type="hidden"
						name="id" value="<%=director.getId()%>" />
				</tr>

				<tr>
					<td class="label">&nbsp;</td>
					<td><input type="submit" name="action" value="Save Director" /></td>
					<td><input type="submit" name="action" value="Delete Director" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>