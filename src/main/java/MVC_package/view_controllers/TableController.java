package MVC_package.view_controllers;

import entities.SessionDictionary;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
import io_data_module.CsvWordsWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import services.WordProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.System.out;

@Slf4j
@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private CsvWordsReader csvWordsReader;
    @Autowired
    private CsvWordsWriter csvWordsWriter;
    @Autowired
    private SessionDictionary sessionDictionary;

    @GetMapping("/downloadFile")
    public void downloadFile(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        out.println(">>> SENDING A FILE");
        response.setContentType("text/csv;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=myFile.csv");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String csv = csvWordsWriter.getCsv(sessionDictionary.getDictionary());
        out.println(csv);
        out.flush();
        out.close();
    }

    @GetMapping
    public String mainPage(Model model) throws IOException {
        out.println(">>> mainPage");
        out.println(">>> processing " + sessionDictionary);
        return "table";
    }

    @PostMapping(value = "/uploadFile")
    public String submit(@RequestParam("aFile") MultipartFile file, Model model) {
        out.println(">>> file uploaded: " + file.getSize());
        try {
            File targetFile = new File("targetFile.tmp");
            targetFile.createNewFile();
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(file.getBytes());
            List<WordTranslate> dictionary = csvWordsReader.getItemListFromFile(targetFile);
            out.println(">>> dictionary with " + dictionary.size() + " was uploaded.");
            sessionDictionary.setDictionary(dictionary);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/table";
    }


}
