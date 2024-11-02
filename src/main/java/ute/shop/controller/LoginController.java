package ute.shop.controller;

import jakarta.servlet.RequestDispatcher;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ute.shop.entity.User;

import java.io.IOException;
import ute.shop.services.IUserService;
import ute.shop.utils.Constant;
import ute.shop.services.impl.UserService;

@WebServlet(urlPatterns = { "/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// lấy toàn bộ function trong IUserService
	IUserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// lấy tham số từ views
		String email = request.getParameter("email").trim();
		String password = request.getParameter("pass").trim();
		String remember = request.getParameter("remember").trim();
		boolean isRememberMe = false;
		// kiểm tra Parameter
		if ("on".equals(remember)) {
			isRememberMe = true;
		}
	String alertMsg = "";
		if (email.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/Login.jsp").forward(request, response);
			return;
		}
		// xử lí Data
		User user = service.login(email, password);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("account", user);
			if (isRememberMe) {
				saveRemeberMe(response, email);
			}
			response.sendRedirect(request.getContextPath() + "/waiting");
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/views/Login.jsp").forward(request, response);
		}
	}

	private void saveRemeberMe(HttpServletResponse response, String username) {

		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
	}
}
