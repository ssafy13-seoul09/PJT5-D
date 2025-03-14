package com.ssafy.review.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import com.ssafy.review.model.dto.Review;
import com.ssafy.review.model.service.ReviewService;
import com.ssafy.review.model.service.ReviewServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// review 어노테이션 마즘??? 
@WebServlet("/review")
public class ReviewController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ReviewService service = ReviewServiceImpl.getInstance();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = req.getParameter("act");
		
		switch (act) { 
		
		// 리뷰 리스트 출력
		case "list":
			doList(req, resp);
			break;
			
		// 작성 화면 보여주기
		case "writeform":
			doWriteForm(req, resp);
			break;
		
		// 리뷰 작성 
		case "write": 
			doWrite(req, resp);
			break;
			
		// 수정 폼 제공
		case "updateform":
			doUpdateForm(req, resp);
			break;
			
		// 리뷰 상세보기 
		case "detail":
			doDetail(req, resp);
			break;
			
		// 리뷰 수정 실제 수행 
		case "update":
			doUpdate(req, resp);
			break;

		// 실제 삭제 수행
		case "remove":
			doRemove(req, resp);
			
		}
		
	}

	// 전체 리뷰 보여주기 >> 비디오당?? 일단 전체 목록 가져와보기
	private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", service.selectAll());
		req.getRequestDispatcher("WEB-INF/video/reviewList.jsp").forward(req, resp);
	}


	// 작성 화면 보여주기
	private void doWriteForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/review/register.jsp").forward(req, resp);
	}

	// 작성 
	private void doWrite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		
		Review review = new Review(title, writer, content);
		
		//게시글 등록: 서비스 호출
		service.insertReview(review);
		
		// 추가 기능?
		// 게시글 상세보기
		
		
		// 게시글 전체보기
		resp.sendRedirect("review?act=list");
	}

	// 수정 버튼 클릭하면 수정 폼 띄워주기
	private void doUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int reviewId = Integer.parseInt(req.getParameter("reviewId"));
		
		// 수정할 리뷰 선택 후 띄워주기
		Review review = service.select(reviewId);
		req.setAttribute("review", review);
		
		req.getRequestDispatcher("/WEB-INF/review/update.jsp").forward(req, resp);
	}
	
	
	// 내용들로 업데이트 수행
	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int reviewId = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
		String writer = req.getParameter("writer");
        String contents = req.getParameter("contents");
        
        // 기존 리뷰 가져와서 수정하기 
        Review review = service.select(reviewId); 
        
        review.setTitle(title);
        review.setAuthorId(writer);
        review.setCreatedAt(LocalDateTime.now());
        review.setContents(contents);
        // 업데이트 수행 
        service.updateReview(review);

        // 데이터 저장
        req.setAttribute("review", review);
        
        // 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/video/reviewList.jsp");
        dispatcher.forward(req, resp);
		
	}

	// 리뷰 상세보기 : reviewId 기반으로 리뷰 내용 받아와 detail pg에 띄워주기
	private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int reviewId = Integer.parseInt(req.getParameter("reviewId"));
		
		// 수정할 리뷰 선택 후 띄워주기
		Review review = service.select(reviewId);
		req.setAttribute("review", review);

		req.getRequestDispatcher("/WEB-INF/review/detail.jsp").forward(req, resp);
	}
	
	// id 기반으로 삭제 수행 
	private void doRemove(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int reviewId = Integer.parseInt(req.getParameter("reviewId"));
		
		// 호출
		service.removeReview(reviewId);
		// 게시글 지운 후 리뷰리스트로 돌아가기
		resp.sendRedirect("review?act=list");
	
	}

	
}
