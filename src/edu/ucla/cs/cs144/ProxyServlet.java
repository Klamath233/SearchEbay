package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet implements Servlet {
       
    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
        try{
        	String query = request.getParameter("q");
        	URL url = new URL("http://google.com/complete/search?output=toolbar&q="+query);
        	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        	InputStream is = connection.getInputStream();
        	InputStreamReader isr = new InputStreamReader(is);
        	BufferedReader rd = new BufferedReader(isr);
        	StringBuffer buf = new StringBuffer();
			String line = rd.readLine();
        	while(line != null) {
        		buf.append(line);
        		line = rd.readLine();
        	}
        	response.setContentType("text/xml"); 
        	PrintWriter out = response.getWriter();
        	out.println(buf.toString());
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
