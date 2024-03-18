package platform.backend.claude.functions.skeleton;

public class Invoke {
    private String topic;
    private String type;

    public Invoke(String topic, String type) {
        this.topic = topic;
        this.type = type;
    }

    public String getXml() {
        return "<invoke topic=\"" + topic + "\" type=\"" + type + "\" />";
    }
}
