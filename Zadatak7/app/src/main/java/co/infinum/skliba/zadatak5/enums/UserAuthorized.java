package co.infinum.skliba.zadatak5.enums;

/**
 * Created by noxqs on 20.07.15..
 */
public enum UserAuthorized {

    AUTHORIZED(1), UNAUTHORIZED(0);

    private int key;

    UserAuthorized(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public static UserAuthorized fromKey(int key) {
        for (UserAuthorized userAuthorized : values()) {
            if (key == userAuthorized.key) {
                return userAuthorized;
            }
        }
        return UNAUTHORIZED;
    }
}
