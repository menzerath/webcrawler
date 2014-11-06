# WebCrawler
This is a simple, recursive Java Web-Crawler for internal and external links and images on a specific website, which creates a simple XML-file including the found pages.
While it attempts to crawl through any website and find new links, it won't crawl a site multiple times or try to crawl a downloadable file.

**Important:** Crawling may take some time and use many server-resources. Be careful!

## Download
* [**GitHub Releases**](https://github.com/MarvinMenzerath/WebCrawler/releases)

## Run
```
java -jar WebCrawler.jar
```

Now enter the URL you want to crawl (including `http://` or `https://`), press [Enter] and wait until you see the results.  
This may take a while - depending on your server and internet-speed.

### Example Output

#### Console
```
INTERNAL LINKS:
[1] https://menzerath.eu
[2] https://menzerath.eu/rss/
[3] https://menzerath.eu/tag/android/
[4] https://menzerath.eu/tag/java/
[5] https://menzerath.eu/tag/linux/
[6] https://menzerath.eu/tag/news/
[7] https://menzerath.eu/tag/raspberry-pi/
[8] https://menzerath.eu/tag/review/
[9] https://menzerath.eu/tag/software/
[10] ...

EXTERNAL LINKS:
[1] https://facebook.com/menzerath.eu
[2] https://twitter.com/MarvinMenzerath
[3] https://github.com/MarvinMenzerath
[4] http://blackphantom.de
[5] http://ratgeber---forum.de
[6] http://thiefas.de
[7] http://patrick246.de
[8] https://ghost.org
[9] http://www.e-recht24.de/
[10] ...

INTERNAL / EXTERNAL IMAGES:
[1] https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot1.png
[2] https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot2.png
[3] https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot3.png
[4] https://menzerath.eu/content/images/2014/10/Screen.png
[5] https://menzerath.eu/content/images/2014/10/WordpressBlog-Installation.png
[6] https://menzerath.eu/content/images/2014/10/WordpressBlog-Dashboard.png
[7] https://menzerath.eu/content/images/2014/10/WordpressBlog-Theme.png
[8] https://menzerath.eu/content/images/2014/10/GitHub-Pages.png
[9] https://menzerath.eu/content/images/2014/10/OwnCloud-Dateiablage.png
[10] ...

```

#### XML-File
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<urlset>
    <internal>
        <url>https://menzerath.eu</url>
        <url>https://menzerath.eu/rss/</url>
        <url>https://menzerath.eu/tag/android/</url>
        <url>https://menzerath.eu/tag/java/</url>
        <url>https://menzerath.eu/tag/linux/</url>
        <url>https://menzerath.eu/tag/news/</url>
        <url>https://menzerath.eu/tag/raspberry-pi/</url>
        <url>https://menzerath.eu/tag/review/</url>
        <url>https://menzerath.eu/tag/software/</url>
        <url>...</url>
    </internal>
    <external>
        <url>https://facebook.com/menzerath.eu</url>
        <url>https://twitter.com/MarvinMenzerath</url>
        <url>https://github.com/MarvinMenzerath</url>
        <url>http://blackphantom.de</url>
        <url>http://ratgeber---forum.de</url>
        <url>http://thiefas.de</url>
        <url>http://patrick246.de</url>
        <url>https://ghost.org</url>
        <url>http://www.e-recht24.de/</url>
        <url>...</url>
    </external>
    <images>
        <url>https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot1.png</url>
        <url>https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot2.png</url>
        <url>https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot3.png</url>
        <url>https://menzerath.eu/content/images/2014/10/Screen.png</url>
        <url>https://menzerath.eu/content/images/2014/10/WordpressBlog-Installation.png</url>
        <url>https://menzerath.eu/content/images/2014/10/WordpressBlog-Dashboard.png</url>
        <url>https://menzerath.eu/content/images/2014/10/WordpressBlog-Theme.png</url>
        <url>https://menzerath.eu/content/images/2014/10/GitHub-Pages.png</url>
        <url>https://menzerath.eu/content/images/2014/10/OwnCloud-Dateiablage.png</url>
        <url>...</url>
    </images>
</urlset>
```

## License
Copyright (C) 2014 [Marvin Menzerath](http://menzerath.eu)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the [GNU General Public License](https://github.com/MarvinMenzerath/WebCrawler/blob/master/LICENSE) for more details.
