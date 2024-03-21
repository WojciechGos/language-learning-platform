package platform.backend.claude.functions.definition.exercise.audio;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import platform.backend.claude.functions.definition.exercise.ClaudeExerciseResponse;
import platform.backend.claude.functions.definition.exercise.ExerciseDefinition;
import platform.backend.claude.functions.skeleton.ClaudeFunctionPrompt;
import platform.backend.claude.functions.skeleton.Parameter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class AudioExercise extends ExerciseDefinition {
    public AudioExercise(String topic) {
        super(topic);
        this.functionPrompt = new ClaudeFunctionPrompt(
                "generate_exercise",
                "<tool_description>\n" +
                        "    <tool_name>generate_exercise</tool_name>\n" +
                        "    <description> Generates exercises for english lessons on the given topic. This is audio \n" +
                        "        exercise, that helps us to learn extracting information from text. In the <exercises></exercises> tag\n" +
                        "        return one audio tag and list of objects wih 2 fields question and answer. Create exercise that way where\n" +
                        "        questions and answers are different. <result>\n" +
                        "            <exercises>\n" +
                        "                <audio>{{AUDIO}}</audio>\n" +
                        "                <exercise>\n" +
                        "                    <question>{{QUESTION}}</question>\n" +
                        "                    <answer>{{ANSWER}}</answer>\n" +
                        "                </exercise>\n" +
                        "            </exercises>\n" +
                        "        </result>\n" +
                        "        - audio: string: max 500 token. Short exercise about given topic in \n" +
                        "        - question: string: a short question about an audio\n" +
                        "        - answer: string: short answer on given question\n" +
                        "       </description>\n" +
                        "    <parameters>\n" +
                        "        <parameter>\n" +
                        "            <name>topic</name>\n" +
                        "            <type>string</type>\n" +
                        "            <description>The main theme for exercise e.g. Nasa's spin offs, IT procedures</description>\n" +
                        "        </parameter>\n" +
                        "    </parameters>\n" +
                        "</tool_description>",
                List.of(
                        new Parameter("topic", "string", "The main theme for exercise e.g. Nasa's spin offs, IT procedures",topic)
                )
        );
    }

    @Override
    public List<ClaudeExerciseResponse> getResponseData(String responseXml) {
        return null;
    }

    public ClaudeAudioExerciseResponse getAudioResponse(String responseXml) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            ByteArrayInputStream input = new ByteArrayInputStream(responseXml.getBytes());
            Document doc = dBuilder.parse(input);
            doc.getDocumentElement().normalize();

            String audio = doc.getElementsByTagName("audio").item(0).getTextContent();
            System.out.println("Audio: " + audio);

            NodeList exerciseList = doc.getElementsByTagName("exercise");

            List<ClaudeExerciseResponse> claudeExerciseResponseList = new ArrayList<>();

            for (int i = 0; i < exerciseList.getLength(); i++) {
                Node exerciseNode = exerciseList.item(i);
                if (exerciseNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element exerciseElement = (Element) exerciseNode;
                    String question = exerciseElement.getElementsByTagName("question").item(0).getTextContent();
                    String answer = exerciseElement.getElementsByTagName("answer").item(0).getTextContent();
                    claudeExerciseResponseList.add(new ClaudeExerciseResponse(question, answer));
                    System.out.println("Question: " + question);
                    System.out.println("Answer: " + answer);
                }
            }

            return new ClaudeAudioExerciseResponse(audio, claudeExerciseResponseList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
