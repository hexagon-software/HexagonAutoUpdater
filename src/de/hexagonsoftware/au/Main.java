package de.hexagonsoftware.au;

import java.awt.*;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.text.DecimalFormat;

/**
 * This is an Auto-Updating Program, which is easy modifiable,
 * it works by downloading a current-version.txt from a given
 * website, which contains a version number. It then compares this
 * to the specified version file (path relative to updater location).
 * If there is a newer version it will download the lastest version,
 * from the specified server. This works via http, if Authentication
 * is required, this most be specified, alongside the credentials.
 * Visit hexagonsoftware.github.io/AutoUpdater for further information
 *
 * @author Felix Eckert
 * */
public class Main {
    public static final String LOCAL_VERSION = "version.txt"; // Name of the Local Version File goes here
    public static final String ONLINE_VERSION_FOLDER = "http://127.0.0.1/au"; // The Adress to the Webserver with the path to the folder containing the version
    public static final boolean AUTH_REQUIRED = false; // Set to true if Password Auth is required
    public static final String AUTH_PWD = ""; // Password for authentication
    public static final String AUTH_USR = ""; // Username for authentication

    public static void main(String args[]) throws IOException {
        System.out.println("Hexagon-AutoUpdater is starting...\n");

        // Load the Local Version
        log("Loading local version file...");
        String localVersion = FileLoader.loadFile(System.getProperty("user.dir")+"/"+LOCAL_VERSION); // Use the File Loader to load the local version file
        log("Found local version as: "+localVersion);

        // Load the newest version which is online
        log("Grabbing online version...");
        if (AUTH_REQUIRED) {
            log("Authenticating...");
            Authenticator.setDefault(new de.hexagonsoftware.au.Authenticator(AUTH_USR, AUTH_PWD));
        }
        FileDownloader.download(ONLINE_VERSION_FOLDER+"/version.txt", System.getProperty("user.dir"), "olVersion.txt", true, true);
        String onlineVersion = FileLoader.loadFile(System.getProperty("user.dir")+"/olVersion.txt");
        log("Found online version as: "+onlineVersion);

        // Convert version string
        Float localVersionFL = Float.parseFloat(localVersion);
        Float onlineVersionFL = Float.parseFloat(onlineVersion);

        // Check if newer Version available
        if (onlineVersionFL > localVersionFL) {
            log("New Version Available, downloading!");

            // Download the files.txt which contains a list of files which the update reuires.
            log("Grabbing file list...");
            FileDownloader.download(ONLINE_VERSION_FOLDER+"/files.txt", System.getProperty("user.dir"), "files.txt", true, true);

            // Start downloading these files
            log("Starting download...\n");
            UpdateLoader.load(new File(System.getProperty("user.dir")+"/files.txt"), ONLINE_VERSION_FOLDER);
        } else {
            log("No New Version Available, stopping!");
            System.exit(0);
        }
    }

    public static void log(String s) {
        System.out.println("> "+s);
    }
}
