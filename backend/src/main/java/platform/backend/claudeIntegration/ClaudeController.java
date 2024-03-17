package platform.backend.claudeIntegration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/claude")
public class ClaudeController {
    private final ClaudeService claudeService;


//    @GetMapping
//    public String getClaudeResponse(@RequestBody ClaudeRequest claudeRequest) {
//        return claudeService.getClaudeResponse(prompt, model);
//    }

}
