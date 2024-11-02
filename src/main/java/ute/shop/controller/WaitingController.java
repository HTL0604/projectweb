package ute.shop.controller;

import java.io.IOException;

import ute.shop.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/waiting" })
public class WaitingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("account") != null) {
			User u = (User) session.getAttribute("account");
			request.setAttribute("firstname", u.getFirstname());
			request.setAttribute("lastname", u.getLastname());
			if (u.getRole().toString() == "ADMIN") {
				response.sendRedirect(request.getContextPath() + "/admin/home");
			} else if (u.getRole().toString() == "VENDOR") {
				response.sendRedirect(request.getContextPath() + "/vendor/home");
			}else if (u.getRole().toString() == "USER") {
				response.sendRedirect(request.getContextPath() + "/user/home");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/views/Home.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
}
