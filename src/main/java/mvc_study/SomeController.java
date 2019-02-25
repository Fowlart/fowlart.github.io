package mvc_study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SomeController {

    /*First method on start application*/
    /*ѕопадаем сюда на старте приложени€ (см. параметры аннотации и настройки пути после депло€) */

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ModelAndView main() {
        System.out.println("main() from SomeController");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}