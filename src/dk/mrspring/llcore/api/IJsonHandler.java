package dk.mrspring.llcore.api;

/**
 * Created by Konrad on 12-02-2015.
 */
public interface IJsonHandler
{
    public <T> T loadFromJson(String jsonCode, Class<? extends T> typeClass);

    public String toJson(Object object);
}
