# WebCrawler
This is a simple, recursive Java Web-Crawler for internal and external links on a specific website, which creates a simple XML-file including the found pages.  
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
[1] http://menzerath.eu
[2] http://menzerath.eu/kategorie/android/
[3] http://menzerath.eu/kategorie/java/
[4] http://menzerath.eu/kategorie/linux/
[5] http://menzerath.eu/kategorie/news/
[6] http://menzerath.eu/kategorie/raspberry-pi/
[7] http://menzerath.eu/kategorie/review/
[8] http://menzerath.eu/kategorie/software/
[9] http://menzerath.eu/kategorie/vermischt/
[10] ...
...

EXTERNAL LINKS:
[1] http://mcgen.menzerath.eu/
[2] http://play.google.com/store/apps/details?id=de.menzerath.ggblocker
[3] https://facebook.com/menzerath.eu
[4] https://twitter.com/MarvinMenzerath
[5] https://plus.google.com/+MenzerathEu
[6] https://github.com/MarvinMenzerath
[7] https://github.com/new
[8] https://windows.github.com/
[9] https://mac.github.com/
[10] ...
```

#### XML-File
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<urlset>
    <internal>
        <url>http://menzerath.eu</url>
        <url>http://menzerath.eu/kategorie/android/</url>
        <url>http://menzerath.eu/kategorie/java/</url>
        <url>http://menzerath.eu/kategorie/linux/</url>
        <url>http://menzerath.eu/kategorie/news/</url>
        <url>http://menzerath.eu/kategorie/raspberry-pi/</url>
        <url>http://menzerath.eu/kategorie/review/</url>
        <url>http://menzerath.eu/kategorie/software/</url>
        <url>http://menzerath.eu/kategorie/vermischt/</url>
        <url>...</url>
    </internal>
    <external>
        <url>http://mcgen.menzerath.eu/</url>
        <url>http://play.google.com/store/apps/details?id=de.menzerath.ggblocker</url>
        <url>https://facebook.com/menzerath.eu</url>
        <url>https://twitter.com/MarvinMenzerath</url>
        <url>https://plus.google.com/+MenzerathEu</url>
        <url>https://github.com/MarvinMenzerath</url>
        <url>https://github.com/new</url>
        <url>https://windows.github.com/</url>
        <url>https://mac.github.com/</url>
        <url>...</url>
    </external>
</urlset>
```

## License
Copyright (C) 2014 [Marvin Menzerath](http://menzerath.eu)

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the [GNU General Public License](https://github.com/MarvinMenzerath/WebCrawler/blob/master/LICENSE) for more details.
