import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HabrJsoupTests {
    public static void main(String[] args) throws IOException {
        String url = "https://habr.com/ru/all/";

        Document document = Jsoup.connect(url).get();

        Element head = document.getElementsByTag("head").first();
        Element title = head.getElementsByTag("title").first();

        System.out.println("title.text() = " + title.text());

    }
}
