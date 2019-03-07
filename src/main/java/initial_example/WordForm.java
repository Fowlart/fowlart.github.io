package initial_example;

import models.WordsRenderer;
import project.entities.item_implementations.words.WordPropertie;
import project.entities.item_implementations.words.WordTranslateItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WordForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("synchronized");
        WordsRenderer wordsRenderer = new WordsRenderer();
        WordPropertie eng = new WordPropertie("Собака");
        WordPropertie ukr = new WordPropertie("Dog");
        WordTranslateItem wordTranslateItem = new WordTranslateItem(eng,ukr);
        System.out.println(eng.getType());
        System.out.println(ukr.getType());
        wordsRenderer.setEnglish_word(wordTranslateItem.getEngword());
        wordsRenderer.setUkr_word(wordTranslateItem.getUkrword());
        request.setAttribute("renderer", wordsRenderer);
        request.getRequestDispatcher("words.jsp").forward(request, response);
    }
}
