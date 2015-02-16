package dk.mrspring.llcore.litemod;

import com.mumfrey.liteloader.LiteMod;

import java.io.File;

/**
 * Created by MrSpring on 16-02-2015 for MC Music Player.
 */
public class LiteModLLCore implements LiteMod
{
    @Override
    public String getVersion()
    {
        return "1.0.0";
    }

    @Override
    public void init(File configPath)
    {

    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath)
    {

    }

    @Override
    public String getName()
    {
        return "Spring LLCore";
    }
}
