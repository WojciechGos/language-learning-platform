package platform.backend.claude.functions.definition;

import platform.backend.claude.functions.skeleton.ClaudeFunction;
import platform.backend.claude.functions.skeleton.Parameter;

import java.util.List;

public class ExerciseDefinition {
    private String topic;

    private String response = "this is response format\n" +
            "<result>\n" +
            "    <content>{{CONTENT}}</content>\n" +
            "    <correct_answer>{{CORRECT_ANSWER}}</correct_answer>\n" +
            "</result>";


    public String getXml(){
        return new ClaudeFunction("generate_exercise", "Generates exercises for english lessons.\n" +
                "       Returns a object wih 2 fields\n" +
                "        - content: string,  Only one sentence of exercise\n" +
                "        - correct_answer: string, a correct answer for the exercise", List.of(
                        new Parameter("topic", "string", "The main theme for exercise e.g. Nasa's spin offs, IT procedures","nasa's spin offs"),
                        new Parameter("type", "string", "The exercise type e.g. grammar, fill the blanks","fill the blanks")
        ),response ).getXML();
    }
}
