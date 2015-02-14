package dk.mrspring.llcore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.mrspring.llcore.api.IJsonHandler;

/**
 * Created by Konrad on 12-02-2015.
 */
public class JsonHandler implements IJsonHandler
{
    boolean prettyPrinting = true;

    @Override
    public <T> T loadFromJson(String json, Class<? extends T> typeClass)
    {
        return new Gson().fromJson(json, typeClass);
    }

    @Override
    public String toJson(Object object)
    {
        GsonBuilder builder = new GsonBuilder();
        if (prettyPrinting)
            builder.setPrettyPrinting();
        return builder.create().toJson(object);
    }
}
