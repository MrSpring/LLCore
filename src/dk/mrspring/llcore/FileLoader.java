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
    public static FileFilter DEFAULT_FILTER=new FileFilter()
    {
        @Override
        public boolean accept(File pathname)
        {
            return true;
        }
    };

    @Override
    public File[] getFilesInFolder(File folder, boolean addSubFolders, FileFilter filter)
    {
        List<File> files = new ArrayList<File>();
        this.addFilesToList(folder, files, addSubFolders, filter);
        return files.toArray(new File[files.size()]);
    }

    @Override
    public void addFilesToList(File folder, List<File> fileList, boolean addSubFolders, FileFilter filter)
    {
        File[] subFiles = folder.listFiles();
        if (subFiles == null)
            return;
        for (File file : subFiles)
        {
            if (filter.accept(file))
                fileList.add(file);
            if (file.isDirectory() && addSubFolders)
                this.addFilesToList(file, fileList, true, filter);
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

    @Override
    public boolean copyFile(File original, File copy) throws IOException
    {
        if (original.isDirectory())
            FileUtils.copyDirectory(original, copy);
        else FileUtils.copyFile(original, copy);
        return true;
    }

    @Override
    public boolean moveFile(File original, File newFile) throws IOException
    {
        this.copyFile(original, newFile);
        this.deleteFile(original);
        return true;
    }
}
