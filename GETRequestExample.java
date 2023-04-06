import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GETRequestExample
{
   public static void main(String[] args) 
   {
      try 
      {
         URL url = new URL("http://34.120.190.133/auth/me");
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Accept", "application/json");

         String accessToken = null;


         if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
         }

         BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

         String output;
         while ((output = br.readLine()) != null) {
            System.out.println(output);
         }

         conn.disconnect();

      } catch (Exception e) {
         e.printStackTrace();
      }
    }
}