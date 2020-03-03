package MVC_package.view_controllers;

import entities.SessionDictionary;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    private SessionDictionary sessionDictionary;


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
            out.println(">>> dictionary with "+dictionary.size()+" was uploaded.");
            sessionDictionary.setDictionary(dictionary);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/table";
    }


}
