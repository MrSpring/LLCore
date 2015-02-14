package dk.mrspring.llcore;

import dk.mrspring.llcore.api.IFileLoader;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 30-01-2015.
 */
public class FileLoader implements IFileLoader
{
    @Override
    public File[] getFilesInFolder(File folder, boolean addSubFolders)
    {
        List<File> files = new ArrayList<File>();
        this.addFilesToList(folder, files, addSubFolders);
        return files.toArray(new File[files.size()]);
    }

    @Override
    public void addFilesToList(File folder, List<File> fileList, boolean addSubFolders)
    {
        File[] subFiles = folder.listFiles();
        if (subFiles == null)
            return;
        for (File file : subFiles)
        {
            fileList.add(file);
            if (file.isDirectory() && addSubFolders)
                this.addFilesToList(file, fileList, true);
        }
    }

    @Override
    public String getContentsFromFile(File file) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null)
            builder.append(line).append('\n');
        bufferedReader.close();
        return builder.toString();
    }

    @Override
    public boolean writeToFile(File file, String toWrite) throws IOException
    {
        FileWriter writer = new FileWriter(file);
        writer.write(toWrite);
        writer.close();
        return true;
    }

    @Override
    public boolean deleteFile(File file) throws IOException
    {
        if (file.isDirectory())
            FileUtils.deleteDirectory(file);
        else return file.delete();
        return true;
    }
}
