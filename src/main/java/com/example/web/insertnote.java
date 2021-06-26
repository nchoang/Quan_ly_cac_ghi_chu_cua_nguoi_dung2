package com.example.web;

import com.example.dao.NoteDAO;
import com.example.model.Notemodel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/insert1")
public class insertnote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String notetitle = request.getParameter("notetitle");
        String  content = request.getParameter("content");
        String iduser = (String) session.getAttribute("user");
        NoteDAO noteDAO = new NoteDAO();

        try {
            noteDAO.insertnote(id,notetitle,content,iduser);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
     response.sendRedirect("loader");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
