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

public class ThirdConditionalExercise extends ExerciseDefinition {

    public ThirdConditionalExercise(String topic) {
        super(topic);
        this.functionPrompt = new ClaudeFunctionPrompt("generate_exercise",
                "Generates exercises for english lessons on the given topic. This is conditional\n" +
                        "        exercise, that helps us to learn third conditional. \n" +
                        "        In the <exercises></exercises> tag returns a list of objects wih 2 fields CONTENT and ANSWER. Create exercise that way\n" +
                        "        where contents and answers are different. \n" +
                        "        <result>\n" +
                        "            <exercises>\n" +
                        "                <exercise>\n" +
                        "                    <content>{{CONTENT}}</content>\n" +
                        "                    <answer>{{ANSWER}}</answer>\n" +
                        "                </exercise>\n" +
                        "            </exercises>\n" +
                        "        </result>\n" +
                        "        - content: string: 2 sentences in basic form e.g. \"The crash happened; Gran Canaria Airport was closed\", \"The controller was not able to see aircraft on the ground; he couldn't give clearer instructions\"\n" +
                        "        - answer: string, a correct answer. \"The crash would not have happened if Gran Canaria Airport had not been closed.\", \"If the controller had been able to see aircraft on the ground, he could have given clearer instructions\"",
                List.of(
                        new Parameter("topic", "string", "The main theme for exercise e.g. Nasa's spin offs, IT procedures",topic))
                );
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
