package MVC_package.rest_endpoints.stripe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import data_base.mongo.LogEntriesMongoRepository;
import dtos.PaymentIntentDTO;
import entities.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@RestController
public class StripeController {

    private static final Logger logger = LoggerFactory.getLogger(StripeController.class);

    private final LogEntriesMongoRepository logEntriesMongoRepository;

    @Value("${STRIPE.SECRET_KEY}")
    private String stripeSK;

    public StripeController(LogEntriesMongoRepository logEntriesMongoRepository) {
        this.logEntriesMongoRepository = logEntriesMongoRepository;
    }

    @PostMapping(path = "/webhook")
    public ResponseEntity acceptHook(HttpServletRequest request) throws IOException {

        String strCurrentLine;
        StringBuilder body = new StringBuilder();
        while ((strCurrentLine = request.getReader().readLine()) != null) {
            body.append(strCurrentLine);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Event event = gson.fromJson(body.toString(), Event.class);
        logger.debug("> Received request: ");
        LogEntry logEntry = new LogEntry();
        logEntry.setCreationTime(new Date());
        logEntry.setJson(event.toJson());
        logEntriesMongoRepository.save(logEntry);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/payment-intent")
    public ResponseEntity<String> paymentIntent(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {

        PaymentIntentCreateParams params = PaymentIntentCreateParams
                .builder()
                .setAmount(paymentIntentDTO.amount)
                .setCurrency("usd")
                .build();

        RequestOptions requestOptions = RequestOptions.builder().setApiKey(stripeSK).build();

        PaymentIntent intent = PaymentIntent.create(params, requestOptions);

        PaymentIntentConfirmParams paymentIntentConfirmParams = PaymentIntentConfirmParams
                .builder()
                .setPaymentMethod("pm_card_visa")
                .build();

        System.out.println(intent);

        //   PaymentIntent confirmedPaymentIntent = intent.confirm(paymentIntentConfirmParams, requestOptions);// separate call

        return ResponseEntity.ok().body(intent.getClientSecret());
    }

}
