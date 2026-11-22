import com.sun.net.httpserver.SimpleFileServer;
import com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticSite {
    public static void main(String[] args) throws Exception {
        Path root = Files.createTempDirectory("site");
        Files.writeString(root.resolve("index.html"), "<h1>A Hike from Java 7</h1>");

        var server = SimpleFileServer.createFileServer(                          // Java 18
                new InetSocketAddress("127.0.0.1", 0), root.toAbsolutePath(), OutputLevel.NONE);
        server.start();
        try {
            var uri = URI.create("http://127.0.0.1:" + server.getAddress().getPort() + "/index.html");
            var response = HttpClient.newHttpClient()
                    .send(HttpRequest.newBuilder(uri).build(), HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode() + " " + response.body());
        } finally {
            server.stop(0);
        }
    }
}
