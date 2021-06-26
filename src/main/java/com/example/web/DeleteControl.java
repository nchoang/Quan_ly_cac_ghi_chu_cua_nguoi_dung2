package com.example.web;

import com.example.dao.NoteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteControl", value = "/delete")
public class DeleteControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NoteDAO dao = new NoteDAO();
        try {
            dao.deleteNote(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("loader");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
