import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler_site_2 extends WebCrawler {
	
	
	 private final static Pattern FILTERS = Pattern.compile(".*(/.js/)"
	 + "$");
	 
	 private final static Pattern FILTERS_2 = Pattern.compile(".*(\\.("
			 + "html|pdf|doc|docx))$");
	
	 @Override
	 public boolean shouldVisit(Page referringPage, WebURL url) 
	 {
		 String href = url.getURL().toLowerCase();
		 System.out.println(" should visit URL: " + href);
		 return ( href.startsWith("http://www.thefamouspeople.com/") && href.endsWith(".php"));
	 }
	 
	 
	 
	 /**
	  * This function is called when a page is fetched and ready
	  * to be processed by your program.
	  */
	  @Override
	  public void visit(Page page) 
	  {
		  String url = page.getWebURL().getURL();
		  System.out.println(" visited URL: " + url);
		  
		  try {
			
		
		  if (page.getParseData() instanceof HtmlParseData)
		  {
			  
			//HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
		
			DataExtractor d=new DataExtractor();
		
				d.extractData(url);
			
	
		  }
		  } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  }
}