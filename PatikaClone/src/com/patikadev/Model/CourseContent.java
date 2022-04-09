package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseContent {
    private int id;
    private int course_id;
    private int user_id;
    private String title;
    private String description;
    private String youtube_url;

    public CourseContent() {

    }

    public CourseContent(int id, int course_id, String title) {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
    }

    public CourseContent(int id, int course_id, int user_id, String title, String description, String youtube_url) {
        this.id = id;
        this.course_id = course_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.youtube_url = youtube_url;
    }

    public static boolean add(int course_id, int user_id, String title, String description, String youtube) {
        String query = "INSERT INTO content (course_id, user_id, title, description, youtube) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, course_id);
            pr.setInt(2, user_id);
            pr.setString(3, title);
            pr.setString(4, description);
            pr.setString(5, youtube);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<CourseContent> getListByUserForEducator(int id) {
        ArrayList<CourseContent> contentList = new ArrayList<>();
        CourseContent obj;
        Statement st = null;

        try {
            st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM content WHERE id = " + id);
            while (rs.next()) {
                int content_id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String title = rs.getString("title");
                obj = new CourseContent(content_id, course_id, title);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contentList;
    }

    public static ArrayList<CourseContent> getListByUserForEducatorFilter(int id, String course, String contentTitle) {
        ArrayList<CourseContent> contentList = new ArrayList<>();

        CourseContent obj;
        Statement st = null;
        try {
            st = DBConnector.getInstance().createStatement();
            String query = "SELECT * FROM content WHERE user_id = " + id +
                    " AND title LIKE '%" + contentTitle +
                    "SELECT id FROM course WHERE name LIKE '%" + course + "%'))";
            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {
                int content_id = rs.getInt("id");
                int course_id = rs.getInt("course_id");
                String title = rs.getString("title");
                obj = new CourseContent(content_id, course_id, title);
                contentList.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }

}
