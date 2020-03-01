package MVC_package.rest_endpoints;

import com.google.inject.internal.util.Lists;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/funApi")
public class EndpointsForFun {

    @GetMapping(value = "/getData", produces = "application/json")
    public List getWord(Model model) {
        List<String> data = Lists.newArrayList();
        data.add(" <p> <input type=\"radio\" name=\"size\" id=\"size_1\" value=\"FIRST\"> <label for=\"size_1\">FIRST</label> </p>");
        data.add("<p> <input type=\"radio\" name=\"size\" id=\"size_2\" value=\"SECOND\"> <label for=\"size_2\">SECOND</label> </p>");
//        data.add("<p> <input type=\"radio\" name=\"size\" id=\"size_3\" value=\"THIRD\"> <label for=\"size_3\">THIRD</label> </p>");
        return data;
    }

}
