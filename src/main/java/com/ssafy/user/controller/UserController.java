package com.ssafy.user.controller;

import java.io.IOException;

import com.ssafy.user.model.service.UserService;
import com.ssafy.user.model.service.UserServiceImpl;
import com.ssafy.util.ViewPaths;

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

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");

        if (act == null) {
            resp.sendRedirect(ViewPaths.INDEX);
            return;
        }

        switch (act) {
            case "loginForm":
                req.getRequestDispatcher(ViewPaths.LOGIN).forward(req, resp);
                break;
            case "login":
                doLogin(req, resp);
                break;
            case "logout":
                doLogout(req, resp);
                break;
            case "registerForm":
                req.getRequestDispatcher(ViewPaths.REGISTER).forward(req, resp);
                break;
            case "register":
                doRegister(req, resp);
                break;
            case "followList":
                doFollowList(req, resp);
                break;
            case "follow":
                doFollow(req, resp);
                break;
            case "checkFollow":
                doCheckFollow(req, resp);
                break;
            case "unfollow":
                doUnfollow(req, resp);
                break;
            case "likedVideoList":
                doLikedVideoList(req, resp);
                break;
            case "likeVideo":
                doLikeVideo(req, resp);
                break;
            case "unlikeVideo":
                doUnlikeVideo(req, resp);
                break;
            case "checkLike":
                doCheckLike(req, resp);
                break;
            case "mypage":
                req.getRequestDispatcher(ViewPaths.MYPAGE).forward(req, resp);
                break;
            default:
                break;
        }
    }

    private void setLoginUser(HttpServletRequest req, String id) {
        HttpSession session = req.getSession();

        session.setMaxInactiveInterval(LOGIN_SESSION_TIMEOUT);
        session.setAttribute("loginUser", id);
    }

    private String getLoginUser(HttpServletRequest req) {
        return (String) req.getSession().getAttribute("loginUser");
    }

    /**
     * 로그인 여부 확인
     * 로그인 되어 있지 않으면 로그인 페이지로 이동
     * 
     * @return 로그인 여부
     */
    private boolean checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (getLoginUser(req) == null) {
            req.setAttribute("msg", "로그인이 필요합니다.");
            req.getRequestDispatcher(ViewPaths.LOGIN).forward(req, resp);
            return false;
        }
        return true;
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (userService.authenticate(id, password)) {
            setLoginUser(req, id);
            resp.sendRedirect(ViewPaths.INDEX);
        } else {
            req.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            req.getRequestDispatcher(ViewPaths.LOGIN).forward(req, resp);
        }
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(ViewPaths.INDEX);
    }

    private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        if (userService.userIdExists(id)) {
            req.setAttribute("msg", "이미 존재하는 아이디입니다.");
            req.getRequestDispatcher(ViewPaths.REGISTER).forward(req, resp);
            return;
        }

        if (!userService.register(id, password)) {
            req.setAttribute("msg", "회원가입에 실패했습니다.");
            req.getRequestDispatcher(ViewPaths.REGISTER).forward(req, resp);
            return;
        }

        req.setAttribute("msg", "회원가입이 완료되었습니다. 로그인해주세요.");
        req.getRequestDispatcher(ViewPaths.LOGIN).forward(req, resp);
    }

    private void doFollowList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        req.setAttribute("followList", userService.getFollowings(getLoginUser(req)));
        req.getRequestDispatcher(ViewPaths.FOLLOW_LIST).forward(req, resp);
    }

    private void doUnfollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String targetId = req.getParameter("id");
        String referer = req.getHeader("referer");
        String dest = referer == null ? ViewPaths.INDEX : referer;
        userService.unfollow(userId, targetId);

        resp.sendRedirect(dest);
    }

    private void doFollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String targetId = req.getParameter("id");
        String referer = req.getHeader("referer");
        String dest = referer == null ? ViewPaths.INDEX : referer;
        userService.follow(userId, targetId);

        resp.sendRedirect(dest);
    }

    private void doCheckFollow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = getLoginUser(req);
        String targetId = req.getParameter("id");

        resp.getWriter().print(userService.checkFollowing(userId, targetId));
    }

    private void doLikedVideoList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        req.setAttribute("likedVideoList", userService.getLikedVideos(userId));
        req.getRequestDispatcher(ViewPaths.LIKED_VIDEO_LIST).forward(req, resp);
    }

    private void doUnlikeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String videoId = req.getParameter("id");
        String referer = req.getHeader("referer");
        String dest = referer == null ? ViewPaths.INDEX : referer;
        userService.unlikeVideo(userId, videoId);

        resp.sendRedirect(dest);
    }

    private void doLikeVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!checkLogin(req, resp)) {
            return;
        }

        String userId = getLoginUser(req);
        String videoId = req.getParameter("id");
        String referer = req.getHeader("referer");
        String dest = referer == null ? ViewPaths.INDEX : referer;
        userService.likeVideo(userId, videoId);

        resp.sendRedirect(dest);
    }

    private void doCheckLike(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = getLoginUser(req);
        String videoId = req.getParameter("id");
        resp.getWriter().print(userService.checkLikedVideo(userId, videoId));
    }
}
