package com.saint.bang.utils;

/**
 * Created by zzh on 15-12-2.
 */
public class BannerData {
    private int Id;
    private String Title;
    private String Image;
    private int PositionId;
    private String hot_content;
    private int page;



    private String contents;


    //region getter and setter
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getPositionId() {
        return PositionId;
    }

    public void setPositionId(int positionId) {
        PositionId = positionId;
    }

    public String getHot_content() {
        return hot_content;
    }

    public void setHot_content(String hot_content) {
        this.hot_content = hot_content;
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }



    //endregion
}
