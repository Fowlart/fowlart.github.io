package servlets;

import models.TableWordsRender;
import models.WordsRenderer;
import project.entities.Item;
import project.entities.item_implementations.words.WordPropertie;
import project.entities.item_implementations.words.WordTranslate;
import project.input_data_module.CsvWordsReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WordForm extends HttpServlet {

    private static String PATH_TO_FILE = "c:/input2.csv";

    private List<WordTranslate> wordTranslatelist;

    @Override
    public void init() throws ServletException {
        super.init();
        CsvWordsReader csvWordsReader = new CsvWordsReader();
        wordTranslatelist = csvWordsReader.getItemList(PATH_TO_FILE);

        for (WordTranslate it: wordTranslatelist) {
            System.out.println(it.getEngword()+"/"+it.getUkrword()+
                    "/"+it.getPoints());
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("synchronized");

        WordsRenderer wordsRenderer = new WordsRenderer();
        WordPropertie eng = new WordPropertie("Собака");
        WordPropertie ukr = new WordPropertie("Dog");
        WordTranslate wordTranslateItem = new WordTranslate(eng, ukr, 0);
        wordsRenderer.setEnglish_word(wordTranslateItem.getEngword());
        wordsRenderer.setUkr_word(wordTranslateItem.getUkrword());

        TableWordsRender tableRenderer = new TableWordsRender();
        tableRenderer.addItemEntry(wordTranslateItem.getEngword(), wordTranslateItem.getUkrword(), "0");
        tableRenderer.addItemEntry(wordTranslateItem.getEngword(), wordTranslateItem.getUkrword(), "1");

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

                TableWordsRender tableWordsRender = new TableWordsRender();

                for (WordTranslate w : this.wordTranslatelist) {
                    tableWordsRender.addItemEntry(w.getUkrword(), w.getEngword(), w.getPoints().toString());
                }

                request.setAttribute("tableRenderer", tableWordsRender);
                request.getRequestDispatcher("words.jsp").forward(request, response);

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
        }


        request.setAttribute("renderer", wordsRenderer);
        request.setAttribute("tableRenderer", tableRenderer);
        request.getRequestDispatcher("words.jsp").forward(request, response);
    }
}
