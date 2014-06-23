import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.*;

public class TocHw3 {
    public static void main(String[] args) throws Exception {
        URL oracle = new URL(args[0]);
		
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        JSONArray jsonRealPrice = new JSONArray(new JSONTokener(in));

        JSONObject json;
        Pattern pattern = Pattern.compile(".*"+args[1]+".*"+args[2]+".*");
        String tests;
        Matcher matcher;
        int total = 0, num = 0;

        for(int i=0; i<jsonRealPrice.length();i++)
        {
            json = jsonRealPrice.getJSONObject(i);
            tests = json.toString();
            matcher = pattern.matcher(tests);
            if(matcher.find())
                if(Integer.valueOf(json.optString("交易年月")) >= Integer.valueOf(args[3])*100)
                {
                    total += Integer.valueOf(json.optString("總價元"));
                    num++;
                }
        }
        System.out.println(total / num);
		
        in.close();
    }
}
