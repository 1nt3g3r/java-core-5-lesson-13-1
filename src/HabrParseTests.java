import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HabrParseTests {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://habr.com/ru/all/";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient client = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("response.statusCode() = " + response.statusCode());

        FileWriter fileWriter = new FileWriter("habr-main.html");
        fileWriter.write(response.body());
        fileWriter.close();
    }
}
