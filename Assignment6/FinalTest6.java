
public class FinalTest6 {

	public void run () {
		
	}
	
	public boolean isGooglewhack(String w1, String w2) {
		String[] search1 = googleSearch(w1);
		String[] search2 = googleSearch(w2);
		int result = 0;
		for (int i = 0; i < search1.length; i++) {
			for (int j = 0; j < search2.length; j++) {
				if (search1[i] == search2[j]) {
					result++;
				}
				if (result > 1) {
					return false;
				}
			}
		}
		return (result == 1);		
	}
	
	public String[] googleSearch(String word) {
		return new String[2];
	}
	
}
