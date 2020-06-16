package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WxArticleChapterBean {


    /**
     * children : []
     * courseId : 13
     * id : 408
     * name : 鸿洋
     * order : 190000
     * parentChapterId : 407
     * userControlSetTop : false
     * visible : 1
     */

    @SerializedName("courseId")
    public int courseId;
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("order")
    public int order;
    @SerializedName("parentChapterId")
    public int parentChapterId;
    @SerializedName("userControlSetTop")
    public boolean userControlSetTop;
    @SerializedName("visible")
    public int visible;
    @SerializedName("children")
    public List<?> children;
}
