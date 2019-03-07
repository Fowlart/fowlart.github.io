package initial_example;

import models.HelloJspRenderer;
import project.entities.Item;
import project.input_data_module.CsvReader;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloForm extends HttpServlet
{
	private static String PATH_TO_FILE = "c:/input.csv";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		CsvReader csvReader = new CsvReader();
		String search_text = request.getParameter("search_text");
		String selected_filter = request.getParameter("selectoid");
		if (!search_text.isEmpty() || (selected_filter.equals("all")))
		{
			request.setAttribute("renderer", searchForText(search_text, csvReader));
		}
		else
		{
			if (selected_filter.equals("min"))
			{
				request.setAttribute("renderer", searchMinValue(csvReader));
			}

			if (selected_filter.equals("max"))
			{
				request.setAttribute("renderer", searchMaxValue(csvReader));
			}

			if (selected_filter.equals("avg"))
			{
				request.setAttribute("renderer", searchAvgValue(csvReader));
			}
		}

		request.getRequestDispatcher("hello.jsp").forward(request, response);
	}

	private HelloJspRenderer searchForText(String text, CsvReader reader)
	{
		List<Item> serch_rez = reader.getItemList(PATH_TO_FILE).stream()
				.filter(x -> x.getList().get(0).getPropertieValue().toString().contains(text))
				.collect(Collectors.toList());
		HelloJspRenderer renderer = new HelloJspRenderer();
		serch_rez.stream().forEach(i -> renderer.addItemEntry(i.getList().get(0).getPropertieValue().toString(),
				i.getList().get(1).getPropertieValue().toString()));
		return renderer;
	}

	private HelloJspRenderer searchAvgValue(CsvReader reader)
	{
		HelloJspRenderer returned_render = new HelloJspRenderer();

		ToDoubleFunction<Item> function = (i) -> {
			Object value = i.getList().get(1).getPropertieValue();
			return ((BigDecimal) value).doubleValue();
		};

		returned_render.addItemEntry("average price ", reader.getItemList(PATH_TO_FILE).stream().collect(
				Collectors.averagingDouble(function)).toString());

		return returned_render;
	}

	private HelloJspRenderer searchMinValue(CsvReader reader)
	{
		HelloJspRenderer returned_render = new HelloJspRenderer();

		Optional<? extends Item> serch_rez = reader.getItemList(PATH_TO_FILE).stream().min((i1, i2) -> {
			BigDecimal bigDecimal1 = (BigDecimal)i1.getList().get(1).getPropertieValue();
			BigDecimal bigDecimal2 = (BigDecimal)i2.getList().get(1).getPropertieValue();
			return bigDecimal1.compareTo(bigDecimal2);
		});

		if (serch_rez.isPresent())
		{
			Item item = serch_rez.get();
			returned_render.addItemEntry(item.getList().get(0).getPropertieValue().toString(),
					item.getList().get(1).getPropertieValue().toString());
		}

		return returned_render;
	}

	private HelloJspRenderer searchMaxValue(CsvReader reader)
	{
		HelloJspRenderer returned_render = new HelloJspRenderer();

		Optional<? extends Item> serch_rez = reader.getItemList(PATH_TO_FILE).stream().max((i1, i2) -> {
			BigDecimal bigDecimal1 = (BigDecimal)i1.getList().get(1).getPropertieValue();
			BigDecimal bigDecimal2 = (BigDecimal)i2.getList().get(1).getPropertieValue();
			return bigDecimal1.compareTo(bigDecimal2);
		});

		if (serch_rez.isPresent())
		{
			Item item = serch_rez.get();
			returned_render.addItemEntry(item.getList().get(0).getPropertieValue().toString(),
					item.getList().get(1).getPropertieValue().toString());
		}

		return returned_render;
	}
}