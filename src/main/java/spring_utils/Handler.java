package spring_utils;


import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.File;

public class Handler {

    public Message<File> handleFile(File input) {
        System.out.println(">Copying file: " + input.getAbsolutePath());

        return new Message<File>() {
            @Override
            public File getPayload() {
                return input;
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        };
    }
}