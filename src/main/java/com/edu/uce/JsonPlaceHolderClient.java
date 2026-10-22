package com.edu.uce;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class JsonPlaceHolderClient {


    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    private final ObjectMapper mapper = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    public <T> T get(String path, TypeReference<T> type) throws IOException {
        URL url = URI.create(BASE_URL + path).toURL();
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        try {
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Accept", "application/json");
            int httpCode = conexion.getResponseCode();
            if (httpCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error en la conexión. Código: " + httpCode);

            }
            try (InputStream inputStream = conexion.getInputStream()) {
                return mapper.readValue(inputStream, type);
            }
        } finally {
            conexion.disconnect();
        }
    }
}
