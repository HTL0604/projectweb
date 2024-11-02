package ute.shop.controller;

import java.io.IOException;

import ute.shop.entity.User;
import ute.shop.services.IUserService;
import ute.shop.utils.Constant;
import ute.shop.services.impl.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/forgotpassword"})
public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/Forgotpassword.jsp");
		rd.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		IUserService service = new UserService();
		String phone = request.getParameter("phone").trim();
		String email = request.getParameter("email").trim();
		String alertMsg = "";
		if(email == null || phone == null) {
			alertMsg ="Phone hoặc Email không được rỗng!";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/Forgotpassword.jsp").forward(request, response);
			return;
		}else {
		User user = service.findbyEmail(email);
		if(!user.getEmail().equals(email) ) {
			alertMsg ="Không tìm thấy tài khoản!";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/Forgotpassword.jsp").forward(request, response);
			return;
		}else {
			alertMsg ="Mật khẩu của bạn là : " + user.getHashed_password();
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/Forgotpassword.jsp").forward(request, response);
			return;
		}
		}
	}
}
