package MVC_package.view_controllers;

import com.google.inject.internal.util.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/radioButtonVariants")
public class RadioButtonVariantController {

    @GetMapping
    public String mainPage(Model model) throws IOException {
        System.out.println(">>> radioButtonVariantForm");
        return "radioButtonVariantForm";
    }

    @GetMapping("/radioButtonVariantFormAction")
    public String processradioButtonVariantForm(@RequestParam("size") String size) throws IOException {
        System.out.println(">>> action accepted!");
        System.out.println(">>> chosen variant: " + size);
        return "radioButtonVariantForm";
    }
}
