import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class POSTRequestExample {
   public static void main(String[] args) 
   {
      try 
      {
         URL url = new URL("http://34.120.190.133/auth/register");
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Type", "application/json; utf-8");
         conn.setDoOutput(true);

         String accessToken = null;
         String jsonInputString = "{\"username\": \"Jack12\", \"email\": \"example12@gmail.com\",\"password\": \"qwerty1H^\",\"passwordConfirm\": \"qwerty1H^\"}"; 

         try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        
         if (conn.getResponseCode() != 201) {                                                       
            throw new RuntimeException("Failed : POST HTTP error code : " + conn.getResponseCode());
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