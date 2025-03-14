package com.ssafy.video.model.dto;

import java.util.List;

import com.ssafy.review.model.dto.Review;

public class Video {
	private String title;
	private String fitPartName;
	private String youtubeId;
	private String channelName;
	private int viewCnt;
	private List<Review> reviews;
	
	public Video() {
		// TODO Auto-generated constructor stub
	}
	public Video(String youtubeId, String channelName, int viewCnt, String fitPartName, String title) {
		this.channelName = channelName;
		this.fitPartName = fitPartName;
		this.title = title;
		this.viewCnt = viewCnt;
		this.youtubeId = youtubeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFitPartName() {
		return fitPartName;
	}
	public void setFitPartName(String fitPartName) {
		this.fitPartName = fitPartName;
	}
	public String getYoutubeId() {
		return youtubeId;
	}
	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
