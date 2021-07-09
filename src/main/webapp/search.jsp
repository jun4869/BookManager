<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.Zosyo, java.util.List" %>
<%
List<Zosyo> searchList = (List<Zosyo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>蔵書管理アプリ</title>
</head>
<body>
<h1>検索一覧</h1>
<% if (searchList != null) { %>
<table border="1">
<tr><th>書籍名</th><th>著者名</th><th>発行年</th><th>店舗名</th><th>評価</th><th>感想</th></tr>
<% for (Zosyo search: searchList) { %>
<tr>
<td><%=search.getBookname() %></td>
<td><%=search.getAuthorname() %></td>
<td><%=search.getYear() %></td>
<td><%=search.getStorename() %></td>
<td><%=search.getPoint() %></td>
<td><%=search.getText() %></td>
</tr>
<% } %>
</table>
<% } %>
<a href="ZosyoServlet">戻る</a>
</body>
</html>