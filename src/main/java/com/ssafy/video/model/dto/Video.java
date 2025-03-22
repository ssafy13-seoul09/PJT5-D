package com.ssafy.video.model.dto;

public class Video {
	private String title;
	private String fitPartName;
	private String youtubeId;
	private String channelName;
	private int viewCnt;
//	private List<Review> reviews;
	// review 객체 날리고 pk : fk로 구동
	
	public Video() {

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
		return "Video [title=" + title + ", fitPartName=" + fitPartName + ", youtubeId=" + youtubeId + ", channelName="
				+ channelName + ", viewCnt=" + viewCnt + "]";
	}
	
}
