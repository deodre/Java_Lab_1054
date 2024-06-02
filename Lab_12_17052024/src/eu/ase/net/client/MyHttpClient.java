package eu.ase.net.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MyHttpClient {

	public static void main(String[] args) {
		
		try {
            HttpClient httpClient = HttpClient.newHttpClient(); //Create a HttpClient
            System.out.println(httpClient.version());
            HttpRequest httpRequest = 
            		HttpRequest.newBuilder().uri(new URI("https://www.example.com/")).GET().build(); //Create a GET request for the given URI
            Map <String, List<String> > headers = httpRequest.headers().map();
            headers.forEach((k, v) -> System.out.println(k + " - " + v.toString()));
            
            
            HttpResponse < String > httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println("HTTP2 response = \n" + httpResponse.body());
        
            CompletableFuture<HttpResponse<String>> httpResponse2 
            	= httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
            
            Thread.currentThread().sleep(5000);
            
            if(httpResponse2.isDone()) {
            	System.out.println("\n\n httpResponse2 = \n");
                System.out.println(httpResponse2.get().statusCode());
                System.out.println(httpResponse2.get().body());
            } else {
            	System.out.println("Response not received!");
                httpResponse2.cancel(true);
            }
            
            //Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            System.out.println("message " + e);
        }
		
	}

}
