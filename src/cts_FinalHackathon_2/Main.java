package cts_FinalHackathon_2;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("G:\\EclipseWorkspace\\cts_FinalHackathon_2\\Battery.txt"));
		FileWriter file = new FileWriter("output_battery.json");
		String str = "";
		String[] sub = null;
//		String[] records2 = null;
		String temp1 = "",temp2="";
		int i = 0;
		JSONObject res = new JSONObject();
		while((str = br.readLine()) != null) {
			++i;
//			System.out.println(str);
			if(str.contains("Foreground activities:")) {
				temp1 = str.substring(str.indexOf("activities:")+12);
				temp1 = temp1.substring(0,32);
//				System.out.println(temp1);
			}
			if(str.contains("Uid u0a202:")) {
				temp2 = str.substring(str.indexOf("Uid u0a202:")+12);
				temp2 = temp2.substring(0,5);
//				System.out.println(temp2);
			}
		}
		double bp = Double.parseDouble(temp2)/1000.0D;
		DecimalFormat df = new DecimalFormat("#.###");
		df.setRoundingMode(RoundingMode.CEILING);
		res.put("Foreground_time", temp1);
		res.put("Battery_percentage", df.format(bp));
		res.put("Battery_drain", temp2);
		System.out.println(res);
		
		file.write(res.toJSONString());
		file.flush();
	}

}
