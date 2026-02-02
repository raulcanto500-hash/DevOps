import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;

public class StatsEndpointTest {

    @Test
    
    void get_tasks_devuelve200_y_claves() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8080/api/stats/summary"))
        .GET()
        .build();

        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("\"total\""));
        assertTrue(response.body().contains("\"done\""));
        assertTrue(response.body().contains("\"done_ratio\""));
        assertTrue(response.body().contains("\"top_priority_titles\""));
    }    
}
