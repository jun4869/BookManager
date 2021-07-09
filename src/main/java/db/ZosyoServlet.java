package db;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ZosyoServlet")
public class ZosyoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ZosyoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String item = request.getParameter("item");
		String order = request.getParameter("order");
		String submit = request.getParameter("submit");
		String newbookcode = request.getParameter("newbookcode");
		String newstorecode = request.getParameter("newstorecode");
		String point = request.getParameter("point");
		String message = request.getParameter("message");
		String deleteid = request.getParameter("deleteid");

		if(submit != null) {
			if(submit.equals("並び替え")) {
			}else if(submit.equals("登録")) {
				insert(newbookcode, newstorecode,point,message);
			}else if(submit.equals("削除")) {
				delete(deleteid);
			}
		}
		selectAll(request, response, item, order);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/zosyo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String key1 = request.getParameter("key1");
		String key2 = request.getParameter("key2");
		String submit = request.getParameter("search");
		
		if(submit.equals("検索")) {
			search(request,response,key1,key2);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		dispatcher.forward(request, response);
	}
	
	void selectAll(HttpServletRequest request, HttpServletResponse response, String item, String order) {
		ZosyoDAO zosyoDAO = new ZosyoDAO();
		List<Zosyo> list = zosyoDAO.findALL(item, order);
		request.setAttribute("list", list);
	}

	void insert(String newbooknumber, String newstorecode, String point, String message) {
		try {
			ZosyoDAO zosyoDAO = new ZosyoDAO();
			zosyoDAO.insert(newbooknumber, newstorecode,point,message);
		}catch(NumberFormatException e) {
			System.out.println("不正な番号または市コードがにゅうりょくされました" + e.getMessage());
		}
	}

	void delete(String deleteid) {
		try {
			int id = Integer.parseInt(deleteid);
			ZosyoDAO zosyoDAO = new ZosyoDAO();
			zosyoDAO.delete(id);
		}catch(NumberFormatException e) {
			System.out.println("不正なIDがにゅりょくされました" + e.getMessage());
		}
	}
	
	void search(HttpServletRequest request, HttpServletResponse response,String key1, String key2) {
			ZosyoDAO zosyoDAO = new ZosyoDAO();
			List<Zosyo> list2 = zosyoDAO.search(key1, key2);
			request.setAttribute("list", list2);
	}
}
