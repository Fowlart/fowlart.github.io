package servlets;

import models.TableWordsRender;
import models.WordsRenderer;
import project.entities.item_implementations.words.WordTranslate;
import project.input_data_module.CsvWordsReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class WordForm extends HttpServlet {

    private static String PATH_TO_FILE = "c:/input2.csv";

    private List<WordTranslate> wordTranslatelist;
    private Random random;
    private TableWordsRender tableWordsRender;
    private WordsRenderer wordsRenderer;

    @Override
    public void init() throws ServletException {
        super.init();
        CsvWordsReader csvWordsReader = new CsvWordsReader();
        wordTranslatelist = csvWordsReader.getItemList(PATH_TO_FILE);
        random = new Random(47);
        tableWordsRender = new TableWordsRender();
        wordsRenderer = new WordsRenderer();

        for (WordTranslate it : wordTranslatelist) {
            System.out.println(it.getEngword() + "/" + it.getUkrword() +
                    "/" + it.getPoints());
        }
    }

    private void forwarding(WordsRenderer r1, TableWordsRender r2, HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        request.setAttribute("renderer", r1);
        request.setAttribute("tableRenderer", r2);
        request.getRequestDispatcher("words.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("synchronized");

        String search_text = request.getParameter("check_text");
        String selected_filter = request.getParameter("selectoid");

        // first start without parameters
        if ((search_text == null) || (selected_filter == null)) {
            System.out.println("FIRST START");
            search_text = "";
            selected_filter = "all";
        }

        // if word is skipped
        if (search_text.equalsIgnoreCase("")) {

            if (selected_filter.equals("all")) {
                this.tableWordsRender.clearTable();
                for (WordTranslate w : this.wordTranslatelist) {
                    tableWordsRender.addItemEntry(w.getEngword(), w.getUkrword(), w.getPoints().toString());
                }
                this.forwarding(wordsRenderer, tableWordsRender, request, response);
            }

            if (selected_filter.equals("min")) {
                System.out.println("In min");
            }

            if (selected_filter.equals("medium")) {
                System.out.println("In medium");
            }

            if (selected_filter.equals("max")) {
                System.out.println("In max");
            }

            //training mode
        } else {
            System.out.println("TRAINING MODE");
            this.tableWordsRender.clearTable();
            Optional<WordTranslate> optionalWordTranslate = this.wordTranslatelist.stream().
                    skip(this.random.nextInt(wordTranslatelist.size() - 1)).findAny();
            WordTranslate word = optionalWordTranslate.get();
            tableWordsRender.addItemEntry(word.getEngword(), word.getUkrword(), word.getPoints().toString());
            wordsRenderer.setUkr_word(word.getUkrword());
            this.forwarding(wordsRenderer, tableWordsRender, request, response);
        }
    }
}