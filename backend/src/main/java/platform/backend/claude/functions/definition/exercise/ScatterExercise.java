package platform.backend.claude.functions.definition.exercise;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import platform.backend.claude.functions.skeleton.ClaudeFunctionPrompt;
import platform.backend.claude.functions.skeleton.Parameter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

public class ScatterExercise extends ExerciseDefinition{

    public ScatterExercise(String topic) {
        super(topic);
        this.functionPrompt = new ClaudeFunctionPrompt("generate_exercise", " Generates exercises for english lessons.\n" +
                "        Returns a object wih 2 fields\n" +
                "        - content: string, word scatters with a polish translations, for example: OGD (pies)\n" +
                "        - correct_answer: string, a correct answer for the exercise, for example: DOG",
                List.of(
                        new Parameter("topic", "string", "The main theme for exercise e.g. food vocabulary, names of jobs",topic)
                ),
                "this is response format" +
                        "<result>\n" +
                        "    <content>{{CONTENT}}</content>\n" +
                        "    <correct_answer>{{CORRECT_ANSWER}}</correct_answer>\n" +
                        "</result>"
        );
    }

    @Override
    public List<ClaudeExerciseResponse> getResponseData(String responseXml) {
        try {
            String content = getContent(responseXml);
            String correctAnswer = getCorrectAnswer(responseXml);
            System.out.println("Content: " + content);
            System.out.println("Correct Answer: " + correctAnswer);

            String[] contents = content.split(",");
            String[] answers = correctAnswer.split(",");

            System.out.println("Answers length: " + answers.length);
            System.out.println("content length: " + contents.length);
            List<ClaudeExerciseResponse> claudeExerciseResponsesList = new ArrayList<>();
            for(int i = 0; i < contents.length; i++){
                System.out.println("Content: " + contents[i]);
                System.out.println("answers: " + answers[i]);
                if( i <= (answers.length-1))
                    claudeExerciseResponsesList.add(new ClaudeExerciseResponse(contents[i], answers[i]));
            }

            return claudeExerciseResponsesList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String getContent(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new java.io.ByteArrayInputStream(xmlString.getBytes()));
        Element root = doc.getDocumentElement();
        NodeList contentNodeList = root.getElementsByTagName("content");
        Node contentNode = contentNodeList.item(0);
        return contentNode.getTextContent();
    }
    private static String getCorrectAnswer(String xmlString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new java.io.ByteArrayInputStream(xmlString.getBytes()));
        Element root = doc.getDocumentElement();
        NodeList correctAnswerNodeList = root.getElementsByTagName("correct_answer");
        Node correctAnswerNode = correctAnswerNodeList.item(0);
        return correctAnswerNode.getTextContent();
    }

}
