package object_oriented.ood;

public interface ApiAuthenticator {
    void auth(String url);
    void auth(ApiRequest apiRequest);
}
