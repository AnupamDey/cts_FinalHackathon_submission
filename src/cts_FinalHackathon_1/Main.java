package cts_FinalHackathon_1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {
	
	private static final int index = 2;

	@SuppressWarnings({ "unchecked", "unused" })
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("G:\\EclipseWorkspace\\cts_FinalHackathon_1\\Memory.txt"));
		FileWriter file = new FileWriter("output_mem.json");
		String str = "";
		String[] records = null;
		int i = 0,c = 0;
		double max_mem = Double.MIN_VALUE,sum = 0.0D;
		JSONArray memory = new JSONArray();
		JSONObject mems = new JSONObject();
		JSONObject vals = new JSONObject();
		JSONObject res = new JSONObject();
		while((str = br.readLine()) != null) {
			i++;
			if(i % 2 == 0) {
				++c;
				if(str.trim().startsWith("TOTAL:")) records = str.split("\\s+");
				String t = String.valueOf(i).concat("s");
				double m = Double.parseDouble(records[index]);
				m /= 1024.0;
				mems.put(t,String.valueOf(m));
//				list.add(m);
				if(m > max_mem) {
					max_mem = m;
				}
				sum += m;
			}
		}
//		double avg = list.stream().mapToDouble(x -> x).average().orElse(0.0D);
		double avg = (double)(sum / c);
//		double max_mem = Collections.max(list);
		DecimalFormat df = new DecimalFormat("#.##");
		String avgf = df.format(avg);
		String maxmemf = df.format(max_mem);
		vals.put("AverageMemory(MB)",avgf);
		vals.put("Values", mems);
		vals.put("MaxMemory(MB)", maxmemf);
		res.put("sample_output", vals);
		res.put("Usecasename", "HomePage");
		memory.add(res);
		System.out.println(memory);
		
		file.write(memory.toJSONString());
		file.flush();
	}

}
