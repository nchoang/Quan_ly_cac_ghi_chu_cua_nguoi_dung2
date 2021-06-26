package com.example.web;

import com.example.dao.NoteDAO;
import com.example.model.Notemodel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet(urlPatterns = {"/note-list"})
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoteDAO noteDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		noteDAO = new NoteDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertNote(request, response);
					break;
				default:
					listNote(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listNote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Notemodel> listNote = noteDAO.selectAllNotes();
		request.setAttribute("listNote", listNote);
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("note-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertNote(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		response.sendRedirect("list");
	}
}