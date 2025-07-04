package org.example;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
@AllArgsConstructor
public class SenderController {
    private final SenderService senderService;

    @PostMapping
    public ResponseEntity<Void> notify(@RequestBody String message) {
        try {
            senderService.send(message);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}