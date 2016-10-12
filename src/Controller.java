import java.io.FileWriter;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class Controller
{
	public static void main(String[] args) throws Exception {
		 String crawlStorageFolder = "/data/crawl";
		 int numberOfCrawlers = 1;
		 CrawlConfig config = new CrawlConfig();
		 config.setCrawlStorageFolder(crawlStorageFolder);
		 config.setMaxPagesToFetch(1000);
		 config.setMaxDepthOfCrawling(1000);
		 /*
		 * Instantiate the controller for this crawl.
		 */
		 PageFetcher pageFetcher = new PageFetcher(config);
		 RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		 RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		 CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
		 /*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		 
		// FileWriter fw = new FileWriter("extractions.json",true);
	//	 fw.write("[");
		// fw.close();
		 controller.addSeed("http://www.thefamouspeople.com/");
	//	 controller.addSeed("http://www.biography.com/people/gwyneth-paltrow-9432573");
	//	 controller.addSeed("http://www.biography.com/people/albert-einstein-9285408");
	//	 controller.addSeed("http://www.biography.com/people");
		 /*
		 * Start the crawl. This is a blocking operation, meaning that your code
		7
		 * will reach the line after this only when crawling is finished.
		 */
		 controller.start(MyCrawler_site_2.class, numberOfCrawlers);
	     FileWriter fw = new FileWriter("extractions.json",true);
	     fw.write("[");
	     int length= DataExtractor.objs.size();
	     for(int i=0;i<length-1;i++)
	     {
	    	 fw.write(DataExtractor.objs.get(i).toJSONString());
	    	 fw.write(",\n");
	     }
	     fw.write(DataExtractor.objs.get(length-1).toJSONString());
	     fw.write("]");
	   //  fw.write(DataExtractor.objs.toString());
	     
		 fw.close();
		 }
}

