package com.example.web;

import com.example.dao.NoteDAO;
import com.example.model.usermodel;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignUpControl", value = "/signup")
public class SignUpControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String pass = request.getParameter("password");
        NoteDAO noteDAO = new NoteDAO();
        List<usermodel> list = noteDAO.checkAccountExist(username);
        if(list == null) {
            noteDAO.signup(username, pass);
            request.setAttribute("message", "Sign up success! Please login.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
            request.setAttribute("message2", "Username has already taken!");
            request.getRequestDispatcher("sign-up.jsp").forward(request, response);;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
