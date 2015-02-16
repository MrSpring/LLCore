package dk.mrspring.llcore;

import dk.mrspring.llcore.api.IClipboardHelper;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by Konrad on 10-02-2015.
 */
public class ClipboardHelper implements IClipboardHelper
{
    @Override
    public String paste()
    {
        try
        {
            Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
            return (String) board.getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e)
        {
            System.err.println("Failed to paste from clipboard:");
            e.printStackTrace();
        } catch (IOException e)
        {
            System.err.println("Failed to paste from clipboard:");
            e.printStackTrace();
        } catch (ClassCastException e)
        {
            System.err.println("Failed to paste from clipboard:");
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public void copy(String string)
    {
        try
        {
            StringSelection selection = new StringSelection(string);
            Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
            board.setContents(selection, selection);
        } catch (Exception e)
        {
            System.err.println("Failed to copy string: " + string);
            e.printStackTrace();
        }
    }
}
