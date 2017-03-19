import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class LeftRecursion {
	// public static MyGrammar grammar = new MyGrammar();

	public static ArrayList<String> afterSubstitution = new ArrayList<String>();

	public static void ImmediateLeftRec(String key, ArrayList<String> value) {
		ArrayList<String> temp = new ArrayList<String>();

		for (int poi = 0; poi < value.size(); poi++) {

			if (value.get(poi).startsWith(key)) {

				for (int i = 0; i < value.size(); i++) {
					if (value.get(i).startsWith(key)) {
						temp.add(value.get(i));

					}
				}
				// System.out.println(temp);
				for (int j = 0; j < temp.size(); j++) {
					// System.out.println(temp.get(j));
					value.remove(temp.get(j));

				}

				// da el E el adeem
				//System.out.print(key + "->");
				ArrayList<String> modified = new ArrayList<String>();
				for (int qw = 0; qw < value.size(); qw++) {
					String normalMod = value.get(qw) + key + "'";
					modified.add(normalMod);

				}
				MyGrammar.map.put(key, modified);

				System.out.println();
				// EL gedeeed
			//	System.out.print(key + "'" + "->");
				ArrayList<String> newKeyDash = new ArrayList<String>();
				for (int k = 0; k < temp.size(); k++) {
					String x = temp.get(k).substring(key.length());
					x = x + key + "'";

					newKeyDash.add(x);

				}

				newKeyDash.add("!");
				MyGrammar.map.put(key + "'", newKeyDash);

			/*	for (int d = 0; d < newKeyDash.size(); d++) {
					if (d == newKeyDash.size() - 1) {
					//	System.out.print(newKeyDash.get(d));
					} else {
						//System.out.print(newKeyDash.get(d) + ",");

					}

				}*/

			}

		}
	}

	public static void IndirectLeftRec() {
		for (int i = 0; i < MyGrammar.nonTerminals.size(); i++) {
			for (int j = 0; j < i; j++) {
				String keyrightnow = MyGrammar.nonTerminals.get(i);
				// System.out.println();
				// System.out.println("P is " + keyrightnow);
				ArrayList<String> rightsideofKeyNow = (ArrayList<String>) MyGrammar.map
						.get(keyrightnow);
				// System.out.println(rightsideofKeyNow);
				// System.out.println(MyGrammar.nonTerminals.get(j));
				ArrayList<String> mapValues = MyGrammar.map
						.get(MyGrammar.nonTerminals.get(j));
				// System.out.println(mapValues);
				for (int k = 0; k < rightsideofKeyNow.size(); k++) {
					// System.out.println(rightsideofKeyNow);
					// System.out.println( MyGrammar.nonTerminals.get(j));

					if (rightsideofKeyNow.get(k).startsWith(
							MyGrammar.nonTerminals.get(j))) {
						String notIncluded = rightsideofKeyNow.get(k)
								.substring(
										MyGrammar.nonTerminals.get(j).length());
						for (int e = 0; e < mapValues.size(); e++) {
							String r = mapValues.get(e) + notIncluded;
							afterSubstitution.add(r);
						}
						// System.out.println( notIncluded);
						// System.out.println(afterSubstitution);
						rightsideofKeyNow.remove(k);

						rightsideofKeyNow.addAll(afterSubstitution);

						// System.out.println(rightsideofKeyNow);

						MyGrammar.map.put(keyrightnow, rightsideofKeyNow);
						System.out.println("New map after substitution: ");
						MyGrammar.printMap(MyGrammar.map);

					}

				}

			}
			// System.out.println(MyGrammar.map.get(MyGrammar.nonTerminals.get(i)));

			ImmediateLeftRec(MyGrammar.nonTerminals.get(i),
					MyGrammar.map.get(MyGrammar.nonTerminals.get(i)));

		}
	}

	public static void main(String[] args) {
		MyGrammar grammar = new MyGrammar();

	
		
		FileReader fr = null;
		FileWriter fw = null;
		
		try{
			File file = new File("Sample.out");
			
			String sCurrentLine;

		
			 fw = new FileWriter(file);
			

			 IndirectLeftRec();
				System.out.println("Final Map is:");
				System.out.println();
				MyGrammar.printMap(MyGrammar.map);
				
				Iterator it = MyGrammar.map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					fw.write(pair.getKey() + "->" + pair.getValue());
					fw.write("\n");
				}
				
			
			
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				
				if (fr != null)
					fr.close();
				

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}


	}
	

	


	}

}
