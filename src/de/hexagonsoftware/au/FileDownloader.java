package de.hexagonsoftware.au;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * This class is used to download files via HTTP
 *
 * @author Felix Eckert
 * */
public class FileDownloader {
    private boolean printLog, replace;

    public FileDownloader(boolean printLog, boolean replace) {
        this.printLog = printLog;
        this.replace  = replace;
    }

    /**
     * This function downloads and saves a file via HTTP.
     *
     * @param site The link to the file to be downloaded.
     * @param outputdir The directory in which the file should be saved
     * @param outputname The name of the file which the download should be saved as.
     * @throws IOException
     *
     * @author Felix Eckert
     * */
    public static void download(String site, String outputdir, String outputname, boolean replace, boolean printLog) throws IOException {
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL.ACCEPT_ALL));
            // Create URL object

            URL url = new URL(site);
            BufferedReader readr =
                    new BufferedReader(new InputStreamReader(url.openStream()));

            // Enter filename in which you want to download
            File out = new File(outputdir+"/"+outputname);
            if (out.exists() && replace)
                out.delete();

            if (!out.exists())
                out.createNewFile();

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(out.getAbsoluteFile()));

            // read each line from stream till end
            String line;
            while ((line = readr.readLine()) != null) {
                writer.write(line+"\n");
            }

            readr.close();
            writer.close();
            if (printLog)
                Main.log("Succesfully downloaded "+site);
        }

        // Exceptions
        catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
    }

    /**
     * This function downloads and saves a file via HTTP, but uses
     * predetermined flags for the process.
     *
     * @param site The link to the file to be downloaded.
     * @param outputdir The directory in which the file should be saved
     * @param outputname The name of the file which the download should be saved as.
     * @throws IOException
     *
     * @author Felix Eckert
     * */
    public void download(String site, String outputdir, String outputname) throws IOException {
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL.ACCEPT_ALL));
            // Create URL object

            URL url = new URL(site);
            BufferedReader readr =
                    new BufferedReader(new InputStreamReader(url.openStream()));

            // Enter filename in which you want to download
            File out = new File(outputdir+"/"+outputname);
            if (out.exists() && this.replace)
                out.delete();

            if (!out.exists())
                out.createNewFile();

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(out.getAbsoluteFile()));

            // read each line from stream till end
            String line;
            while ((line = readr.readLine()) != null) {
                writer.write(line+"\n");
            }

            readr.close();
            writer.close();
            if (this.printLog)
                Main.log("Succesfully downloaded "+site);
        }

        // Exceptions
        catch (MalformedURLException mue) {
            mue.printStackTrace();
        }
    }
}
