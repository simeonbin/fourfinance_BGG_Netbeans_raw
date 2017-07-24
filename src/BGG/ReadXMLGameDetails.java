package BGG;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.xml.sax.InputSource;



class ReadXMLGameDetails {
    static ArrayList<String> strMostVotedLD = new ArrayList<>(5);
    static String currentMostVotedLD = "No necessary in-game text";
    static String nameOfGame="10 Days in Africa"; 
    static String gameObjectId="7865";
    
    public static Map.Entry<Integer, Integer> findKeyWithMaxValue( HashMap<Integer, Integer> map) {
    Map.Entry<Integer, Integer> maxEntry = null;

        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return maxEntry;
     }
    
    public void ReadXMLGameDetailsImpl () {
        
    HashMap<Integer, Integer> mostVotedLanguageDependence = new HashMap<>();

    try {

//	File fXmlFile = new File("/Users/mkyong/staff.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        InputSource isXml = new InputSource(new StringReader("items.xml"));
	Document doc = dBuilder.parse("gamedetails.xml");

	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("boardgames");
        System.out.println("----------------------------");
        NodeList list = doc.getElementsByTagName("boardgame");
       
        Element docEle = doc.getDocumentElement();
        NodeList nl = docEle.getChildNodes();

        if (nl != null) {

            int length = nl.getLength();
    //        System.out.println(length); 

            for (int i = 0; i < list.getLength(); i++) {
                //    System.out.println(" Length of Attributes: " + list.item(i).getAttributes().getLength());
            Node node = list.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element el = (Element) node;

                        if (el.getNodeName().contains("boardgame")) {
                            gameObjectId = el.getAttribute("objectid");
                            System.out.println("Game Object Id: " + gameObjectId);
                            
                            NodeList name = el.getElementsByTagName("name");
                            nameOfGame = name.item(0).getTextContent();
                             System.out.println("Name Of Game: " + nameOfGame);                        
                      
                            NodeList yearpublished = el.getElementsByTagName("yearpublished");
                            String yearpublishedOfGame = yearpublished.item(0).getTextContent();
                             System.out.println("Publication Year Of Game: " + yearpublishedOfGame);
                             System.out.println("----------------------------");
                             System.out.println(); 
                             
                            NodeList poll = el.getElementsByTagName("poll");
                    //        for (int j = 0; j < poll.getLength(); j++) {                  
                            //    if (poll != null) {                             
                                Element    pollElement = (Element) poll.item(1);
                                
                                    // if (pollElement != null) {
                                        String languageDependence = pollElement.getAttribute("name");
                                        System.out.println("Language Dependence: " + languageDependence);
                                        

                                        
                                        String totalvotes = pollElement.getAttribute("totalvotes");
                                        System.out.println("Total number of votes : " + totalvotes);
                                        System.out.println();
                                        
                                        int intTotalVotes = Integer.valueOf(totalvotes);
                                        if (intTotalVotes == 0) {
                                            System.out.println("Total number of Votes for Language Dependence on this game is ZERO (0): ");
                                            System.exit(0);
                                        }
                                        
                                         NodeList results = pollElement.getElementsByTagName("results");
                                         
                                         Element    resultsElement = (Element) results.item(0);
                                         
                                         NodeList result = resultsElement.getElementsByTagName("result");
                                         
                                         for (int k = 0; k < result.getLength(); k++) {  
                                             
                                             Element resultElement = (Element) result.item(k);
                                             
                                             String level = resultElement.getAttribute("level");
                                             System.out.println("Language dependence level : " + level);
                                             
                                             String value = resultElement.getAttribute("value");
                                             strMostVotedLD.add(value);
                                             System.out.println("Text value : " + value);
                                             
                                             
                                             String numvotes = resultElement.getAttribute("numvotes");
                                             System.out.println("Number of votes: " + numvotes); 
                                             System.out.println();
                                             
                                             Integer intNumVotes = Integer.parseInt(numvotes);
                                             mostVotedLanguageDependence.put(k+1, intNumVotes);                                           
                                             
                                         }
                                         
                                  Map.Entry entryWithMaxValue = findKeyWithMaxValue (mostVotedLanguageDependence);    
                                  
                                  Integer keyMax = (Integer) entryWithMaxValue.getKey();
                                  Integer valMax = (Integer) entryWithMaxValue.getValue();
                                  
                                  System.out.println();
                                  System.out.println("Most Voted is Level: " + keyMax + ", with votes: " + valMax + ", " +  
                                  strMostVotedLD.get ( keyMax - 1 ) );  
                                  currentMostVotedLD = strMostVotedLD.get ( keyMax - 1 );
                                  System.out.println();                                  
                        }
                    }
            }
        }
    } catch (Exception e) {
	e.printStackTrace();
    }    
}
    
  public static void main(String argv[]) {
      
      ReadXMLGameDetails xmlGameDetails = new ReadXMLGameDetails();      
      xmlGameDetails.ReadXMLGameDetailsImpl();    
  }

}

