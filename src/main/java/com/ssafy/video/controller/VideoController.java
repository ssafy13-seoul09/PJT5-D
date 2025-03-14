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
	private static ArrayList<Video> fullBody = new ArrayList<>();
	private static ArrayList<Video> upperBody = new ArrayList<>();
	private static ArrayList<Video> lowerBody = new ArrayList<>();
	private static ArrayList<Video> abdomen = new ArrayList<>();
	
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
		String bodyPart = req.getParameter("bodyPart");
		
		List<Video> allVideos = service.selectAll();
		int len = allVideos.size();
		
		for(int i=0; i<len; i++) {
			if(allVideos.get(i).getFitPartName().equals("전신")) {
				fullBody.add(allVideos.get(i));
			}else if(allVideos.get(i).getFitPartName().equals("복부")) {
				abdomen.add(allVideos.get(i));
			}else if(allVideos.get(i).getFitPartName().equals("하체")) {
				lowerBody.add(allVideos.get(i));
			}else if(allVideos.get(i).getFitPartName().equals("상체")){
				upperBody.add(allVideos.get(i));
			}
			
			
			//System.out.println(video);
		}
		
		// 받아오는 query 값에 따라, req에 담는 정보를 다르게 한다.
		switch(bodyPart) {
			case "전신":
				req.setAttribute("fullBody", fullBody);
				System.out.println(fullBody);
				break;
			case "복부":
				req.setAttribute("abdomen", abdomen);
				System.out.println(abdomen);
				break;
			case "상체":
				req.setAttribute("upperBody", upperBody);
				System.out.println(upperBody);
				break;
			case "하체":
				req.setAttribute("lowerBody", lowerBody);
				System.out.println(lowerBody);
				break;
			
		}
		
		
		RequestDispatcher disp = req.getRequestDispatcher("/");
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
