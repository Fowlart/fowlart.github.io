package MVC_package.view_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StripeCardPaymentController {

    @GetMapping("/stripe-card-payment")
    public String stripeCardPayment(Model model) {
        return "stripe-card-payment";
    }
}
