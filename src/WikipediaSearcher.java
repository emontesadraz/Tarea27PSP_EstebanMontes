import java.io.*;
import java.net.http.HttpClient;
                import java.net.http.HttpRequest;
                import java.net.http.HttpResponse;
                import java.util.Scanner;

                public class WikipediaSearcher {
                    public static void main(String[] args) {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Introduce el término a buscar en Wikipedia: ");
                        String query = sc.nextLine();
                        String url = "https://es.wikipedia.org/wiki/" + query;

                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder().uri(java.net.URI.create(url)).GET().build();

                        try {
                            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                            if (response.statusCode() == 200) {
                                System.out.println("La búsqueda se ha realizado correctamente. " + response.statusCode());
                                BufferedWriter writer = new BufferedWriter(new FileWriter("result.html"));
                                writer.write(response.body());
                                writer.close();
                                System.out.println("El resultado se ha guardado en el archivo result.html");

                            } else {
                                System.out.println("Error: " + response.statusCode());
                            }
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }