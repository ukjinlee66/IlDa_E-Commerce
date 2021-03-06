

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybean.db.userDAO;
import mybean.db.userVO;

@WebServlet("/signUpServlet")
public class signUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public signUpServlet() {}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd1");
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		Date birth;
		if(date.equals("")) {
			birth = null;
		}
		else {
			birth = Date.valueOf(date);
		}
		String userPhoneNumber = request.getParameter("phone");
		String userAddr = request.getParameter("address");
		String userEmail = request.getParameter("email");
		String userGender = request.getParameter("gender");
		
		userVO usr = new userVO();
		usr.setUserId(id);
		usr.setUserName(name);
		usr.setUserPwd(pwd);
		usr.setUserBirth(birth);
		usr.setUserPhoneNumber(userPhoneNumber);
		usr.setUserAddr(userAddr);
		usr.setUserEmail(userEmail);
		usr.setUserGender(userGender);
		
		try {
			userDAO db = userDAO.getInstance();
			db.insertRecord(usr);
//			db.disConnect();
		}catch(SQLException e) {
			System.out.print(e);
			return;
		} catch (ClassNotFoundException e) {
			System.out.print(e);
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		response.sendRedirect("signupSuccess.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}