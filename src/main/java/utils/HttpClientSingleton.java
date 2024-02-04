
package utils;
import java.io.IOException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpClientSingleton {

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    private HttpClientSingleton() {}

    public static CloseableHttpClient getInstance() {
        return httpClient;
    }

    // Assurez-vous d'appeler cette méthode lors de l'arrêt de l'application
    public static void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            // Log l'exception ou la gérer selon les besoins
        }
    }
}