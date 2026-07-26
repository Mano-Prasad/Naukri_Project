package com.utility;

import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileReaderManager {

private static File file;
private static FileInputStream fileInputStream;
private static Properties properties;

    public FileReaderManager(){

        try{
        file = new File("C:\\Users\\Mano\\IdeaProjects\\Naukri_Project\\src\\main\\resources\\TestData.properties");
        fileInputStream = new FileInputStream(file);
        properties = new Properties();
        properties.load(fileInputStream);
    }
        catch(FileNotFoundException e){
            Assert.fail("ERROR : OCCURRED DURING FILE CREATION "+ e.getMessage());
        }
        catch(IOException e){
            Assert.fail("ERROR : OCCURRED DURING PROPERTY FILE LOAD " + e.getMessage());
        }
        catch(Exception e){
            Assert.fail("ERROR : OCCURRED DURING SETUP PROPERTY " + e.getMessage());
        }
    }

    public String getConfigProperty(String keyName){
       return properties.getProperty(keyName);
    }
}
