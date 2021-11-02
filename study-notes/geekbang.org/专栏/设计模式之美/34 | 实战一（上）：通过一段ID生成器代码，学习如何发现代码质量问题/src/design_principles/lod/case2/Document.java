package design_principles.lod.case2;

import design_principles.lod.case1.Html;

public class Document {
    private Html html;
    private String url;

    public Document(String url, Html html) {
        this.html = html;
        this.url = url;
    }
    //...
}