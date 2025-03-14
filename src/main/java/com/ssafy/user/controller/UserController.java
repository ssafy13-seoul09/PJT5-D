package com.ssafy.user.controller;

import java.io.IOException;

import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = UserServiceImpl.getInstance();

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");

        switch (act) {
            case "login":
                doLogin(req, resp);
                break;
            default:
                break;
        }
    }

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (userService.isValid(id, password)) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(60 * 60);
            session.setAttribute("loginUser", id);
            resp.sendRedirect("index.jsp");
        } else {
            String msg = "아이디 또는 비밀번호가 일치하지 않습니다.";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
	}

}
