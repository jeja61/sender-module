package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class SenderService {

    private final HttpClient client = HttpClient.newHttpClient();

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.chat.id}")
    private String chatId;

    public void send(String text) throws Exception {
        String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
        String url = String.format(
                "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                token,
                chatId,
                encodedText
        );
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}