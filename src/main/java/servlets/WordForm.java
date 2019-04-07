package servlets;

import models.TableWordsRender;
import models.WordsRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import project.dictionary_optimizer.Optimizer;
import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsReader;
import project.io_data_module.CsvWordsWriter;
import speech.Speech;
import spring_utils.Handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@WebServlet(
		name = "MyServlet",
		urlPatterns = {"/word"}
)
public class WordForm extends HttpServlet
{

	private static String INPUT_FILE = "dictionaries/db.csv";
	private static String OUTPUT_FILE = "dictionaries/db.csv";

	private List<WordTranslate> wordTranslatelist;
	private Random random;
	private TableWordsRender tableWordsRender;
	private WordsRenderer wordsRenderer;
	private WordTranslate wordTranslate;
	private Double progress;


	public List<WordTranslate> getWordTranslatelist()
	{
		return wordTranslatelist;
	}

	private Double avg_point;
	private Integer total_points;
	private Integer count_of_words;
	private Speech speech;
	private CsvWordsReader csvWordsReader;


	@Override
	public void init() throws ServletException
	{
		super.init();
		// setup
		//ApplicationContext context = new ClassPathXmlApplicationContext("dic_mover-spring.xml");
		ApplicationContext context = new FileSystemXmlApplicationContext("dic_mover-spring.xml");
		// where is root for tomcat
		csvWordsReader = new CsvWordsReader();
		wordTranslatelist = csvWordsReader.getItemList(INPUT_FILE);
		wordTranslatelist = new Optimizer(wordTranslatelist).getOptimizedList();
		random = new Random(47);
		tableWordsRender = new TableWordsRender();
		wordsRenderer = new WordsRenderer();
		wordTranslate = nextWord();
		wordsRenderer.setEnglish_word(wordTranslate.getEngword());
		wordsRenderer.setUkr_word(wordTranslate.getUkrword());
		//speech = new Speech();
	}

	//todo: infinity loop danger here
	private WordTranslate nextWord()
	{
		// recalculating view metrics
		{
			total_points = this.wordTranslatelist.stream().mapToInt((i) -> i.getPoints()).reduce(0, (i1, i2) -> i1 + i2);
			count_of_words = this.wordTranslatelist.size();
			avg_point = (double) total_points / (double) count_of_words;
			progress = avg_point / 30 * 100;
		}
		while (true)
		{
			WordTranslate wordTranslate = wordTranslatelist.stream().skip(this.random.nextInt(wordTranslatelist.size()))
					.findAny().get();
			if (wordTranslate.getPoints() <= (this.avg_point.intValue()))
			{
				new Speech().speak(wordTranslate.getUkrword(), "uk");
				return wordTranslate;
			}
			System.out.println(">skip '" + wordTranslate.getEngword() + "' with " + wordTranslate.getPoints() + " points");
		}
	}

	private void forwarding(WordsRenderer r1, TableWordsRender r2, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setAttribute("file", csvWordsReader.lastModified());
		request.setAttribute("total_points", total_points);
		request.setAttribute("count_of_words", count_of_words);
		request.setAttribute("avg_point", avg_point.intValue());
		request.setAttribute("progress", progress);
		if (progress > 100)
			request.setAttribute("progress", "completed!");
		request.setAttribute("renderer", r1);
		request.setAttribute("tableRenderer", r2);
		request.getRequestDispatcher("words.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String entered_text = request.getParameter("check_text");
		String selected_filter = request.getParameter("selectoid");
		request.setAttribute("renderer", this.wordsRenderer);
		this.tableWordsRender.clearTable();

		// first start without parameters
		if ((entered_text == null) || (selected_filter == null))
		{
			System.out.println(">FIRST START");
			entered_text = "";
			selected_filter = "sort";
		}

		// if word is skipped
		if (entered_text.equalsIgnoreCase(""))
		{

			//by default going HERE.
			if (selected_filter.equals("sort"))
			{
				wordTranslatelist = this.wordTranslatelist.stream().sorted((w1, w2) -> w1.getPoints() - w2.getPoints())
						.collect(Collectors.toList());

			}

			//ToDO: here, imported dictionaries must added into common dictionary
			if (selected_filter.equals("import"))
			{
				this.wordTranslatelist.addAll(Handler.getList());
			}

			// fill the dictionary table
			for (WordTranslate w : this.wordTranslatelist)
				tableWordsRender.addItemEntry(w.getEngword(), w.getUkrword(), w.getPoints().toString());
			this.forwarding(wordsRenderer, tableWordsRender, request, response);

			//training mode
		}
		else
		{
			System.out.println(">TRAINING MODE");
			this.tableWordsRender.clearTable();

			//if entered word is correct
			if (entered_text.equalsIgnoreCase(wordTranslate.getEngword()))
			{
				//increasing the point
				this.wordTranslatelist.remove(wordTranslate);
				wordTranslate.setPoints(wordTranslate.getPoints() + 1);
				this.wordTranslatelist.add(wordTranslate);
				System.out.println(">CORRECT! The scores on word '" + wordTranslate.getEngword() + "' is up to "
						+ wordTranslate.getPoints());

				// set up new random word
				wordTranslate = nextWord();
				wordsRenderer.setUkr_word(wordTranslate.getUkrword());
				wordsRenderer.setEnglish_word(wordTranslate.getEngword());
			}
			// just inform
			else
				new Speech().speak("wrong input", "en-us");
				System.out.println(">WRONG! Try again!");
			this.forwarding(wordsRenderer, tableWordsRender, request, response);
		}
	}

	@Override
	public void destroy()
	{
		System.out.println(">destroying application");
		CsvWordsWriter csvWordsWriter = new CsvWordsWriter();
		csvWordsWriter.writeInFile(OUTPUT_FILE, this.wordTranslatelist);
		super.destroy();
	}
}
