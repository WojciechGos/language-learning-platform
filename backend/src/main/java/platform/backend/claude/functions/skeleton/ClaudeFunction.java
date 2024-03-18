package platform.backend.claude.functions.skeleton;

import java.util.List;

public class ClaudeFunction {
    private String toolName;
    private String description;
    private List<Parameter> parameters;
    private String response;



    public ClaudeFunction(String toolName, String description, List<Parameter> parameters, String response) {
        this.toolName = toolName;
        this.description = description;
        this.parameters = parameters;
        this.response = response;
    }

    public String getXML(){
        StringBuilder xml = new StringBuilder();
        xml.append("<tool_description>");
        xml.append("<tool_name>").append(toolName).append("</tool_name>");
        xml.append("<description>").append(description).append("</description>");
        xml.append("<parameters>");
        for (Parameter parameter : parameters) {
            xml.append(parameter.getXML());
        }
        xml.append("</parameters>");
        xml.append("</tool_description>");

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


        xml.append(response);
        return xml.toString();
    }
}
