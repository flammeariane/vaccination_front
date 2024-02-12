package facade.impl;

import bean.ListCentreBean;
import bean.StatCentreBeanIn;
import bean.StatCentreBeanOut;
import com.fasterxml.jackson.databind.ObjectMapper;
import facade.ResponsableUserFacade;
import java.io.IOException;
import modele.MembrePersonnel;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import utils.ApiUrls;
import utils.HttpClientSingleton;

public class ResponsableUserFacadeImpl implements ResponsableUserFacade {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ListCentreBean getListCentre(MembrePersonnel membrePersonnel) throws IOException {

        String requestBody = objectMapper.writeValueAsString(membrePersonnel);

        HttpPost postRequest = new HttpPost(ApiUrls.STAT_AFFICHER_CENTRE);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, ListCentreBean.class);
            }
        }
        return null;
    }

    public StatCentreBeanIn getStatCentre(StatCentreBeanOut statCentreBeanOut) throws IOException {

        String requestBody = objectMapper.writeValueAsString(statCentreBeanOut);

        HttpPost postRequest = new HttpPost(ApiUrls.STAT_CENTRE);
        postRequest.setEntity(new StringEntity(requestBody, "UTF-8"));
        postRequest.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse httpResponse = HttpClientSingleton.getInstance().execute(postRequest)) {
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                String responseBody = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                return objectMapper.readValue(responseBody, StatCentreBeanIn.class);
            }
        }
        return null;
    }

    public StatCentreBeanIn getStatCentre(StatCentreBeanIn centreRequest) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
