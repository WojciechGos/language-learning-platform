package platform.backend.claude.functions.definition.exercise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import platform.backend.claude.functions.skeleton.ClaudeFunctionPrompt;
import platform.backend.claude.functions.skeleton.Parameter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class PastSPastCExercise extends ExerciseDefinition{

    public PastSPastCExercise(String topic, String languageLevel) {
        super(topic, languageLevel);
        this.functionPrompt = new ClaudeFunctionPrompt("generate_exercise",
                "Generates exercises for english lessons on the given topic. This is grammar\n" +
                        "        exercise, that helps us to learn past simple and past continoues, so please give in the\n" +
                        "        brackets next to the blank spot verb. In the given structure. In the <exercises></exercises>\n" +
                        "        tag returns a list of objects wih 2 fields CONTENT and ANSWER. Create exercise that way\n" +
                        "        where all answers are different. Please take into account level of language that is given as\n" +
                        "        a parameter. \n" +
                        "        <result>\n" +
                        "            <exercises>\n" +
                        "                <exercise>\n" +
                        "                    <content>{{CONTENT}}</content>\n" +
                        "                    <answer>{{ANSWER}}</answer>\n" +
                        "                </exercise>\n" +
                        "            </exercises>\n" +
                        "        </result>\n" +
                        "        - content: string, Only one sentence of exercise - correct_answer: string, a correct answer\n" +
                        "        for the exercise ",
                List.of(
                new Parameter("topic", "string", "The main theme for exercise e.g. Nasa's spin offs, IT procedures",topic),
                new Parameter("level", "string", "It is language level e.g. B1, B2, C2",languageLevel)
        ));
    }

    @Override
    public List<ClaudeExerciseResponse> getResponseData(String responseXml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(responseXml.getBytes());
            Document doc = dBuilder.parse(input);

            doc.getDocumentElement().normalize();

            NodeList exerciseList = doc.getElementsByTagName("exercise");
            List<ClaudeExerciseResponse> claudeExerciseResponseList = new ArrayList<>();
            for (int i = 0; i < exerciseList.getLength(); i++) {
                Node exerciseNode = exerciseList.item(i);

                if (exerciseNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element exerciseElement = (Element) exerciseNode;
                    String content = exerciseElement.getElementsByTagName("content").item(0).getTextContent();
                    String answer = exerciseElement.getElementsByTagName("answer").item(0).getTextContent();

                    claudeExerciseResponseList.add(new ClaudeExerciseResponse(content, answer));

                    System.out.println("Content: " + content);
                    System.out.println("Answer: " + answer);

                }
            }
            return claudeExerciseResponseList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
