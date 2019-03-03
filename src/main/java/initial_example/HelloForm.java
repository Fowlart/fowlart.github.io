package initial_example;

// Import required java libraries

import models.HelloJspRenderer;
import project.entities.Item;
import project.input_data_module.CsvReader;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.http.*;


// Extend HttpServlet class
public class HelloForm extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		HelloJspRenderer renderer = new HelloJspRenderer();
		CsvReader csvReader = new CsvReader();
		String search_text = request.getParameter("search_text");

		if (!search_text.isEmpty())
		{
			request.setAttribute("renderer", searchForText(search_text, csvReader));
			request.getRequestDispatcher("hello.jsp").forward(request, response);
		}

		String selected_filter = request.getParameter("selectoid");
		System.out.println(search_text);
		System.out.println(selected_filter);

		for (Item item : csvReader.getItemList("c:/input.csv"))
		{
			renderer.addItemEntry(item.getList().get(0).getPropertieValue().toString(),
					item.getList().get(1).getPropertieValue().toString());
		}
		request.setAttribute("renderer", renderer);
		request.getRequestDispatcher("hello.jsp").forward(request, response);
	}


	private HelloJspRenderer searchForText(String text, CsvReader reader)
	{
		List<Item> serch_rez = reader.getItemList("c:/input.csv").stream().filter(x -> ((Item) x).getList().
				get(0).getPropertieValue().toString().contains(text))
				.collect(Collectors.toList());
		HelloJspRenderer renderer = new HelloJspRenderer();
		serch_rez.stream().forEach(i -> renderer.addItemEntry(i.getList().get(0).getPropertieValue().toString(),
				i.getList().get(1).getPropertieValue().toString()) );
		return renderer;
	}
}
