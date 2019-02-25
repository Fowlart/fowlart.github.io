package initial_example;

// Import required java libraries

import models.HelloJspRenderer;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class HelloForm extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search_text = request.getParameter("search_text");
        String selected_filter = request.getParameter("selectoid");
        System.out.println(search_text);
        System.out.println(selected_filter);
        HelloJspRenderer renderer = new HelloJspRenderer();
        renderer.addItemEntry("фігня","100");
        renderer.addItemEntry("дорожча фігня","200");
        System.out.println(HelloJspRenderer.getTableCode());
        response.sendRedirect("Hello.jsp");

    }
}