package ec.edu.uce.ProyectoJuego.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.edu.uce.ProyectoJuego.model.Hero;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ServerConnection {
    private HttpClient client;
    private ObjectMapper objectMapper;
    private String url = "http://localhost:8080/user/";

    public ServerConnection() {
        client = HttpClient.newHttpClient();
        objectMapper = new ObjectMapper();
    }

    public void sendHeroData(Hero heroData) {
        try {
            String jsonString = objectMapper.writeValueAsString(heroData);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/user/save"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserData(Hero heroData) {
        try {
            String jsonString = objectMapper.writeValueAsString(heroData);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/user/update"))
                    .timeout(Duration.ofMinutes(1))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error: " + response.statusCode());
            } else {
                System.out.println("User updated successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo para actulizar un usuario
    public void loadUser(Hero hero) {
        try {
            // URL del endpoint en el otro proyecto
            String url = "http://localhost:8080/user/updateUser";

            // Configuración de cabeceras para la solicitud PUT

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Instancia de RestTemplate
            RestTemplate restTemplate = new RestTemplate();

            // Crear el objeto User con los campos que se van a actualizar
            Hero updatedUser = new Hero();
            updatedUser.setId(hero.getId());
            updatedUser.setName(hero.getName());
            updatedUser.setPassword(hero.getPassword());
            updatedUser.setLife(hero.getLife());
            updatedUser.setScore(hero.getScore());
            updatedUser.setCurrentLevelIndex(hero.getCurrentLevelIndex());
            updatedUser.setNumEnemies(hero.getNumEnemies());

            //System.out.println(updatedUser.toString());

            // Crear la entidad HTTP con los datos y las cabeceras
            HttpEntity<Hero> request = new HttpEntity<>(updatedUser, headers);

            // Realizar la solicitud PUT y obtener la respuesta
            ResponseEntity<Hero> response = restTemplate.exchange(
                    url + "?name=" + hero.getName() + "&password=" + hero.getPassword(),
                    HttpMethod.PUT,
                    request,
                    Hero.class
            );

            // Manejo de la respuesta
            if (response.getStatusCode().is2xxSuccessful()) {

                Hero user = response.getBody();
                if (user != null) {
                    System.out.println("Solicitud PUT exitosa. Usuario actualizado: " + user.toString());
                } else {
                    System.out.println("Usuario no encontrado.");
                }

            } else {
                System.out.println("La solicitud PUT no se pudo enviar correctamente");
            }

        } catch (Exception e) {
            System.out.println("Error durante la solicitud PUT: " + e.getMessage());
        }
    }
}