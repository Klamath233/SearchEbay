package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	/** First, we will extract the parameters from the request. Then, we
    	 *  establish a AuctionSearchClient to communicate with the web service 
		 *  and get the requested SearchResult array. Then, we pass this array to
		 *  the jsp page by setting the request's attribute.
    	 */

        String q = request.getParameter("q");
        int numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip"));
        int numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn"));

        SearchResult[] srArray = AuctionSearchClient.basicSearch(q, numResultsToSkip, numResultsToReturn);

        request.setAttribute("basicSearchResult", srArray);
        request.getRequestDispatcher("/keywordSearchResult.jsp").forward(request, response);
    }
}
