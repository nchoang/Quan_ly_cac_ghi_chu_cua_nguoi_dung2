package com.example.web;

import com.example.dao.NoteDAO;
import com.example.model.Notemodel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateControl", value = "/update")
public class UpdateControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NoteDAO dao = new NoteDAO();
        Notemodel notemodel = dao.selectNote(id);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        request.setAttribute("id",id);
        request.setAttribute("Notemodel", notemodel);
        request.getRequestDispatcher("update.jsp").
                forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        NoteDAO dao = new NoteDAO();
        try {
            dao.updateNote(id, title, content);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("loader");

    }
}
