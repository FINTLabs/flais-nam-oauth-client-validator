package io.flais;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SummaryController {

    private final ValidatorService validatorService;

    public SummaryController(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @GetMapping("summary")
    public ResponseEntity<Summary> getSummary() {

        return ResponseEntity.ok(validatorService.getSummary());

    }
}
