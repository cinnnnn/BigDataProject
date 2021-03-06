package com.src.rivalrooster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet implementation class Operation
 */
@WebServlet("/operation")
public class Operation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String choice = request.getParameter("choice");

		DAO dao = new DAO();
		if ("monthusage".equalsIgnoreCase(choice)) {
			String pageID = request.getParameter("pageid");
			UserGraphVO graphVO = dao.last6MonthsUsage(pageID);
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(graphVO));

		} else if ("hoursusage".equalsIgnoreCase(choice)) {
			String pageID = request.getParameter("pageid");
			// Date date = Date.parse(request.getParameter("date"));
			Date date = null;
			GraphVO graphVO = dao.hours24Usage(pageID, date);
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(graphVO));

		} else if ("score".equalsIgnoreCase(choice)) {
			String pageID = request.getParameter("pageid");
			double score = dao.getscore(pageID);
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(score));

		} else if ("topkusers".equalsIgnoreCase(choice)) {
			String pageID = request.getParameter("pageid");
			UserGraphVO userGraphVO = dao.topkUsers(pageID, 10);
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(userGraphVO));
		} else if ("sentiments".equalsIgnoreCase(choice)) {
			String pageID = request.getParameter("pageid");
			List<Integer> sentiments = dao.getSentiments(pageID);
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(sentiments));
		} else if ("tags".equalsIgnoreCase(choice)) {
			List<Tag> tags = dao.getTags();
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(tags));

		} else if ("location".equalsIgnoreCase(choice)) {
			String pageID = request.getParameter("pageid");
			GraphVO graphVO = dao.locationStats(pageID);
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(graphVO));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
	}

}
