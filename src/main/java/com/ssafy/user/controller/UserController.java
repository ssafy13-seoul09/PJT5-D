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

    private static final int LOGIN_SESSION_TIMEOUT = 60 * 60;

    // JSP 경로
    private static final String PAGE_INDEX = "index.jsp";
    private static final String PAGE_MYPAGE = "/WEB-INF/user/mypage.jsp";
    private static final String PAGE_REGISTER = "/WEB-INF/user/register.jsp";
    private static final String PAGE_LOGIN = "/WEB-INF/user/login.jsp";
    private static final String PAGE_FOLLOWING = "/WEB-INF/user/following.jsp";
    private static final String PAGE_FAVORITE = "/WEB-INF/user/favorite.jsp";

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");

        switch (act) {
            case "loginform":
                doLoginform(req, resp);
                break;
            case "login":
                doLogin(req, resp);
                break;
            case "logout":
                doLogout(req, resp);
                break;
            case "registform":
                doRegistform(req, resp);
                break;
            case "regist":
                doRegist(req, resp);
                break;
            case "followlist":
                doFollowlist(req, resp);
                break;
            case "follow":
                doFollow(req, resp);
                break;
            case "checkfollow":
                doCheckFollow(req, resp);
                break;
            case "unfollow":
                doUnfollow(req, resp);
                break;
            case "likedvideolist":
                doLikedVideoList(req, resp);
                break;
            case "likevideo":
                doLikeVideo(req, resp);
                break;
            case "unlikevideo":
                doUnlikeVideo(req, resp);
                break;
            case "checklike":
                doCheckLike(req, resp);
                break;
            case "mypage":
                req.getRequestDispatcher(PAGE_MYPAGE).forward(req, resp);
                break;
            default:
                break;
        }
    }

    /**
     * 로그인 여부 확인
     * 로그인 되어 있지 않으면 로그인 페이지로 이동
     * 
     * @return 로그인 여부
     */
    private boolean checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginUser") == null) {
            req.setAttribute("msg", "로그인이 필요합니다.");
            req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
            return false;
        }
        return true;
    }

    private void setLoginUser(HttpServletRequest req, String id) {
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(LOGIN_SESSION_TIMEOUT);
        session.setAttribute("loginUser", id);
    }

    private String getLoginUser(HttpServletRequest req) {
        Object loginUser = req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return null;
        }
        return loginUser.toString();
    }

    private void doLoginform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (userService.authenticate(id, password)) {
            setLoginUser(req, id);
            resp.sendRedirect(PAGE_INDEX);
        } else {
            req.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
        }
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(PAGE_INDEX);
    }

    private void doRegistform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PAGE_REGISTER).forward(req, resp);
    }

    private void doRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (userService.userIdExists(id)) {
            req.setAttribute("msg", "이미 존재하는 아이디입니다.");
            req.getRequestDispatcher(PAGE_REGISTER).forward(req, resp);
            return;
        }

        userService.register(id, password);
        req.setAttribute("msg", "회원가입이 완료되었습니다. 로그인해주세요.");
        req.getRequestDispatcher(PAGE_LOGIN).forward(req, resp);
    }

    private void doFollowlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        req.setAttribute("followlist", userService.getFollowings(userId));
        req.getRequestDispatcher(PAGE_FOLLOWING).forward(req, resp);
    }

    // TODO: Follow/Unfollow Failure Handling
    private void doUnfollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String targetId = req.getParameter("id");
        String referer = req.getHeader("referer");
        userService.unfollow(userId, targetId);

        resp.sendRedirect(referer);
    }

    private void doFollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String targetId = req.getParameter("id");
        String referer = req.getHeader("referer");
        userService.follow(userId, targetId);

        resp.sendRedirect(referer);
    }

    private void doCheckFollow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = getLoginUser(req);
        String targetId = req.getParameter("id");
        resp.getWriter().print(userService.checkFollowing(userId, targetId));
    }

    // TODO: Consistent Naming
    private void doLikedVideoList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        req.setAttribute("likedvideolist", userService.getLikedVideos(userId));
        req.getRequestDispatcher(PAGE_FAVORITE).forward(req, resp);
    }

    // TODO: Like/Unlike Failure Handling
    private void doUnlikeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String videoId = req.getParameter("id");
        String referer = req.getHeader("referer");
        userService.unlikeVideo(userId, videoId);

        resp.sendRedirect(referer);
    }

    private void doLikeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String videoId = req.getParameter("id");
        String referer = req.getHeader("referer");
        userService.likeVideo(userId, videoId);

        resp.sendRedirect(referer);
    }

    private void doCheckLike(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = getLoginUser(req);
        String videoId = req.getParameter("id");
        resp.getWriter().print(userService.checkLikedVideo(userId, videoId));
    }
}
