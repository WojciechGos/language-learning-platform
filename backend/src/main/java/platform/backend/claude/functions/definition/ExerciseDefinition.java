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

public abstract class ExerciseDefinition {

    private String description;
    private String topic;
    private String languageLevel;
    protected ClaudeFunctionPrompt functionPrompt;

    public ExerciseDefinition(String topic) {
        this.topic = topic;
    }



    public ExerciseDefinition(String topic, String languageLevel) {
        this.topic = topic;
        this.languageLevel = languageLevel;

    }

    public String getSystemPrompt(){
        return functionPrompt.getFunctionPrompt();
    }

    public String getUserPrompt(){
        return functionPrompt.getUserPrompt();
    }

    public abstract  List<ClaudeExerciseResponse> getResponseData(String responseXml);
}
