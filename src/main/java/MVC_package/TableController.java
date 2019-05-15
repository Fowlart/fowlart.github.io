package MVC_package;

import com.google.inject.internal.util.Lists;
import data_base.WordTranslateRepository;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/table")
public class TableController {

    private List<WordTranslate> list;
    private final WordTranslateRepository repository;

    @Autowired
    public TableController(WordTranslateRepository repository) {
        this.repository = repository;
    }

    @ModelAttribute
    public void addTable(Model model) {
        //fetching from the file to database
        CsvWordsReader csvWordsReader = new CsvWordsReader();
        csvWordsReader.getItemList("db.csv").stream().forEach(repository::save);

        list = Lists.newArrayList();
        //fetching from the database to list

        repository.findAll().forEach(list::add);
        model.addAttribute("table", list);
    }

    @GetMapping
    public String mainPage(Model model) {
        log.info("word with id 1: " + repository.findById((long) 1));
        log.info("mainPage");
        return "table";
    }

}
