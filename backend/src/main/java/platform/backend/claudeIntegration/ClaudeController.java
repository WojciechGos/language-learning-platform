package platform.backend.claudeIntegration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/claude")
public class ClaudeController {
    private final ClaudeService claudeService;

    public ClaudeController(ClaudeService claudeService) {
        this.claudeService = claudeService;
    }


    @PostMapping
    public ResponseEntity<ClaudeMessageResponse> getClaudeResponse(@RequestBody ClaudeRequest claudeRequest) {
        ClaudeMessageResponse response = claudeService.getClaudeMessageResponse("hi");
        System.out.println(response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
