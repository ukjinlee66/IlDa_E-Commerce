

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.db.commentDAO;
import mybean.db.commentVO;

@WebServlet("/commentAddServlet")
public class commentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public commentAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("id");
		String contents = request.getParameter("contents");
		int noticeNumber = Integer.parseInt(request.getParameter("noticeNumber"));
		
		try {
			commentDAO comment = commentDAO.getInstance();
			comment.insertRecord(new commentVO(contents,noticeNumber,userId));
//			comment.disConnect();
			response.sendRedirect(request.getHeader("referer"));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
