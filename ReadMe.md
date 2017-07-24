BGG UAT for 4finance. 2 REST API Calls, 2 XML Responses studied. 

I print a User’s Collection of Games, I then choose one randomly to visit, and check out.
Then I Go to Game Page, check and print info of the game. 
I also ‘assertThat’ the scraped string of the most voted language dependence level is the same 
as the one coming out of the XML Response.

So, the Flow of the Scenario is:

Given I have a <username> of BGG (in this case ‘mkgray’)

When I read through the <Game_Collection> of the User, I Print all  <Object_Id>’s and Other Info of the Games in Collection.

Then I get a Random number (1-1511), which is number of Games in Collection of user, and I get that Game’s <Object_ID>

And I Parse through the Game’s XML, print <Poll_Results> about <Game_Language_Dependence>
And all relevant Information about the Game.

Given a <firefox_Profile> I navigate to <Game_Page> using Selenium

When I am in the Page, after I made sure everything in Page is loaded 

Then I <assertThat> the value of the <Most_Voted_Language_Dependence_Level> read from the XML file 
coming back as a Response to the REST-API Call is EQUAL to the Scraped alphanumeric string with XPath value the one below!


strXPathLanguageDependence = ".//*[@id='mainbody']/div/div[1]/div[1]/div[2]/ng-include/div/div/ui-view/ui-view/div[1]/
overview-module/description-module/div/div[2]/div/div[3]/div[2]/div[1]/div/ul/li/div[2]/span/span";
