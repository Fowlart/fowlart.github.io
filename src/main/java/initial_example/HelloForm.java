package initial_example;

// Import required java libraries

import models.HelloJspRenderer;
import project.input_data_module.CsvReader;

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
        CsvReader csvReader = new CsvReader();

        renderer.addItemEntry("good1","100");
        renderer.addItemEntry("good2","200");

        request.setAttribute("renderer",renderer);
        System.out.println(request.getAttribute("html"));
        request.getRequestDispatcher("hello.jsp").forward(request,response);
    }
}