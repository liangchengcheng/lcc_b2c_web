<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=context%>>/manager/addproduct" method="post"
		enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>商品名称:
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>商品价格:
				<td><input type="text" name="price"></td>
			</tr>

			<tr>
				<td>商品描述:
				<td><input type="text" name="description"></td>
			</tr>
			<tr>
				<td>商品图片:
				<td><input type="file" name="file"></td>
			</tr>
		</table>
		
		<input type="submit" value="提交">
		<input type="reset" value="重置">
	</form>
</body>
</html>