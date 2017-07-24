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
public class FourFinanceUserGames {
    
    public static void main(String[] argv) throws InterruptedException  {
        String userName = "mkgray";
        String firefoxProfile; //= "SimBin";
        
            firefoxProfile = argv[0];      
            GetUserGameCollection getUGC = new GetUserGameCollection();
            getUGC.GetUserGameCollectionImpl(userName);
            
            ReadXMLUserGameCollection xmlUGC = new ReadXMLUserGameCollection();
            xmlUGC.ReadXMLUserGameCollectionImpl();
            
//    int intGameObjectId = objectid of game, based on Random number (1 - 1511) i.e. Game Collection of 'mkgray';
            int intGameObjectId = ReadXMLUserGameCollection.chosenGameObjectId;
            String strGameObjectId = String.valueOf(intGameObjectId);
            
            GetGameDetails gameDetail = new GetGameDetails();            
            gameDetail.GetGameDetailsImpl(strGameObjectId);
            
            ReadXMLGameDetails xmlGameDetails = new ReadXMLGameDetails();      
            xmlGameDetails.ReadXMLGameDetailsImpl();             
                        
            WebScraperBGG scrape = new WebScraperBGG();
            scrape.WebScraperBGGImpl(strGameObjectId, firefoxProfile);   
  }    
    
}
