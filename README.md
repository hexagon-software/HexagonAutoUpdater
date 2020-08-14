# HexagonAutoUpdater
A simple Update Program written in java.

## Introduction
This is an Auto-Updating Program, which is easy modifiable,
it works by downloading a version.txt from a given
webserver, which contains a version number. It then compares this
to the specified version file.
If there is a newer version it will download the lastest version,
from the specified server. This works via http, if Authentication
is required, this most be specified, alongside the credentials.

For Larger Updates or Updates cotaining subfolders, it is recommended
to provide a singular archive file of the update, which then later be
opened by a seperate program.

## Howto use
First of all you need to setup a basic Webserver/Website which contains the following files: <br />
version.txt - The update servers current version of the program <br />
files.txt - This file contains of all Files needed to download, for folders, create a zip file. <br />
update files - All the files of the update. <br />
Then, in the root directory of your program, put the Updater Jar and (optional) a batch file to execute, and
a version.txt of the current version on the computer, this will be renewed with every update. <br />
Finally you just have to execute the updater on every launch or add an autostart for the updater jar or start
script.

## License
The Hexagon Auto Upadter is licensed under the 3-Clause BSD license.
Copyright (c) 2020 Hexagon Software
