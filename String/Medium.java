package String;

public class Medium {

    /*
        Question1: Sub-string finding
        How to determine whether a string is a substring of another string?

        Eg: s1 = abcde; s2 = cde --> matched
     */

    public int subStringFind(String s1, String s2) {
        //assume that s2 is shorter that s1
        if (s1 == null || s2 == null || s1.length() < s2.length()) return -1;

        int j = 0, i = 0; // j for s1, i for s2
        while (j <= s1.length() - s2.length() && i < s2.length()) {
            if (s1.charAt(j) != s2.charAt(i)) {
                j++;
            } else { // if (s1.charAt(j) == s2.charAt(i))
                while (j < s1.length() && i < s2.length() && s1.charAt(j) == s2.charAt(i)) {
                    i++;
                    j++;
                }
                if (i == s2.length()) {
                    return j - s2.length();
                }
                i = 0;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Medium m = new Medium();
        //Question1
        String s11 = "abcde";
        String s12 = "edc";
        int result1 = m.subStringFind(s11, s12);
        System.out.println(result1);
    }
}
