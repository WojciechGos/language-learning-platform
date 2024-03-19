package platform.backend.claude.Integration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.backend.claude.functions.definition.ExerciseDefinition;

@RestController
@RequestMapping("api/v1/claude")
public class ClaudeController {
    private final ClaudeService claudeService;

    public ClaudeController(ClaudeService claudeService) {
        this.claudeService = claudeService;
    }

    @PostMapping
    public ResponseEntity<String> getClaudeResponse(@RequestBody ClaudeRequest claudeRequest) {
        ExerciseDefinition exerciseDefinition = new ExerciseDefinition(
                " Generates exercises for english lessons on the given topic. \n" +
                        "        This is translate exercise. Generate polish sentences to the {{CONTENT}} and sentence english words to the {{ANSWER}}.\n" +
                        "        In the given structure:\n" +
                        "        <result>\n" +
                        "            <exercises>\n" +
                        "                <exercise>\n" +
                        "                    <content>{{CONTENT}}</content>\n" +
                        "                    <answer>{{ANSWER}}</answer>\n" +
                        "                </exercise>\n" +
                        "            </exercises>\n" +
                        "        </result>\n" +
                        "        In the <exercises></exercises> tag returns a list of objects wih 2 fields CONTENT and ANSWER. Create exercise that way\n" +
                        "        where all answers are different. Please take into account level of language that is given as\n" +
                        "        a parameter.\n" +
                        "        - content: string, Only one sentence of exercise - answer: string, a correct answer for the exercise",
                "nature spin'offs",
                "C2");

        System.out.println(exerciseDefinition.getSystemPrompt());
        System.out.println(exerciseDefinition.getUserPrompt());
//        ClaudeMessageResponse response = claudeService.getClaudeMessageResponse(exerciseDefinition.getSystemPrompt(), exerciseDefinition.getUserPrompt());
//        System.out.println(response);
        System.out.println(ExerciseDefinition.getResponseData("<result>\n" +
                "    <exercises>\n" +
                "        <exercise>\n" +
                "            <content>NASA technologia opracowana dla programów kosmicznych znalazła wiele zastosowań w życiu codziennym, na przykład {{BLANK}} używane w materacach pochodzą z technologii siedzeń wahadłowców.</content>\n" +
                "            <answer>memory foam</answer>\n" +
                "        </exercise>\n" +
                "        <exercise>\n" +
                "            <content>Okulary {{BLANK}} blokujące odblaski światła zostały pierwotnie opracowane dla astronautów.</content>\n" +
                "            <answer>polarized</answer>\n" +
                "        </exercise>\n" +
                "        <exercise>\n" +
                "            <content>Bezprzewodowe {{BLANK}} do pomiaru temperatury ciała u niemowląt wywodzą się z czujników używanych przez NASA do monitorowania temperatury astronautów.</content>\n" +
                "            <answer>thermometers</answer>\n" +
                "        </exercise>\n" +
                "        <exercise>\n" +
                "            <content>Technologia filtrowania wody NASA przyczyniła się do rozwoju przenośnych systemów {{BLANK}} wody pitnej.</content>\n" +
                "            <answer>purification</answer>\n" +
                "        </exercise>\n" +
                "    </exercises>\n" +
                "</result>"));
        return new ResponseEntity<>("response", HttpStatus.OK);
    }

}
