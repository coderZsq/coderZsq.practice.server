package design_pattern.behaviour.visitor.case7;

public class WordFile extends ResourceFile {
    public WordFile(String s) {
        super(s);
    }

    @Override
    public ResourceFileType getType() {
        return ResourceFileType.WORD;
    }
}
