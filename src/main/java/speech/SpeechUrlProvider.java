package speech;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

public class SpeechUrlProvider {

    private String API_KEY = "AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw";

    public URL get_url(String word, String code) throws IOException {
        String encoded = URLEncoder.encode(word, "UTF-8"); //Encode
        StringBuilder sb = new StringBuilder("https://www.google.com/speech-api/v2/synthesize?enc=mpeg" +
                "&client=chromium");
        sb.append("&key=" + API_KEY);
        sb.append("&text=" + encoded);
        sb.append("&lang=" + code);
        sb.append("&speed=" + 0.5);
        sb.append("&pitch=" + 0.5);
        URL url = new URL(sb.toString());
        return url;
    }
}
