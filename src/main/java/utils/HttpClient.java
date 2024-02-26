package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {

    public static <R, T> R post(T request, String url, Class<R> responseClass) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String requestBody = objectMapper.writeValueAsString(request);

        HttpPost postRequest = new HttpPost(url);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, responseClass);
            }
        }
        return null;
    }
}
