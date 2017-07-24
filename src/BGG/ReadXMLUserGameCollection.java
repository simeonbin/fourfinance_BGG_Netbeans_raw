package BGG;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simeon
 */
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.StringReader;
import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.xml.sax.InputSource;

class ReadXMLUserGameCollection {    
    static int numOfGames = 0;
    static ArrayList<Integer> gameObjectIds = new ArrayList<>(1600);
    static int chosenGameNum;
    static int chosenGameObjectId;
    
    public void ReadXMLUserGameCollectionImpl () {       
        
        try {

//	File fXmlFile = new File("/Users/mkyong/staff.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        InputSource isXml = new InputSource(new StringReader("items.xml"));
	Document doc = dBuilder.parse("usergamecollection.xml");

	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("items");
        System.out.println("----------------------------");
        NodeList list = doc.getElementsByTagName("item");
        numOfGames = list.getLength();
        System.out.println("Total of elements : " + numOfGames);
    //    System.out.println(" Length of Items Attributes: " + list.item(1000).getAttributes().getLength());
	System.out.println("----------------------------");
        
        // Choose a random number between 0 and (numOfGames-1)
        chosenGameNum = ThreadLocalRandom.current().nextInt(0, numOfGames);
//        chosenGameObjectId = gameObjectIds.get(chosenGameNum); //DO THIS AT THE END, WHEN IDs ARE FILLED UP
        
        System.out.println("Chosen Game Num=" + chosenGameNum);
        
        
        Element docEle = doc.getDocumentElement();
        NodeList nl = docEle.getChildNodes();
        
    //    System.out.println("NL elements : " + nl.getLength());
    //    System.out.println("----------------------------");
    
        if (nl != null) {

            int length = nl.getLength();
      //      System.out.println(length); 

            for (int i = 0; i < list.getLength(); i++) {                           

        //    System.out.println(" Length of Attributes: " + list.item(i).getAttributes().getLength());
            Node node = list.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        
                     System.out.println("\nCurrent Game number : " + i);

                    Element el = (Element) node;

                        if (el.getNodeName().contains("item")) {
                            String gameObjectId = el.getAttribute("objectid");
                            Integer intGameObjectId = Integer.parseInt(gameObjectId);
                            gameObjectIds.add(intGameObjectId);
                            System.out.println("Game Object Id : " + gameObjectId);
                            
                            String name = el.getElementsByTagName("name").item(0).getTextContent();
                            
                            if ((el.getElementsByTagName("yearpublished").getLength() != 0)) {                                
                                String yearpublished = el.getElementsByTagName("yearpublished").item(0).getTextContent();
                                System.out.println("Publication Year : " + yearpublished);
                            }                
                            
                            if ((el.getElementsByTagName("minplayers").getLength() != 0)) {                                
                                String stats_minplayers = el.getElementsByTagName("minplayers").item(0).getTextContent();
                                System.out.println("stats_minplayers : " + stats_minplayers);
                            }                      
                            
                            if ((el.getElementsByTagName("maxplayers").getLength() != 0)) {                                
                                String stats_maxplayers = el.getElementsByTagName("minplayers").item(0).getTextContent();
                                System.out.println("stats_maxplayers : " + stats_maxplayers);
                            }                   

                             if ((el.getElementsByTagName("maxplaytime").getLength() != 0)) {                                
                                String stats_maxplaytime = el.getElementsByTagName("maxplaytime").item(0).getTextContent();
                                System.out.println("stats_maxplaytime : " + stats_maxplaytime);
                            }                   
                             if ((el.getElementsByTagName("playingtime").getLength() != 0)) {                                
                                String stats_playingtime = el.getElementsByTagName("playingtime").item(0).getTextContent();
                                System.out.println("stats_playingtime : " + stats_playingtime);
                            }                  
                             
                              if ((el.getElementsByTagName("numowned").getLength() != 0)) {                                
                                String stats_numowned = el.getElementsByTagName("numowned").item(0).getTextContent();
                                System.out.println("stats_numowned : " + stats_numowned);
                            }                          
                                       
                            Element elStats = (Element) el.getElementsByTagName("stats").item(0);
                            String rating = elStats.getElementsByTagName("rating").item(0).getAttributes().getNamedItem("value").getTextContent();                                  
                            String average = elStats.getElementsByTagName("average").item(0).getAttributes().getNamedItem("value").getTextContent();
                            String stddev = elStats.getElementsByTagName("stddev").item(0).getAttributes().getNamedItem("value").getTextContent();
          
                            System.out.println("Name : " + name);       
                            
                            System.out.println("Rating : " + rating);
                            System.out.println("Average : " + average);
                            System.out.println("Stddev : " + stddev);
                            System.out.println();
                        }
                    }
            }
        }
    }   catch (Exception e) {
            e.printStackTrace();
    }    
    // Choose the random Game Object Id based on the random number 0-NumOfGames
    chosenGameObjectId = gameObjectIds.get(chosenGameNum);
    System.out.println("Chosen Game Num=" + chosenGameNum);
    System.out.println("Chosen Game Object Id=" + chosenGameObjectId);
    
    System.out.println();    
}
    
  public static void main(String argv[])  {
      
      ReadXMLUserGameCollection xmlUGC = new ReadXMLUserGameCollection();
      xmlUGC.ReadXMLUserGameCollectionImpl();
  }

}

