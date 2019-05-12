package MVC_package;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsReader;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/table")
public class MainController {

    List<WordTranslate> list;

    @ModelAttribute
    public void addTable(Model model) {
        CsvWordsReader csvWordsReader = new CsvWordsReader();
        list = csvWordsReader.getItemList("db.csv");
        model.addAttribute("table", list);
    }

    @GetMapping
    public String mainPage(Model model) {
       //log.info( "contains word? "+model.containsAttribute("addedWord") );
        // WordTranslate word = (WordTranslate)model.asMap().get("addedWord");
        //log.info(word.toString());
        //list.add(word);
        log.info("mainPage");
        return "table";
    }

}
