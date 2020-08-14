package de.hexagonsoftware.au;

import java.io.File;
import java.io.IOException;

public class UpdateLoader {
    public static void load(File files, String web_adress) throws IOException {
        String temp = FileLoader.loadFile(files.getAbsolutePath());
        String[] fileList = temp.split("\\n");

        for (String s : fileList) {
            FileDownloader.download(web_adress+"/"+s, System.getProperty("user.dir"), s, true);
        }
    }
}
