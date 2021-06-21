package MVC_package.rest_endpoints.stripe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(path = "/webhook")
public class Webhook {

    private static final Logger logger = LoggerFactory.getLogger(Webhook.class);

    @PostMapping
    public ResponseEntity getCustomerWithMeta(HttpServletRequest request) throws StripeException, IOException {

        String strCurrentLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((strCurrentLine = request.getReader().readLine()) != null) {
            stringBuilder.append(strCurrentLine);
        }
        logger.debug("> Received request: ");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Event event = gson.fromJson(stringBuilder.toString(), Event.class);
        logger.debug(event.getType());
        logger.debug(event.toString());

        return ResponseEntity.ok().build();
    }

}
