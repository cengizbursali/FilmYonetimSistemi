<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login Control Form</title>
</head>
<body>
	<%
		String kullaniciadi = request.getParameter("adi");

		String kullanicisifre = request.getParameter("sifre");

		if (kullaniciadi.equals("cengiz") && kullanicisifre.equals("1234")) {

			response.sendRedirect("./list.jsp");
		} else {
			out.println("Wrong username or password!");
		}
	%>
	<form Action=giris.jsp>
		<input type="submit" value="back">
	</form>


</body>
</html>