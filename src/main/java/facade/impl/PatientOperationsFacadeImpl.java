// PatientOperationsFacadeImpl.java
package facade.impl;

import facade.PatientOperationsFacade;
import modele.Patient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PatientOperationsFacadeImpl implements PatientOperationsFacade {

    @Override
    public Patient loginPatient(String numeroNational, String codeSecret) throws IOException {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("numeroNational", numeroNational);
        loginData.put("codeSecret", codeSecret);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(loginData);

        HttpPost postRequest = new HttpPost(ApiUrls.LOGIN_PATIENT);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, Patient.class);
            }
        }
        return null;
    }

}
