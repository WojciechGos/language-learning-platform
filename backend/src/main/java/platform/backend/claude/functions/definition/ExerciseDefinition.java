package platform.backend.claude.functions.definition;

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

public class ExerciseDefinition {

    private String description;
    private String topic;
    private String languageLevel;
    private ClaudeFunctionPrompt functionPrompt;

    public ExerciseDefinition(String description, String topic, String languageLevel) {
        this.description = description;
        this.topic = topic;
        this.languageLevel = languageLevel;
        this.functionPrompt = new ClaudeFunctionPrompt("generate_exercise", description, List.of(
                new Parameter("topic", "string", "The main theme for exercise e.g. Nasa's spin offs, IT procedures","nasa's spin offs"),
                new Parameter("type", "string", "The exercise type e.g. grammar, fill the blanks","fill the blanks")
        ));
    }

    public String getSystemPrompt(){
        return functionPrompt.getFunctionPrompt();
    }

    public String getUserPrompt(){
        return functionPrompt.getUserPrompt();
    }

    public static List<ClaudeExerciseResponse> getResponseData(String responseXml){
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
