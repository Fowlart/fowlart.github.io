package MVC_package.view_controllers;

import data_base.mongo.WordMongoRepository;
import entities.Logger;
import entities.SessionDictionary;
import io_data_module.CsvWordsWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.WordProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.System.out;

@Controller
public class TableController {
    private static int counter;
    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private CsvWordsWriter csvWordsWriter;
    @Autowired
    private SessionDictionary sessionDictionary;
    @Autowired
    private Logger logger;
    @Autowired
    private WordMongoRepository wordMongoRepository;

    // Todo
    @GetMapping("/logger")
    public String getLogger() throws IOException {
        return "logger";
    }

    @GetMapping("/downloadFile")
    public void downloadFile(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        logger.writeInfo("Sending a file.");
        response.setContentType("text/csv;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=myFile.csv");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String csv = csvWordsWriter.getCsv(sessionDictionary.getDictionary());
        out.println(csv);
        out.flush();
        out.close();
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("logger", logger.getFullLog());
        logger.writeInfo("Processing " + sessionDictionary + ".");
        return "table";
    }




    @PostMapping(value = "/deleteWords")
    public String deleteWordsFromVocabulary(@RequestParam List<String> idsItemForDelete) {
        if (!idsItemForDelete.isEmpty()) {
            out.println("ready for deleting:"+idsItemForDelete);
            idsItemForDelete.forEach(wordMongoRepository::deleteById);
        }
        return "redirect:/create";
    }
}
