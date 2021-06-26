package com.example.web;

import com.example.dao.NoteDAO;
import com.example.model.usermodel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet4", value = "/check_account")
public class checkacccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username =   request.getParameter("username");
        String pass =   request.getParameter("password");
        NoteDAO noteDAO =new NoteDAO();
             List<usermodel>  list = noteDAO.checkuser(username,pass);
             if(list.size()==0){
                 request.setAttribute("mess", "Incorrect username or password!");
                 request.getRequestDispatcher("index.jsp").forward(request, response);
             }
             else {
                 session.setAttribute("user",username);
                 session.setAttribute("pass",pass);
                 response.sendRedirect("loader");
             }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
