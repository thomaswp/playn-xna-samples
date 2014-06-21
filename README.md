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

You must also configure the [PlayN-XNA maven plugin](https://github.com/thomaswp/playn-xna-plugin) or the project will not run.

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
