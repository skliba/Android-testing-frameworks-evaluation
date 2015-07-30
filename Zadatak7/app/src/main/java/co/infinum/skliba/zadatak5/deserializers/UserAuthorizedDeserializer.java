package co.infinum.skliba.zadatak5.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import co.infinum.skliba.zadatak5.enums.UserAuthorized;

/**
 * Created by noxqs on 20.07.15..
 */
public class UserAuthorizedDeserializer implements JsonDeserializer<UserAuthorized> , JsonSerializer<UserAuthorized>{

    @Override
    public UserAuthorized deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return UserAuthorized.fromKey(json.getAsInt());
    }

    @Override
    public JsonElement serialize(UserAuthorized src, Type typeOfSrc, JsonSerializationContext context) {
        return null;
    }
}
