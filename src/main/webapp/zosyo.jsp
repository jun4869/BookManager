<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="db.Zosyo, java.util.List" %>
<%
List<Zosyo> zosyoList = (List<Zosyo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>蔵書管理アプリ</title>
</head>
<body>
<h1><a href="ZosyoServlet">蔵書管理</a></h1>
<form action="ZosyoServlet" method="post">
<select name=key1>
<option value="書籍名">書籍名</option>
<option value="著者名">著者名</option>
<option value="出版社">出版社</option>
</select>
<input type="text" name="key2">
<input type="submit" name="search" value="検索">
</form><br>
<form action="ZosyoServlet">
<input type="radio" name="item" value="ID" checked="checked">ID
<input type="radio" name="item" value="書籍名">書籍名
<br>
<input type="radio" name="order" value="asc" checked="checked">昇順
<input type="radio" name="order" value="desc">降順
<br>
<input type="submit" name="submit" value="並び替え">
<hr>
書籍コード<input type="text" name="newbookcode">
店舗コード<input type="text" name="newstorecode"><br>
評価<select name="point">
<option value="☆">☆</option>
<option value="☆☆">☆☆</option>
<option value="☆☆☆">☆☆☆</option>
<option value="☆☆☆☆">☆☆☆☆</option>
<option value="☆☆☆☆☆">☆☆☆☆☆</option>
</select>
<br>
<textarea  name="message" placeholder="感想を入力"></textarea>
<input type="submit" name="submit" value="登録">
<hr>
ID<input type="text" name="deleteid">
<input type="submit" name="submit" value="削除">
</form>
<% if (zosyoList != null) { %>
<h2>書籍情報</h2>
<table border="1">
<tr><th>ID</th><th>書籍名</th><th>著者名</th><th>発行年</th><th>店舗名</th><th>評価</th><th>感想</th></tr>
<% for (Zosyo zosyo: zosyoList) { %>
<tr>
<td><%=zosyo.getId() %></td>
<td><%=zosyo.getBookname() %></td>
<td><%=zosyo.getAuthorname() %></td>
<td><%=zosyo.getYear() %></td>
<td><%=zosyo.getStorename() %></td>
<td><%=zosyo.getPoint() %></td>
<td><%=zosyo.getText() %></td>
</tr>
<% } %>
</table>
<% } %>
</body>
</html>