package design_pattern.behaviour.visitor.case7;

public abstract class ResourceFile {
    protected String filePath;
    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }
    public abstract ResourceFileType getType();
}