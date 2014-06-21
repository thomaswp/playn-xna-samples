PlayN XNA Sample Projects
=====================

Introduction
------------

This project is a fork of the [PlayN Samples](https://code.google.com/p/playn-samples/) with example code for setting up an XNA target using [PlayN-XNA](https://github.com/thomaswp/playn-xna). 

Dependencies
------------

PlayN-XNA currently only supports Windows, though this should change in the future using [monogame](http://www.monogame.net/).

To run the samples, you must have the following dependencies installed:
* [Visual Studio Express 2010](http://www.visualstudio.com/en-us/downloads#d-2010-express)
* [The XNA 4.0 Framework](http://www.microsoft.com/en-us/download/details.aspx?id=23714) (If using Windows 8, you will first need to download the [Windows Marketplace Game Client](http://www.xbox.com/en-US/LIVE/PC/DownloadClient).)
* [Apache Maven](http://maven.apache.org/) should be setup and accessible via the command line.
* [IKVM](http://www.ikvm.net/) allows compiling Java source to a CLR binary and should be setup on the developmen.t computer (note that if you downloaded the ikvm-maven-plugin for an iOS build, this is a different program).
* [PlayN 1.8.5 dlls](https://github.com/thomaswp/playn-xna/raw/master/compiled/PlayN-1.8.5-dlls.zip), unzipped somewhere on your computer. It contains a compiled copy of the PlayN framework against which your code will be linked, as well as other necessary files.

Setup
-----

First ensure you have the dependencies listed above. In order to build for XNA, you must first setup two maven plugins and their configuration. Find your [settings.xml](http://maven.apache.org/settings.html), and add the following:

    <settings>
      ...
      <profiles>
        ...
        <!-- Create a profile for XNA builds -->
        <profile>
          <id>xna</id>
          <properties>
            <!-- repository for the ikvm plugin -->
            <ikvm.plugin>https://raw.githubusercontent.com/thomaswp/ikvm-maven-plugin/mvn-repo/</ikvm.plugin>
            <!-- repository for the xna plugin -->
            <xna.plugin>https://raw.githubusercontent.com/thomaswp/playn-xna-plugin/mvn-repo/</xna.plugin>
            <!-- replace the following with the path to you IKVM installation -->
            <ikvm.path>C:\Program Files\ikvm</ikvm.path>
            <!-- replace the following with the path to your core .NET or mono assemblies (e.g. System.Net.dll) -->
            <!-- see http://msdn.microsoft.com/en-us/library/vstudio/ff462634%28v=vs.100%29.aspx for more details -->
            <dll.path>C:\Program Files (x86)\Reference Assemblies\Microsoft\Framework\.NETFramework\v4.0\Profile\Client\</dll.path>
            <!-- replace the following with the path to the PlayN-1.8.5.dlls you downloaded -->
            <playn.path>...</playn.path>
          </properties>
        </profile>
      </profiles>
      ...
      <activeProfiles>
      	<!-- make sure the profile is active -->
        <activeProfile>xna</activeProfile>
      </activeProfiles>
    </settings>

This should be sufficient to build the samples for XNA, but if you want to setup your own project, see the documentation for [PlayN-XNA](https://github.com/thomaswp/playn-xna/)

Samples
-------

The following sample projects are configured for XNA and should run with full features:
* [cute](/cute)
* [hello](/hello)
* [showcase](/showcase)

Running the Samples
-------------------

If you have installed and configured the dependencies (make sure you can run a normal XNA 4.0 Windows game), use the following command from inside any of the supported sample projects to generate an XNA build:

    mvn -Pxna package
	
Then go to the xna folder, where you will find a .sln file. Open this using Visual Studio 2010, and the game should run without further configuration. If the game contains a large number of assets, the first run may take a few minutes (the showcase project may do this).

Questions
---------

For more information, check out [PlayN-XNA](https://github.com/thomaswp/playn-xna) and the [PlayN-XNA maven plugin](https://github.com/thomaswp/playn-xna-plugin).

Licensing
---------

Unless otherwise stated, all source files are licensed under the Apache
License, Version 2.0:

    Copyright 2011 The PlayN Authors

    Licensed under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy
    of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations
    under the License.
