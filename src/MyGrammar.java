import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MyGrammar {
	static ArrayList<String> nonTerminals = new ArrayList<String>();
	static ArrayList<String> rightSide = new ArrayList<String>();
	public static LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
	public static ArrayList<String> temp = new ArrayList<String>();
	public static ArrayList<String> newKeyDash = new ArrayList<String>();

	public static void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + "->" + pair.getValue());
		}
	}

	public MyGrammar() {
		try {
			BufferedReader buf = new BufferedReader(new FileReader("Sample.in"));
			ArrayList<String> words = new ArrayList<>();
			String lineJustFetched = null;
			String[] wordsArray;
			int lines = 0;

			while (true) {
				lineJustFetched = buf.readLine();
				// lines++;
				if (lineJustFetched != null) {
					System.out.println(lineJustFetched);

				}
				if (lineJustFetched == null) {
					break;
				} else {
					lines++;
					wordsArray = lineJustFetched.split("\t");
					for (String each : wordsArray) {
						if (!"".equals(each)) {
							words.add(each);
						}
					}
				}
			}

			System.out.println("The variables are: ");
			for (int i = 0; i < lines; i += 2) {
				String v = words.get(i);
				nonTerminals.add(v);
			}
			System.out.println(nonTerminals);
			System.out.println();

			System.out.println("The right side of rules are: ");
			for (int i = 1; i < lines; i += 2) {
				String v = words.get(i);
				rightSide.add(v);

			}
			System.out.println(rightSide);
			System.out.println();

			for (int i = 0; i < nonTerminals.size(); i++) {

				ArrayList<String> x = new ArrayList<String>(
						Arrays.asList(rightSide.get(i).split("\\|")));

				map.put(nonTerminals.get(i), x);

				for (int j = 0; j < x.size(); j++) {
					System.out.print(x.get(j) + " ");

				}
				System.out.println();

			}

			System.out.println();

			System.out.println("The map is: ");

			TreeMap<String, ArrayList<String>> myMap = new TreeMap<String, ArrayList<String>>(
					map);
			ArrayList<String> first = myMap.firstEntry().getValue();

			printMap(map);
					//	System.out.println("heree" + map.get("E"));


		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
