package com.ssafy.video.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class VideoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private VideoService service = VideoServiceImpl.getInstance();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String act = req.getParameter("act");
		
		
		switch(act) {
		case "favoriteList":
			doFavoriteList(req, resp);
			break;
			
		case "bodypartList":
			doBodypartList(req, resp);
			break;
			
		case "selectAll":
			doSelectAll(req,resp);
			break;
		}
		
		
	}
	
	// 조회수가 가장 많은 비디오 순으로 출력한다.
	protected void doFavoriteList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Video> allVideos = service.selectAll();
		System.out.println(allVideos);
		
		
		RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/video/reviewList.jsp");
		disp.forward(req,resp);
	
		
	}
	
	// 부위별로 선택해서 출력한다.
	protected void doBodypartList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Video> allVideos = service.selectAll();
		int len = allVideos.size();
		
		ArrayList<Video> temp = new ArrayList<>();
		for(int i=0; i<len; i++) {
			
			
			//System.out.println(video);
		}
		
		RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/video/reviewList.jsp");
		disp.forward(req,resp);

	}

	// 모든 비디오를 출력한다.
	protected void doSelectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DB에서 가져온 비디오를 req에 담아서 보낸다.
		req.setAttribute("list", service.selectAll());
		
		RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/video/showAllVideos.jsp");
		disp.forward(req,resp);

	}
	
	
	


}
