package org.example.Spotify_API;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpotifyApiClient {

    public static void main(String[] args) {
        // Replace these with your actual client ID and client secret
        String clientId = "7ee25102ece74232b479ba6d654c8dad";
        String clientSecret = "74cebc3cfcbe4131bb42a32363e85aee";

        String accessToken = getAccessToken(clientId, clientSecret);
        System.out.println("Access Token: " + accessToken);
    }

    public static String getAccessToken(String clientId, String clientSecret) {
        String accessTokenUrl = "https://accounts.spotify.com/api/token";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(accessTokenUrl);

            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            params.add(new BasicNameValuePair("client_id", clientId));
            params.add(new BasicNameValuePair("client_secret", clientSecret));

            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String responseBody = EntityUtils.toString(entity);
                return extractAccessToken(responseBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Helper method to extract the access token using Jackson JSON parsing
    private static String extractAccessToken(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            AccessTokenResponse tokenResponse = objectMapper.readValue(responseBody, AccessTokenResponse.class);
            return tokenResponse.getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Helper class to represent the access token response structure
    private static class AccessTokenResponse {
        @JsonProperty("access_token")
        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }
    }
}
