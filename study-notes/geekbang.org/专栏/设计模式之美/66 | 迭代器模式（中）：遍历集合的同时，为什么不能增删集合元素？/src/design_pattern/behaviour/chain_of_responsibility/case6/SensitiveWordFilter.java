package design_pattern.behaviour.chain_of_responsibility.case6;

import design_pattern.behaviour.chain_of_responsibility.case5.Content;

public class SensitiveWordFilter {
    // return true if content doesn't contain sensitive words.
    public boolean filter(Content content) {
        if (!filterSexyWord(content)) {
            return false;
        }

        if (!filterAdsWord(content)) {
            return false;
        }

        if (!filterPoliticalWord(content)) {
            return false;
        }

        return true;
    }

    private boolean filterSexyWord(Content content) {
        //....
        return false;
    }

    private boolean filterAdsWord(Content content) {
        //...
        return false;
    }

    private boolean filterPoliticalWord(Content content) {
        //...
        return false;
    }
}