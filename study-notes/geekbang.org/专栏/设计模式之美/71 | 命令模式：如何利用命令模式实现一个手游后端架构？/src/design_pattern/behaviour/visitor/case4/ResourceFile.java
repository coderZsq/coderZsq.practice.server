package design_pattern.behaviour.visitor.case4;

public abstract class ResourceFile {
    protected String filePath;
    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }
    abstract public void accept(Extractor extractor);
    abstract public void accept(Compressor compressor);
}
