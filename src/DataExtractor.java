import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


public class DataExtractor {
	public static List<String> names=new ArrayList<>();
	public static List<JSONObject> objs=new ArrayList<>();
	static int file_no=0;
	public void extractData(String url) throws IOException {
//	public static void main(String[] args)throws IOException {
		
		//FileWriter fw = new FileWriter("extractions.json",true);
	
		JSONObject obj = new JSONObject();
		
		Document doc = Jsoup.connect(url).get();
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("rawFiles/file_"+file_no+".txt")));
		bw.append(doc.toString());
		bw.close();
		file_no++;
		//Document doc = Jsoup.parse(new URL("http://www.thefamouspeople.com/profiles/mata-amritanandamayi-80.php"),100 );
		Element div1 = doc.getElementsByClass("breadcrumbs").get(0);
	
		Element name=div1.getElementsByTag("h1").get(0);
		String PersonName=name.text();
		if(names.contains(PersonName))
			return;
		else
			names.add(PersonName);
		obj.put("name",PersonName);
	//	System.out.println(doc);
		//doc.
		Element table = doc.getElementsByClass("fps-desc").get(0);
		Elements quicklefts = table.getElementsByClass("quick_left");
		Elements quickrights = table.getElementsByClass("quick_right");
	//	System.out.println("0::"+table);
		
		int i=0;
		obj.put("URL",url);
		
		for(Element element:quicklefts)
		{	String id=element.text().trim();
			if(!id.equals(""))
			{
				
				if(id.endsWith(":"))
				{
					id=id.substring(0,id.length()-1);
				}
					
				String answer=quickrights.get(i).text();
				if(id.equals("Birthday"))
				{
					continue;
				}
				if(id.equals("Sun Sign"))
				{
					String[] finalAnswers=answer.split(" ");
					answer=finalAnswers[0];
				}
				if(id.equals("Nationality"))
				{
					String[] finalAnswers=answer.split(",");
					//if(finalAnswers.length>1)
					{
						answer="";
						for(String temp1:finalAnswers)
						{
							String[] temp=temp1.split(" ");
							answer+=temp[0];
						}
						
					}
				}
				obj.put(id,answer);
			//    System.out.println(id+":"+answer);
			}
			i++;
		}
		
		
		objs.add(obj);
	//	System.out.println("Object::"+ obj);
	//	fw.write(obj.toJSONString());
	//	fw.write(",\n");
	//	fw.close();
	}
}
