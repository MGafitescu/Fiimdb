package eu.ubis.fiimdb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* TO DO: */
		/* 1. get searchType - type of search: name, genre, release year
		 *	  and searchedValue parameters from the request
		 *	  hint: use getParameter 
		 */
		String criteria = request.getParameter("searchType");
		String value = request.getParameter("searchedValue");
	    
		
		/* 
		 * 2. create an object of type MovieBean and call search method
		 */
		MovieBean bean = new MovieBean();
		bean.search(criteria, value);
		/*
		 * 3. set the bean as an attribute to the request 
		 * make sure that it is the name from movies.jsp (see tag <jsp:useBean>)
		 */
		request.setAttribute("movieBean", bean);
		
		/*
		 * 4. set the type of the search that was made
		 */
		request.setAttribute("searchType", criteria);
		/*
		 * 5. redirect to searchedMovies.jsp
		 */
		getServletContext().getRequestDispatcher("/searchedMovies.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
