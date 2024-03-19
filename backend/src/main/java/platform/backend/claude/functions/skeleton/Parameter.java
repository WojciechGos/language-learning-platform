package platform.backend.claude.functions.skeleton;

import lombok.Getter;

@Getter
public class Parameter {
    private String name;
    private String type;
    private String description;
    private String value;


    public Parameter(String name, String type, String description, String value) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.value = value;
    }

    public String getSystemPrompt(){
        StringBuilder xml = new StringBuilder();
        xml.append("<parameter>");
        xml.append("<name>").append(name).append("</name>");
        xml.append("<type>").append(type).append("</type>");
        xml.append("<description>").append(description).append("</description>");
        xml.append("</parameter>");
        return xml.toString();
    }
    public String getUserPrompt(int index){
        StringBuilder xml = new StringBuilder();
        xml.append("<parameter>");
        xml.append("<param").append(index).append(">").append(value).append("</param").append(index).append(">");
        xml.append("</parameter>");
        return xml.toString();
    }
}
