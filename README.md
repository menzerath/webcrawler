# WebCrawler
This is a simple, recursive Java Web-Crawler for internal and external links and images on a specific website, which creates a simple XML-file including the found pages and the returned status-code.
While it attempts to crawl through any website and find new links, it won't crawl a site multiple times or try to crawl a downloadable file.

**Important:** Crawling may take some time and use many server-resources. Be careful!

## Download
[![Build Status](https://travis-ci.org/MarvinMenzerath/WebCrawler.svg?branch=master)](https://travis-ci.org/MarvinMenzerath/WebCrawler)
* [**GitHub Releases**](https://github.com/MarvinMenzerath/WebCrawler/releases)

## Run
```
java -jar WebCrawler.jar http://my-website.com
```

### Example Output

#### Console
```
INTERNAL LINKS:
[1] [200] https://menzerath.eu
[2] [200] https://menzerath.eu/rss/
[3] [200] https://menzerath.eu/tag/android/
[4] [200] https://menzerath.eu/tag/java/
[5] [XXX] ...

EXTERNAL LINKS:
[1] [200] https://facebook.com/menzerath.eu
[2] [200] https://twitter.com/MarvinMenzerath
[3] [200] https://github.com/MarvinMenzerath
[4] [200] http://blackphantom.de
[5] [XXX] ...

INTERNAL / EXTERNAL IMAGES:
[1] [200] https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot1.png
[2] [200] https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot2.png
[3] [200] https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot3.png
[4] [200] https://menzerath.eu/content/images/2014/10/Screen.png
[5] [XXX] ...

```

#### XML-File
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<urlset>
    <internal>
        <link>
            <url>https://menzerath.eu</url>
            <code>200</code>
        </link>
        <link>
            <url>https://menzerath.eu/rss/</url>
            <code>200</code>
        </link>
        <link>
            <url>https://menzerath.eu/tag/android/</url>
            <code>200</code>
        </link>
        <link>
            <url>https://menzerath.eu/tag/java/</url>
            <code>200</code>
        </link>
        <link>
            <url>...</url>
            <code>XXX</code>
        </link>
    </internal>
    <external>
        <link>
            <url>https://facebook.com/menzerath.eu</url>
            <code>200</code>
        </link>
        <link>
            <url>https://twitter.com/MarvinMenzerath</url>
            <code>200</code>
        </link>
        <link>
            <url>https://github.com/MarvinMenzerath</url>
            <code>200</code>
        </link>
        <link>
            <url>http://blackphantom.de</url>
            <code>200</code>
        </link>
        <link>
            <url>...</url>
            <code>XXX</code>
        </link>
    </external>
    <images>
        <link>
            <url>https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot1.png</url>
            <code>200</code>
        </link>
        <link>
            <url>https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot2.png</url>
            <code>200</code>
        </link>
        <link>
            <url>https://raw.githubusercontent.com/MarvinMenzerath/IsMyWebsiteDown/master/doc/Screenshot3.png</url>
            <code>200</code>
        </link>
        <link>
            <url>https://menzerath.eu/content/images/2014/10/Screen.png</url>
            <code>200</code>
        </link>
        <link>
            <url>...</url>
            <code>XXX</code>
        </link>
    </images>
</urlset>
```

## License
Copyright (C) 2014 [Marvin Menzerath](http://menzerath.eu)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the [GNU General Public License](https://github.com/MarvinMenzerath/WebCrawler/blob/master/LICENSE) for more details.
