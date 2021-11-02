package design_pattern.behaviour.visitor.case1;

public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void extract2txt() {
        //...
        System.out.println("Extract WORD.");
    }
}
