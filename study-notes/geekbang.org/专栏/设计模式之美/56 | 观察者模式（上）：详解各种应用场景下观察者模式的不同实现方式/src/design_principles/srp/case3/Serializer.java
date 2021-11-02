package design_principles.srp.case3;

import design_principles.srp.case2.Gson;

import java.util.Map;

public class Serializer {
    private static final String IDENTIFIER_STRING = "UEUEUE;";
    private Gson gson;

    public Serializer() {
        this.gson = new Gson();
    }

    public String serialize(Map<String, String> object) {
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append(IDENTIFIER_STRING);
        textBuilder.append(gson.toJson(object));
        return textBuilder.toString();
    }
}