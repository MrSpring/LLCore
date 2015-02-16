package dk.mrspring.llcore.api;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Konrad on 12-02-2015.
 */
public interface IFileLoader
{
    public File[] getFilesInFolder(File folder, boolean addSubFolder, FileFilter filter);

    public void addFilesToList(File folder, List<File> fileList, boolean addSubFolder, FileFilter filter);

    public String getContentsFromFile(File file) throws IOException;

    public boolean writeToFile(File file, String toWrite) throws IOException;

    public boolean deleteFile(File file) throws IOException;
}
