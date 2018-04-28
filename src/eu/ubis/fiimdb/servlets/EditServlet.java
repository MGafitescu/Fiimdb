package eu.ubis.fiimdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.ubis.fiimdb.controller.MovieBean;
import eu.ubis.fiimdb.model.Movie;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit-movie")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String movieId = request.getParameter("movieId");
		MovieBean movieBean = new MovieBean();
		Movie movie = movieBean.getSingleMovie(movieId);
		request.setAttribute("movie", movie);
		getServletContext().getRequestDispatcher("/movie-edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MovieBean movieBean = new MovieBean();
		Movie movie = new Movie();
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		movie.setId(movieId);
		movie.setName(request.getParameter("name"));
		movie.setDirector(request.getParameter("director"));
		movie.setWriter(request.getParameter("writer"));
		movie.setRating(Double.parseDouble(request.getParameter("rating")));
		movie.setLength(Integer.parseInt(request.getParameter("length")));
		movie.setDescription(request.getParameter("description"));

		if (movieBean.updateMovie(movie, movieId)) {
			response.sendRedirect("movies");
		} else {
			request.setAttribute("movie", movie);
			getServletContext().getRequestDispatcher("/movie-edit.jsp").forward(request, response);
		}
	}
}
