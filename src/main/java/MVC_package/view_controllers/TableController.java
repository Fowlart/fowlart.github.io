package MVC_package.view_controllers;

import MVC_package.UserService;
import entities.SingleUser;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import services.WordProcessor;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private WordProcessor wordProcessor;
    @Autowired
    private UserService userService;
    @Autowired
    private SingleUser singleUser;

    //Todo: refactor for accepting a user from the authentication mechanism
    @ModelAttribute("user")
    private User getUser() {
        log.info(">>> user is added to the session attribute");
        return userService.getUsersList().get(0);
    }

    @GetMapping
    public String mainPage(Model model) throws IOException {
        log.info(">>> mainPage");
        return "table";
    }

    @PostMapping(value = "/uploadFile")
    public String submit(@RequestParam("aFile") MultipartFile file, Model model) {
        //  model.addAttribute("file", file);
        log.info(">>> file uploaded: " + file.getSize());
        return "redirect:/";
    }


}
