import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTaskEndpointTest {

    @Test
    void post_tasks_devuelve201_y_campos_basicos() throws Exception {

         HttpClient client = HttpClient.newHttpClient();

        // JSON que vamos a enviar
        String json = """
                {
                    "title": "Comprar pan",
                    "priority": 3,
                    "tags": ["casa"]
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/tasks/"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verificamos status code 201
        assertEquals(201, response.statusCode());

        // Verificamos que el body contenga los campos básicos
        String body = response.body();
        assertTrue(body.contains("\"title\":\"Comprar pan\""));
        assertTrue(body.contains("\"priority\":3"));
        assertTrue(body.contains("\"done\":false"));

        // Verificamos que tenga un id (número)
        assertTrue(body.matches(".*\"id\"\\s*:\\s*\\d+.*"));
    }
}
