package com.project.wifi;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/init")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("HelloServlet init");
        Connection connection = null;

        try {
            Class.forName(Config.DB_DRIVER);
            connection = DriverManager.getConnection(Config.DB_URL);
            Statement statement = connection.createStatement();

            statement.executeUpdate("drop table if exists wifi_detail");
            String a = make_wifi_detail_table();
            statement.executeUpdate(a);

            statement.executeUpdate("drop table if exists wifi_history");
            a = make_wifi_histroy_table();
            statement.executeUpdate(a);

            statement.executeUpdate("drop table if exists book_mark_group");
            a = make_book_mark_group_table();
            statement.executeUpdate(a);

            statement.executeUpdate("drop table if exists book_mark");
            a = make_book_mark_table();
            statement.executeUpdate(a);

            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    private String make_wifi_histroy_table() {
        return "CREATE TABLE \"wifi_history\" (\n" +
                "\t\"seq\"\tINTEGER,\n" +
                "\t\"lat\"\tTEXT,\n" +
                "\t\"lnt\"\tTEXT,\n" +
                "\t\"create_time\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"seq\" AUTOINCREMENT)\n" +
                ");";
    }

    private String make_wifi_detail_table() {
        return "CREATE TABLE \"wifi_detail\" (\n" +
                "\t\"manage_no\"\tTEXT,\n" +
                "\t\"district\"\tTEXT,\n" +
                "\t\"wifi_name\"\tTEXT,\n" +
                "\t\"street_address\"\tTEXT,\n" +
                "\t\"detail_address\"\tTEXT,\n" +
                "\t\"install_location\"\tTEXT,\n" +
                "\t\"install_type\"\tTEXT,\n" +
                "\t\"install_agency\"\tTEXT,\n" +
                "\t\"service_type\"\tTEXT,\n" +
                "\t\"network_type\"\tTEXT,\n" +
                "\t\"install_year\"\tTEXT,\n" +
                "\t\"inoutdoor_type\"\tTEXT,\n" +
                "\t\"wifi_env\"\tTEXT,\n" +
                "\t\"lat\"\tTEXT,\n" +
                "\t\"lnt\"\tTEXT,\n" +
                "\t\"work_date\"\tTEXT,\n" +
                "\t\"lat_sin\"\tTEXT,\n" +
                "\t\"lat_cos\"\tTEXT,\n" +
                "\t\"lnt_sin\"\tTEXT,\n" +
                "\t\"lnt_cos\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"manage_no\")" +
                ")";
    }

    private String make_book_mark_group_table() {
        return "CREATE TABLE \"book_mark_group\" (\n" +
                "\t\"seq\"\tINTEGER,\n" +
                "\t\"book_mark_group_name\"\tTEXT,\n" +
                "\t\"book_mark_group_order\"\tINTEGER,\n" +
                "\t\"create_time\"\tTEXT,\n" +
                "\t\"update_time\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"seq\" AUTOINCREMENT)\n" +
                ")";
    }

    private String make_book_mark_table() {
        return "CREATE TABLE \"book_mark\" (\n" +
                "\t\"seq\"\tINTEGER,\n" +
                "\t\"book_mark_group_seq\"\tTEXT,\n" +
                "\t\"wifi_manage_no\"\tTEXT,\n" +
                "\t\"create_time\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"seq\" AUTOINCREMENT)\n" +
                ")";
    }
}