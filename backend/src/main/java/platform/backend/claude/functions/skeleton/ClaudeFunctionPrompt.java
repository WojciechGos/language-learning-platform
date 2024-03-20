package platform.backend.claude.functions.skeleton;

import java.util.List;

public class ClaudeFunctionPrompt {
    private String toolName;
    private String description;
    private List<Parameter> parameters;
    private String response = null;

    public ClaudeFunctionPrompt(String toolName, String description, List<Parameter> parameters, String response) {
        this.toolName = toolName;
        this.description = description;
        this.parameters = parameters;
        this.response = response;
    }
    public ClaudeFunctionPrompt(String toolName, String description, List<Parameter> parameters) {
        this.toolName = toolName;
        this.description = description;
        this.parameters = parameters;
    }

    public String getFunctionPrompt(){
        StringBuilder xml = new StringBuilder();
        xml.append("<tool_description>");
        xml.append("<tool_name>").append(toolName).append("</tool_name>");
        xml.append("<description>").append(description).append("</description>");
        xml.append("<parameters>");
        for (Parameter parameter : parameters) {
            xml.append(parameter.getSystemPrompt());
        }
        xml.append("</parameters>");
        xml.append("</tool_description>");

        if(this.response != null)
            xml.append(response);

        xml.append("In the result, don't include first message with \"Here is the result of...\", generate only the exercise.");

        return xml.toString();
    }
    public String getUserPrompt(){
        StringBuilder xml = new StringBuilder();
        xml.append("invoke generate_exercise");
        xml.append("<function_call>");
        xml.append("<invoke>");
        xml.append("<tool_name>").append(toolName).append("</tool_name>");
        xml.append("<parameters>");
        int i =1;
        for (Parameter parameter : parameters) {
            xml.append("<param").append(i).append(">").append(parameter.getValue()).append("</param").append(i).append(">");
            i++;
        }
        xml.append("</parameters>");
        xml.append("</invoke>");
        xml.append("</function_call>");

        return xml.toString();
    }
}
