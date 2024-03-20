package platform.backend.claude.functions.definition.exercise;

import platform.backend.claude.functions.skeleton.ClaudeFunctionPrompt;

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
