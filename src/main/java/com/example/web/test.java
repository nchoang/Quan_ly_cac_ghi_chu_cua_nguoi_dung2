package com.example.web;

import com.example.dao.NoteDAO;
import com.example.model.Notemodel;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) throws SQLException {
        NoteDAO dao = new NoteDAO();
        Notemodel n = dao.selectNote("3");
        System.out.println(n);
        }
}
