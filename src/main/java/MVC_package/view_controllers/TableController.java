package MVC_package.view_controllers;

import entities.Logger;
import entities.SessionDictionary;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
import io_data_module.CsvWordsWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import services.WordProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.System.out;

@Controller
public class TableController {
    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private CsvWordsReader csvWordsReader;
    @Autowired
    private CsvWordsWriter csvWordsWriter;
    @Autowired
    private SessionDictionary sessionDictionary;
    @Autowired
    private Logger logger;
    private static int counter;

    @GetMapping("/downloadFile")
    public void downloadFile(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        out.println("SENDING A FILE");
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
    public String mainPage(Model model) throws IOException {
        out.println("main page");
        // out.println(model.asMap().get("eMail"));
        model.addAttribute("logger",logger.getFullLog());
        out.println("processing " + sessionDictionary);
        return "table";
    }

    @PostMapping(value = "/uploadFile")
    public String submit(@RequestParam("eMail") String eMail, @RequestParam("aFile") MultipartFile file, Model model) {
        if (!sessionDictionary.isDictionaryDownloaded()) {
            out.println("file uploaded: " + file.getSize());
            try {
                List<WordTranslate> dictionary = csvWordsReader.getItemListFromFile(file.getBytes());
                out.println("Dictionary with " + dictionary.size() + " words was uploaded, for user [" + eMail + "], file size: " + file.getSize() + " bytes.");
                logger.writeInfo("Dictionary with " + dictionary.size() + " words was uploaded, for user [" + eMail + "], file size: " + file.getSize() + " bytes.");
                sessionDictionary.setDictionary(dictionary);
                sessionDictionary.setId(String.valueOf(++counter));
                model.addAttribute("eMail", eMail);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "table";
        } else {
            out.println("dictionary already exist in the current session: " + sessionDictionary);
            return "table";
        }
    }
}
