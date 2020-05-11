package MVC_package;

import entities.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.lang.System.*;

@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
        out.println("Launcher was started");
        try {
            InetAddress ip = InetAddress.getLocalHost();
            out.println(ip.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}