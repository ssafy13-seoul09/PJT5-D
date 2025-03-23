package com.ssafy.video.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.review.model.dto.Review;
import com.ssafy.review.model.service.ReviewService;
import com.ssafy.review.model.service.ReviewServiceImpl;
import com.ssafy.video.model.dto.Video;
import com.ssafy.video.model.service.VideoService;
import com.ssafy.video.model.service.VideoServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/video")
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private VideoService service = VideoServiceImpl.getInstance();
    private ReviewService Rservice = ReviewServiceImpl.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("act");

        switch (act) {
            case "mainPage":
                doMainPage(req, resp);
                break;

            case "selectAll":
                doSelectAll(req, resp);
                break;

            case "reviewPage":
                doReview(req, resp);
                break;
        }
    }

    /**
     * 영상 메인 페이지로 포워드
     */
    private void doMainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("videos", service.selectAll());
        req.getRequestDispatcher("/WEB-INF/video/mainPage.jsp").forward(req, resp);
    }

    // 리뷰 페이지로 넘어간다.
    // parameter로 받은 youtubeId와 동일한 youtubeId를 가진 Video를 request에 담아서 보낸다.
    protected void doReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String youtubeId = req.getParameter("youtubeId");

        service.updateViewCnt(youtubeId);
        List<Review> reviewList = Rservice.getReviewsbyId(youtubeId);
        Video selVid = service.select(youtubeId);

        // 리뷰 불러오기 위함
        req.setAttribute("selVid", selVid);
        req.setAttribute("list", reviewList);

        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/video/reviewList.jsp");
        disp.forward(req, resp);

    }

    // 모든 비디오를 출력한다.
    protected void doSelectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DB에서 가져온 비디오를 req에 담아서 보낸다.
        req.setAttribute("list", service.selectAll());

        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/video/showAllVideos.jsp");
        RequestDispatcher dispTest = req.getRequestDispatcher("/test.jsp");
        disp.forward(req, resp);
        dispTest.forward(req, resp);

    }

}
