package com.project.wifi.bookmark;

import com.project.wifi.Config;
import com.project.wifi.SqlUtil;
import com.project.wifi.bookmark.dto.BookMarkDto;
import com.project.wifi.bookmark.dto.BookMarkGroupDto;
import com.project.wifi.bookmark.dto.BookMarkGroupResponseDto;
import com.project.wifi.bookmark.dto.BookMarkResponseDto;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookMarkRepository {

    public void insertBookMarkGroup(BookMarkGroupDto bookMarkGroupDto) {
        Connection connection = null;
        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            StringBuilder sb = new StringBuilder();
            sb.append("insert into book_mark_group (book_mark_group_name, book_mark_group_order, create_time, update_time) values");

            sb.append("(")
                    .append(SqlUtil.makeInsertSqlValue(bookMarkGroupDto.getBookMarkName())).append(",")
                    .append(SqlUtil.makeInsertSqlValue(bookMarkGroupDto.getBookMarkOrder())).append(",")
                    .append("strftime('%Y-%m-%dT%H:%M:%S', 'now')").append(",")
                    .append("strftime('%Y-%m-%dT%H:%M:%S', 'now')").append(")");

            sb.append(";");

            String temp = sb.toString();
            System.out.println(temp);
            statement.executeUpdate(temp);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBookMark(BookMarkDto bookMarkDto) {
        Connection connection = null;
        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            StringBuilder sb = new StringBuilder();
            sb.append("insert into book_mark (book_mark_group_seq, wifi_manage_no, create_time) values");

            sb.append("(")
                    .append(SqlUtil.makeInsertSqlValue(bookMarkDto.getBookMarkSeq())).append(",")
                    .append(SqlUtil.makeInsertSqlValue(bookMarkDto.getWifiManageNo())).append(",")
                    .append("strftime('%Y-%m-%dT%H:%M:%S', 'now')").append(")");

            sb.append(";");

            String temp = sb.toString();
            System.out.println(temp);
            statement.executeUpdate(temp);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BookMarkGroupResponseDto> selectBookMarkGroupList() throws IOException {
        Connection connection = null;

        List<BookMarkGroupResponseDto> bookMarkGroupResponseDtoList = new ArrayList<>();

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "SELECT * FROM book_mark_group ORDER BY book_mark_group_order ASC";

            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                BookMarkGroupResponseDto bookMarkGroupResponseDto = new BookMarkGroupResponseDto();
                bookMarkGroupResponseDto.setSeq(rs.getString("seq"));
                bookMarkGroupResponseDto.setBookMarkName(rs.getString("book_mark_group_name"));
                bookMarkGroupResponseDto.setBookMarkOrder(rs.getString("book_mark_group_order"));
                bookMarkGroupResponseDto.setCreateTime(rs.getString("create_time"));
                bookMarkGroupResponseDto.setUpdateTime(rs.getString("update_time"));
                bookMarkGroupResponseDtoList.add(bookMarkGroupResponseDto);
            }

            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookMarkGroupResponseDtoList;
    }

    public BookMarkGroupResponseDto selectBookMarkGroup(String seq) throws IOException {
        Connection connection = null;

        BookMarkGroupResponseDto bookMarkGroupResponseDto = new BookMarkGroupResponseDto();
        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "SELECT * FROM book_mark_group WHERE seq = " + seq;

            ResultSet rs = statement.executeQuery(selectQuery);


            while (rs.next()) {
                bookMarkGroupResponseDto.setSeq(rs.getString("seq"));
                bookMarkGroupResponseDto.setBookMarkName(rs.getString("book_mark_group_name"));
                bookMarkGroupResponseDto.setBookMarkOrder(rs.getString("book_mark_group_order"));
                bookMarkGroupResponseDto.setCreateTime(rs.getString("create_time"));
                bookMarkGroupResponseDto.setUpdateTime(rs.getString("update_time"));
            }

            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookMarkGroupResponseDto;
    }

    public void updateBookMarkGroup(BookMarkGroupDto bookMarkGroupDto) throws IOException {
        Connection connection = null;

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "UPDATE book_mark_group SET "
                                + " book_mark_group_name = " + SqlUtil.makeInsertSqlValue(bookMarkGroupDto.getBookMarkName()) + ","
                                + " book_mark_group_order = " + SqlUtil.makeInsertSqlValue(bookMarkGroupDto.getBookMarkOrder()) + ","
                                + " update_time = strftime('%Y-%m-%dT%H:%M:%S', 'now')"
                                + " WHERE seq = " + bookMarkGroupDto.getSeq() + ";";

            statement.executeUpdate(selectQuery);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBookMarkGroup(BookMarkGroupDto bookMarkGroupDto) {
        Connection connection = null;

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "DELETE FROM book_mark_group" +
                    " WHERE seq = " + bookMarkGroupDto.getSeq() + ";";

            statement.executeUpdate(selectQuery);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BookMarkResponseDto> selectBookMarkList() {
        Connection connection = null;

        List<BookMarkResponseDto> bookMarkResponseDtoList = new ArrayList<>();

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "select a.seq as seq, a.wifi_manage_no as wifi_manage_no, b.book_mark_group_name as book_mark_group_name, c.wifi_name as wifi_name, a.create_time as create_time\n" +
                    " from book_mark a\n" +
                    " join book_mark_group b on a.book_mark_group_seq = b.seq\n" +
                    " join wifi_detail c on a.wifi_manage_no = c.manage_no\n" +
                    ";";

            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                BookMarkResponseDto bookMarkResponseDto = new BookMarkResponseDto();
                bookMarkResponseDto.setSeq(rs.getString("seq"));
                bookMarkResponseDto.setWifiManageNo(rs.getString("wifi_manage_no"));
                bookMarkResponseDto.setBookMarkGroupName(rs.getString("book_mark_group_name"));
                bookMarkResponseDto.setWifiName(rs.getString("wifi_name"));
                bookMarkResponseDto.setCreateTime(rs.getString("create_time"));
                bookMarkResponseDtoList.add(bookMarkResponseDto);
            }

            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookMarkResponseDtoList;
    }

    public BookMarkResponseDto selectBookMark(String seq) {
        Connection connection = null;

        BookMarkResponseDto bookMarkResponseDto = new BookMarkResponseDto();

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "select a.seq as seq, b.book_mark_group_name as book_mark_group_name, c.wifi_name as wifi_name, a.create_time as create_time\n" +
                    " from book_mark a\n" +
                    " join book_mark_group b on a.book_mark_group_seq = b.seq\n" +
                    " join wifi_detail c on a.wifi_manage_no = c.manage_no\n" +
                    " WHERE a.seq = " + seq +
                    ";";

            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                bookMarkResponseDto.setSeq(rs.getString("seq"));
                bookMarkResponseDto.setBookMarkGroupName(rs.getString("book_mark_group_name"));
                bookMarkResponseDto.setWifiName(rs.getString("wifi_name"));
                bookMarkResponseDto.setCreateTime(rs.getString("create_time"));
            }

            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookMarkResponseDto;
    }

    public void deleteBookMark(BookMarkDto bookMarkDto) {
        Connection connection = null;

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            String selectQuery = "DELETE FROM book_mark" +
                    " WHERE seq = " + bookMarkDto.getSeq() + ";";

            statement.executeUpdate(selectQuery);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


