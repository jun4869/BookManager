package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZosyoDAO {
	static {
		try {
			Class.forName("org.h2.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private final String url = "jdbc:h2:tcp://localhost/./example";


	public List<Zosyo> findALL(String item, String order){
		List<Zosyo> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "SELECT ID,書籍名,著者名,発行年,出版社,点数,感想 FROM PURCHASE a INNER JOIN BOOK b ON a.書籍コード=b.書籍コード INNER JOIN STORE c ON a.店舗コード=c.店舗コード INNER JOIN AUTHOR d ON b.著者コード = d.著者コード";
			if(item != null && order != null) {
				String s = " ORDER BY "+item;
				if(order.equals("desc")) {
					s += " DESC";
				}
				sql += s;
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String bookname = rs.getString("書籍名");
				String authorname = rs.getString("著者名");
				int year = rs.getInt("発行年");
				String storename = rs.getString("出版社");
				String point = rs.getString("点数");
				String text = rs.getString("感想");
				Zosyo z = new Zosyo(id, bookname, authorname, year, storename, point, text);
				list.add(z);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn !=null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return list;
	}

	public boolean insert(String bookcode, String storecode,String point,String message) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "INSERT INTO PURCHASE(書籍コード, 店舗コード, 点数, 感想) VALUES(?,?,?,?)";
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setString(1,  bookcode);;
			pre.setString(2, storecode);
			pre.setString(3, point);	
			pre.setString(4, message);
			int result = pre.executeUpdate();
			if(result == 1)return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean delete(int id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "DELETE FROM PURCHASE WHERE ID = ?";
			PreparedStatement pre = conn.prepareStatement(sql);

			pre.setInt(1,  id);
			int result = pre.executeUpdate();
			if(result == 1)return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public List<Zosyo> search(String key1, String key2) {
		List<Zosyo> list2 = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "user", "pass");
			String sql = "SELECT ID,書籍名,著者名,発行年,出版社,点数,感想 FROM PURCHASE a INNER JOIN BOOK b ON a.書籍コード=b.書籍コード INNER JOIN STORE c ON a.店舗コード=c.店舗コード INNER JOIN AUTHOR d ON b.著者コード = d.著者コード";
			if(key1 != null && key2 != null) {
				String s = " WHERE " +key1 + " = " +"'" + key2 + "'";
				sql += s;
			}
			PreparedStatement pre = conn.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String bookname = rs.getString("書籍名");
				String authorname = rs.getString("著者名");
				int year = rs.getInt("発行年");
				String storename = rs.getString("出版社");
				String point = rs.getString("点数");
				String text = rs.getString("感想");
				Zosyo x = new Zosyo(id, bookname, authorname, year, storename, point, text);
				list2.add(x);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return list2;
	}
}

