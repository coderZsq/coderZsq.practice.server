package design_pattern.behaviour.chain_of_responsibility.case5;

public class PoliticalWordFilter implements SensitiveWordFilter {
    @Override
    public boolean doFilter(Content content) {
        return false;
    }
}
