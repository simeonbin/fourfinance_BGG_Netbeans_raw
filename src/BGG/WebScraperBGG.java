package BGG;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simeon
 */
class WebScraperBGG {
    
static String urlBGGGames = "https://boardgamegeek.com/";
static String urlBGGGamesBoardgamegeek = "https://www.boardgamegeek.com/boardgame/";        
static String urlBGGGamesWithObjectId = "7865";
static String strXPathLanguageDependence = ".//*[@id='mainbody']/div/div[1]/div[1]/div[2]/ng-include/div/div/ui-view/ui-view/div[1]/overview-module/description-module/div/div[2]/div/div[3]/div[2]/div[1]/div/ul/li/div[2]/span/span";
static String gameObjectId = "7865";        
        
public void navigateToGameWithObjectId(WebDriver driver, String objectid) {
    WebDriverWait wait;
    
    String urlBGGGamesBoardgmameWithObjectId = urlBGGGamesBoardgamegeek + objectid;
    
//      driver.navigate().to(urlBGGGames);      
//      wait = new WebDriverWait(driver, 15);
//      wait.until(ExpectedConditions.urlMatches(urlBGGGames));
      
      driver.navigate().to(urlBGGGamesBoardgmameWithObjectId);        
      wait = new WebDriverWait(driver, 15);
      wait.until(ExpectedConditions.urlMatches(urlBGGGamesBoardgmameWithObjectId));    
}

public void scrapeGameInfo(WebDriver driver ) {    
    String textLanguageDependence = driver.findElement(By.xpath(strXPathLanguageDependence)).getText();
           System.out.println ("Text Language Dependence scraped by Selenium: " + textLanguageDependence);
           System.out.println ("Text Language Dependence in REST API Call is: " + ReadXMLGameDetails.currentMostVotedLD);
           
           if (textLanguageDependence.equalsIgnoreCase(ReadXMLGameDetails.currentMostVotedLD) ) {
              System.out.println("Language Dependence exists for Game " + ReadXMLGameDetails.nameOfGame + " and is loaded on Game Page");
           }
           else {
                 System.out.println("Language Dependence does not exists for Game " + ReadXMLGameDetails.nameOfGame + " or not loaded on Game Page");
                }
           assertThat( ReadXMLGameDetails.currentMostVotedLD, is(equalTo(textLanguageDependence)) );
           
}

    public void WebScraperBGGImpl(String gameObjectId, String firefoxProfile) {     
     
        WebDriverWait wait;
        String valueOfCookie;

        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile(firefoxProfile);
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(false);
        WebDriver driver2 = new FirefoxDriver(myProfile);    
        
        navigateToGameWithObjectId(driver2, gameObjectId);        
        scrapeGameInfo(driver2);
         
        driver2.close();      
    }    
    
    public static void main(String[] args) throws IOException {
        
        gameObjectId = "7865";
        String firefoxProfile = "SimBin";
        WebScraperBGG scrape = new WebScraperBGG();
        scrape.WebScraperBGGImpl(gameObjectId, firefoxProfile);       
   
//.//*[@id='mainbody']/div/div[1]/div[1]/div[2]/ng-include/div/div/ui-view/ui-view/div[1]/overview-module/description-module/div/div[2]/div/div[3]/div[2]/div[1]/div/ul/li/div[2]/span/span
           
    }    
}
