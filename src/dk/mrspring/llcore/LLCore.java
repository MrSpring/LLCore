package dk.mrspring.llcore;

import dk.mrspring.fileexplorer.helper.ClipboardHelper;
import dk.mrspring.llcore.api.IClipboardHelper;
import dk.mrspring.llcore.api.IFileLoader;
import dk.mrspring.llcore.api.IJsonHandler;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konrad on 30-01-2015.
 */
public class LLCore
{
    String name;

    Map<String, Icon> loadedIcons;
    DrawingHelper drawingHelper;
    IClipboardHelper clipboardHelper;
    IFileLoader fileLoader;
    IJsonHandler jsonHandler;

    public LLCore(String name)
    {
        this.name = name;
        loadedIcons = new HashMap<String, Icon>();
        drawingHelper = new DrawingHelper();
        clipboardHelper = new ClipboardHelper();
        jsonHandler = new JsonHandler();
        loadIcon(new ResourceLocation("core", "arrow_up"));
        loadIcon(new ResourceLocation("core", "arrow_down"));
        loadIcon(new ResourceLocation("core", "arrow_left"));
        loadIcon(new ResourceLocation("core", "arrow_right"));
        loadIcon(new ResourceLocation("core", "check_mark"));
        loadIcon(new ResourceLocation("core", "cross"));
    }

    public Icon getIcon(String iconName)
    {
        if (!this.loadedIcons.containsKey(iconName))
            this.loadIcon(new ResourceLocation(name, iconName));
        return this.loadedIcons.get(iconName);
    }

    public void loadIcon(ResourceLocation iconLocation)
    {
        File fileFromLocation = new File("assets/" + iconLocation.getResourceDomain() + "/icons/" + iconLocation.getResourcePath() + ".icon");
        try
        {
            String iconCode = FileUtils.readFileToString(fileFromLocation);//fileLoader.getContentsFromFile(fileFromLocation);
            Icon icon = jsonHandler.loadFromJson(iconCode, Icon.class);
            if (icon == null)
                icon = new Icon(1, 1, new Quad(0, 0, 1, 1));
            loadedIcons.put(iconLocation.getResourcePath(), icon);
        } catch (Exception e)
        {
            System.err.println("Unable to load icon '" + iconLocation + "' for '" + name + "'");
            if (!loadedIcons.containsKey(iconLocation))
                loadedIcons.put(iconLocation.getResourcePath(), new Icon(1, 1, new Quad(0, 0, 1, 1)));
            e.printStackTrace();
        }
    }

    public DrawingHelper getDrawingHelper()
    {
        return drawingHelper;
    }

    public IClipboardHelper getClipboardHelper()
    {
        return clipboardHelper;
    }

    public IFileLoader getFileLoader()
    {
        return fileLoader;
    }

    public IJsonHandler getJsonHandler()
    {
        return jsonHandler;
    }

    public LLCore setDrawingHelper(DrawingHelper drawingHelper)
    {
        this.drawingHelper = drawingHelper;
        return this;
    }

    public LLCore setClipboardHelper(IClipboardHelper clipboardHelper)
    {
        this.clipboardHelper = clipboardHelper;
        return this;
    }

    public LLCore setFileLoader(IFileLoader fileLoader)
    {
        this.fileLoader = fileLoader;
        return this;
    }

    public LLCore setJsonHandler(IJsonHandler jsonHandler)
    {
        this.jsonHandler = jsonHandler;
        return this;
    }
}
