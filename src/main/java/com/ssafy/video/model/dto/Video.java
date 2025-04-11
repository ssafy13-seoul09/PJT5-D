package com.ssafy.video.model.dto;

/**
 * 운동 유튜브 비디오 데이터 전송을 위한 DTO
 *
 * - 비디오는 youtubeId로 고유하게 식별됨
 * - 하나의 비디오 당 하나의 운동 부위를 가짐
 */
public class Video {
    private String youtubeId;
    private String title;
    private String fitPartName;
    private String channelName;
    private int viewCnt;

    public Video() {
    }

    public Video(String youtubeId, String title, String fitPartName, String channelName, int viewCnt) {
        this.youtubeId = youtubeId;
        this.title = title;
        this.fitPartName = fitPartName;
        this.channelName = channelName;
        this.viewCnt = viewCnt;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
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
