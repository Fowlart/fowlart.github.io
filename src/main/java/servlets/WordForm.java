package servlets;

import models.TableWordsRender;
import models.WordsRenderer;
import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsReader;
import project.io_data_module.CsvWordsWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordForm extends HttpServlet {

    private static String INPUT_FILE = "dictionary/dic1.csv";
    private static String OUTPUT_FILE = "dictionary/dic1.csv";

    private List<WordTranslate> wordTranslatelist;
    private Random random;
    private TableWordsRender tableWordsRender;
    private WordsRenderer wordsRenderer;
    private WordTranslate wordTranslate;

    @Override
    public void init() throws ServletException {
        super.init();

        // setup
        CsvWordsReader csvWordsReader = new CsvWordsReader();
        wordTranslatelist = csvWordsReader.getItemList(INPUT_FILE);
        random = new Random(47);
        tableWordsRender = new TableWordsRender();
        wordsRenderer = new WordsRenderer();
        wordTranslate = nextWord();
        wordsRenderer.setEnglish_word(wordTranslate.getEngword());
        wordsRenderer.setUkr_word(wordTranslate.getUkrword());

        for (WordTranslate it : wordTranslatelist) {
            System.out.println(it.getEngword() + "/" + it.getUkrword() +
                    "/" + it.getPoints());
        }
    }
    //Todo: check this method. StackOverflowError here.
    private WordTranslate nextWord() {
        WordTranslate wordTranslate = wordTranslatelist.stream().skip(this.random.nextInt(wordTranslatelist.size() - 1)).findAny().get();
        if (wordTranslate.getPoints() < 50) return wordTranslate;
        System.out.println("skip '"+wordTranslate.getEngword()+"' with "+wordTranslate.getPoints()+" points");
        return nextWord();
    }

    private void forwarding(WordsRenderer r1, TableWordsRender r2, HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        request.setAttribute("renderer", r1);
        request.setAttribute("tableRenderer", r2);
        request.getRequestDispatcher("words.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.setCharacterEncoding("UTF8");
        System.out.println("doGet Starting...");
        String entered_text = request.getParameter("check_text");
        String selected_filter = request.getParameter("selectoid");
        request.setAttribute("renderer", this.wordsRenderer);
        this.tableWordsRender.clearTable();

        // first start without parameters
        if ((entered_text == null) || (selected_filter == null)) {
            System.out.println("FIRST START");
            entered_text = "";
            selected_filter = "min";
        }

        // if word is skipped
        if (entered_text.equalsIgnoreCase("")) {

            //by default going HERE.
            if (selected_filter.equals("min")) {
                wordTranslatelist = this.wordTranslatelist.stream().sorted((w1, w2) -> w1.getPoints() - w2.getPoints()).
                        collect(Collectors.toList());

            }

            if (selected_filter.equals("max")) {
                wordTranslatelist = this.wordTranslatelist.stream().sorted((w1, w2) -> w2.getPoints() - w1.getPoints()).
                        collect(Collectors.toList());
            }
            // fill the dictionary table
            for (WordTranslate w : this.wordTranslatelist)
                tableWordsRender.addItemEntry(w.getEngword(), w.getUkrword(), w.getPoints().toString());
            this.forwarding(wordsRenderer, tableWordsRender, request, response);

            //training mode
        } else {
            System.out.println("TRAINING MODE");
            this.tableWordsRender.clearTable();

            //if entered word is correct
            if (entered_text.equalsIgnoreCase(wordTranslate.getEngword())) {
                //increasing the point
                this.wordTranslatelist.remove(wordTranslate);
                wordTranslate.setPoints(wordTranslate.getPoints() + 1);
                this.wordTranslatelist.add(wordTranslate);
                System.out.println("CORRECT! The scores on word '" + wordTranslate.getEngword() + "' is up to "
                        + wordTranslate.getPoints());

                // set up new random word
                wordTranslate = nextWord();
                wordsRenderer.setUkr_word(wordTranslate.getUkrword());
                wordsRenderer.setEnglish_word(wordTranslate.getEngword());
            }
            // just inform
            else System.out.println("WRONG! Try again!");
            this.forwarding(wordsRenderer, tableWordsRender, request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroying application");
        CsvWordsWriter csvWordsWriter = new CsvWordsWriter();
        csvWordsWriter.writeInFile(OUTPUT_FILE, this.wordTranslatelist);
        super.destroy();
    }
}