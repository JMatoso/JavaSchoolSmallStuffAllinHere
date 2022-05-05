package com.matoso.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

public class FileService
{
    private File _file;
    private Scanner _reader;
    private FileWriter _writer;

    public FileService(String path)
    {
        if(path.isEmpty() || path.isBlank()) throw new IllegalArgumentException();

        _file = new File(path);

        try
        {
            if(exists())
            {
                _reader = new Scanner(_file);
                _writer = new FileWriter(path);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(format("Algo correu mal: %s", e.getMessage()));
        }
    }

    public boolean exists(String path)
    {
        _file = new File(path);
        return _file.exists();
    }

    public boolean exists()
    {
        return _file.exists();
    }

    public <T> boolean getData()
    {
        if(exists())
        {
            String data = new String();
            while (_reader.hasNextLine())
            {
                data = _reader.next();
            }

            return true;
        }

        return false;
    }

    public <T> boolean saveData(Object data)
    {
        try
        {
            String json = new com.google.gson.Gson().toJson(data);

            _writer.write(json);
            _writer.close();

            if(exists()) _file.delete();

            if (_file.createNewFile())
            {
                System.out.println("Dados salvos.");
                return true;
            }

            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println(format("Algo correu mal: %s", e.getMessage()));

            return false;
        }
    }
}
