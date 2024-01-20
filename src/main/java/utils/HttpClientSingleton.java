
package utils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class HttpClientSingleton {
    
     private static CloseableHttpClient httpClient;

    private HttpClientSingleton() {}

    public static CloseableHttpClient getInstance() {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }
    
}
