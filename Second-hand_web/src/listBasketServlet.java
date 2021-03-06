

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.db.basketDAO;
import mybean.db.basketListVO;
import mybean.db.noticeVO;

@WebServlet("/listBasketServlet")
public class listBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public listBasketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		try {
			basketDAO basket = basketDAO.getInstance();
			HttpSession session = request.getSession();
			List<noticeVO> li = basket.listBasket2((int)session.getAttribute("userNumber"));
//			basket.disConnect();
			RequestDispatcher rd = request.getRequestDispatcher("BasketList.jsp");
			request.setAttribute("basketList", li);
			request.setAttribute("basket", "1");
			rd.forward(request,response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}