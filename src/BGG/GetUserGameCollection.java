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
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class GetUserGameCollection {
    String boardgameCollectionURL = "https://www.boardgamegeek.com/xmlapi/collection/";
    
    public void GetUserGameCollectionImpl(String boardgamegeekUN) {
        
        String boardgamegeekURL = boardgameCollectionURL + boardgamegeekUN;
        
         try {
		URL url = new URL(boardgamegeekURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/xml");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
                
                FileWriter writer = new FileWriter("usergamecollection.xml");
                try(PrintWriter printWriter = new PrintWriter(writer)){
                    
                    while ((output = br.readLine()) != null) {
                    //        System.out.println(output);
                    
                    printWriter.println(output);          

                    }
                }
                conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

    }

	// http://localhost:8080/RESTfulExample/json/product/get
    //https://www.boardgamegeek.com/xmlapi/collection/mkgray
    
	public static void main(String[] args) {
            String userName = "mkgray";
            
            GetUserGameCollection getUGC = new GetUserGameCollection();
            getUGC.GetUserGameCollectionImpl(userName);
            
	}

}


