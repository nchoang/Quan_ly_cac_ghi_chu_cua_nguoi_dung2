package com.example.web;

import com.example.dao.NoteDAO;
import com.example.model.Notemodel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadControl", value = "/loader")
public class loader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tu ham DAO
        NoteDAO noteDAO = new NoteDAO();
        List<Notemodel> list = null;

        //Set data vao trang jsp
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("user");
        list = noteDAO.getallnotebyiduser(username);
        request.setAttribute("listnote",list);
        request.getRequestDispatcher("note-list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
