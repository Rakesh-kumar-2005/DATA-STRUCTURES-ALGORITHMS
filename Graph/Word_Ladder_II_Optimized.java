package Graph;

import java.util.*;

public class Word_Ladder_II_Optimized {

    static String b;
    static ArrayList<ArrayList<String>> ans;
    static HashMap<String, Integer> mp;

    private static void dfs(String word, ArrayList<String> sequence) {

        if (word.equals(b)) {
            ArrayList<String> dup = new ArrayList<>(sequence);
            Collections.reverse(dup);
            ans.add(dup);
            return;
        }

        int level = mp.get(word);
        int len = word.length();

        for (int i = 0; i < len; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {

                char[] replacedCharArray = word.toCharArray();
                replacedCharArray[i] = ch;
                String replacedWord = new String(replacedCharArray);

                if (mp.containsKey(replacedWord) && mp.get(replacedWord) == level - 1) {

                    sequence.add(replacedWord);
                    dfs(replacedWord, sequence);
                    sequence.remove(replacedWord);

                }

            }
        }

    }

    private static ArrayList<ArrayList<String>> findLadders(String beginWord, String endWord, ArrayList<String> wordList) {

        HashSet<String> st = new HashSet<>();

        for (String currWord : wordList) {
            st.add(currWord);
        }

        b = beginWord;
        st.remove(beginWord);

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        mp = new HashMap<>();
        mp.put(beginWord, 1);

        while (! q.isEmpty()) {

            String currWord = q.poll();
            int currLevel = mp.get(currWord);

            if (currWord.equals(endWord)) {
                break;
            }

            for (int i = 0; i < currWord.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    char[] replacedCharArray = currWord.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    if (st.contains(replacedWord)) {
                        mp.put(replacedWord, currLevel + 1);
                        q.add(replacedWord);
                        st.remove(replacedWord);
                    }

                }
            }
        }

        ans = new ArrayList<>();
        if (mp.containsKey(endWord)) {
            ArrayList<String> sequence = new ArrayList<>();
            sequence.add(endWord);
            dfs(endWord, sequence);
        }

        return ans;
    }

    private static void printList(ArrayList<String> wordList) {

        for (int i = 0; i < wordList.size() - 1; i++) {
            System.out.print(wordList.get(i) + " -> ");
        }
        System.out.println(wordList.get(wordList.size() - 1));

    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String targetWord = "cog";

        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("aaaaa");
        wordList.add("caaaa");
        wordList.add("cbaaa");
        wordList.add("daaaa");
        wordList.add("dbaaa");
        wordList.add("eaaaa");
        wordList.add("ebaaa");
        wordList.add("faaaa");
        wordList.add("fbaaa");
        wordList.add("gaaaa");
        wordList.add("gbaaa");
        wordList.add("haaaa");
        wordList.add("hbaaa");
        wordList.add("iaaaa");
        wordList.add("ibaaa");
        wordList.add("jaaaa");
        wordList.add("jbaaa");
        wordList.add("kaaaa");
        wordList.add("kbaaa");
        wordList.add("laaaa");
        wordList.add("lbaaa");
        wordList.add("maaaa");
        wordList.add("mbaaa");
        wordList.add("naaaa");
        wordList.add("nbaaa");
        wordList.add("oaaaa");
        wordList.add("obaaa");
        wordList.add("paaaa");
        wordList.add("pbaaa");
        wordList.add("bbaaa");
        wordList.add("bbcaa");
        wordList.add("bbcba");
        wordList.add("bbdaa");
        wordList.add("bbdba");
        wordList.add("bbeaa");
        wordList.add("bbeba");
        wordList.add("bbfaa");
        wordList.add("bbfba");
        wordList.add("bbgaa");
        wordList.add("bbgba");
        wordList.add("bbhaa");
        wordList.add("bbhba");
        wordList.add("bbiaa");
        wordList.add("bbiba");
        wordList.add("bbjaa");
        wordList.add("bbjba");
        wordList.add("bbkaa");
        wordList.add("bbkba");
        wordList.add("bblaa");
        wordList.add("bblba");
        wordList.add("bbmaa");
        wordList.add("bbmba");
        wordList.add("bbnaa");
        wordList.add("bbnba");
        wordList.add("bboaa");
        wordList.add("bboba");
        wordList.add("bbpaa");
        wordList.add("bbpba");
        wordList.add("bbbba");
        wordList.add("abbba");
        wordList.add("acbba");
        wordList.add("dbbba");
        wordList.add("dcbba");
        wordList.add("ebbba");
        wordList.add("ecbba");
        wordList.add("fbbba");
        wordList.add("fcbba");
        wordList.add("gbbba");
        wordList.add("gcbba");
        wordList.add("hbbba");
        wordList.add("hcbba");
        wordList.add("ibbba");
        wordList.add("icbba");
        wordList.add("jbbba");
        wordList.add("jcbba");
        wordList.add("kbbba");
        wordList.add("kcbba");
        wordList.add("lbbba");
        wordList.add("lcbba");
        wordList.add("mbbba");
        wordList.add("mcbba");
        wordList.add("nbbba");
        wordList.add("ncbba");
        wordList.add("obbba");
        wordList.add("ocbba");
        wordList.add("pbbba");
        wordList.add("pcbba");
        wordList.add("ccbba");
        wordList.add("ccaba");
        wordList.add("ccaca");
        wordList.add("ccdba");
        wordList.add("ccdca");
        wordList.add("cceba");
        wordList.add("cceca");
        wordList.add("ccfba");
        wordList.add("ccfca");
        wordList.add("ccgba");
        wordList.add("ccgca");
        wordList.add("cchba");
        wordList.add("cchca");
        wordList.add("cciba");
        wordList.add("ccica");
        wordList.add("ccjba");
        wordList.add("ccjca");
        wordList.add("cckba");
        wordList.add("cckca");
        wordList.add("cclba");
        wordList.add("cclca");
        wordList.add("ccmba");
        wordList.add("ccmca");
        wordList.add("ccnba");
        wordList.add("ccnca");
        wordList.add("ccoba");
        wordList.add("ccoca");
        wordList.add("ccpba");
        wordList.add("ccpca");
        wordList.add("cccca");
        wordList.add("accca");
        wordList.add("adcca");
        wordList.add("bccca");
        wordList.add("bdcca");
        wordList.add("eccca");
        wordList.add("edcca");
        wordList.add("fccca");
        wordList.add("fdcca");
        wordList.add("gccca");
        wordList.add("gdcca");
        wordList.add("hccca");
        wordList.add("hdcca");
        wordList.add("iccca");
        wordList.add("idcca");
        wordList.add("jccca");
        wordList.add("jdcca");
        wordList.add("kccca");
        wordList.add("kdcca");
        wordList.add("lccca");
        wordList.add("ldcca");
        wordList.add("mccca");
        wordList.add("mdcca");
        wordList.add("nccca");
        wordList.add("ndcca");
        wordList.add("occca");
        wordList.add("odcca");
        wordList.add("pccca");
        wordList.add("pdcca");
        wordList.add("ddcca");
        wordList.add("ddaca");
        wordList.add("ddada");
        wordList.add("ddbca");
        wordList.add("ddbda");
        wordList.add("ddeca");
        wordList.add("ddeda");
        wordList.add("ddfca");
        wordList.add("ddfda");
        wordList.add("ddgca");
        wordList.add("ddgda");
        wordList.add("ddhca");
        wordList.add("ddhda");
        wordList.add("ddica");
        wordList.add("ddida");
        wordList.add("ddjca");
        wordList.add("ddjda");
        wordList.add("ddkca");
        wordList.add("ddkda");
        wordList.add("ddlca");
        wordList.add("ddlda");
        wordList.add("ddmca");
        wordList.add("ddmda");
        wordList.add("ddnca");
        wordList.add("ddnda");
        wordList.add("ddoca");
        wordList.add("ddoda");
        wordList.add("ddpca");
        wordList.add("ddpda");
        wordList.add("dddda");
        wordList.add("addda");
        wordList.add("aedda");
        wordList.add("bddda");
        wordList.add("bedda");
        wordList.add("cddda");
        wordList.add("cedda");
        wordList.add("fddda");
        wordList.add("fedda");
        wordList.add("gddda");
        wordList.add("gedda");
        wordList.add("hddda");
        wordList.add("hedda");
        wordList.add("iddda");
        wordList.add("iedda");
        wordList.add("jddda");
        wordList.add("jedda");
        wordList.add("kddda");
        wordList.add("kedda");
        wordList.add("lddda");
        wordList.add("ledda");
        wordList.add("mddda");
        wordList.add("medda");
        wordList.add("nddda");
        wordList.add("nedda");
        wordList.add("oddda");
        wordList.add("oedda");
        wordList.add("pddda");
        wordList.add("pedda");
        wordList.add("eedda");
        wordList.add("eeada");
        wordList.add("eeaea");
        wordList.add("eebda");
        wordList.add("eebea");
        wordList.add("eecda");
        wordList.add("eecea");
        wordList.add("eefda");
        wordList.add("eefea");
        wordList.add("eegda");
        wordList.add("eegea");
        wordList.add("eehda");
        wordList.add("eehea");
        wordList.add("eeida");
        wordList.add("eeiea");
        wordList.add("eejda");
        wordList.add("eejea");
        wordList.add("eekda");
        wordList.add("eekea");
        wordList.add("eelda");
        wordList.add("eelea");
        wordList.add("eemda");
        wordList.add("eemea");
        wordList.add("eenda");
        wordList.add("eenea");
        wordList.add("eeoda");
        wordList.add("eeoea");
        wordList.add("eepda");
        wordList.add("eepea");
        wordList.add("eeeea");
        wordList.add("ggggg");
        wordList.add("agggg");
        wordList.add("ahggg");
        wordList.add("bgggg");
        wordList.add("bhggg");
        wordList.add("cgggg");
        wordList.add("chggg");
        wordList.add("dgggg");
        wordList.add("dhggg");
        wordList.add("egggg");
        wordList.add("ehggg");
        wordList.add("fgggg");
        wordList.add("fhggg");
        wordList.add("igggg");
        wordList.add("ihggg");
        wordList.add("jgggg");
        wordList.add("jhggg");
        wordList.add("kgggg");
        wordList.add("khggg");
        wordList.add("lgggg");
        wordList.add("lhggg");
        wordList.add("mgggg");
        wordList.add("mhggg");
        wordList.add("ngggg");
        wordList.add("nhggg");
        wordList.add("ogggg");
        wordList.add("ohggg");
        wordList.add("pgggg");
        wordList.add("phggg");
        wordList.add("hhggg");
        wordList.add("hhagg");
        wordList.add("hhahg");
        wordList.add("hhbgg");
        wordList.add("hhbhg");
        wordList.add("hhcgg");
        wordList.add("hhchg");
        wordList.add("hhdgg");
        wordList.add("hhdhg");
        wordList.add("hhegg");
        wordList.add("hhehg");
        wordList.add("hhfgg");
        wordList.add("hhfhg");
        wordList.add("hhigg");
        wordList.add("hhihg");
        wordList.add("hhjgg");
        wordList.add("hhjhg");
        wordList.add("hhkgg");
        wordList.add("hhkhg");
        wordList.add("hhlgg");
        wordList.add("hhlhg");
        wordList.add("hhmgg");
        wordList.add("hhmhg");
        wordList.add("hhngg");
        wordList.add("hhnhg");
        wordList.add("hhogg");
        wordList.add("hhohg");
        wordList.add("hhpgg");
        wordList.add("hhphg");
        wordList.add("hhhhg");
        wordList.add("ahhhg");
        wordList.add("aihhg");
        wordList.add("bhhhg");
        wordList.add("bihhg");
        wordList.add("chhhg");
        wordList.add("cihhg");
        wordList.add("dhhhg");
        wordList.add("dihhg");
        wordList.add("ehhhg");
        wordList.add("eihhg");
        wordList.add("fhhhg");
        wordList.add("fihhg");
        wordList.add("ghhhg");
        wordList.add("gihhg");
        wordList.add("jhhhg");
        wordList.add("jihhg");
        wordList.add("khhhg");
        wordList.add("kihhg");
        wordList.add("lhhhg");
        wordList.add("lihhg");
        wordList.add("mhhhg");
        wordList.add("mihhg");
        wordList.add("nhhhg");
        wordList.add("nihhg");
        wordList.add("ohhhg");
        wordList.add("oihhg");
        wordList.add("phhhg");
        wordList.add("pihhg");
        wordList.add("iihhg");
        wordList.add("iiahg");
        wordList.add("iiaig");
        wordList.add("iibhg");
        wordList.add("iibig");
        wordList.add("iichg");
        wordList.add("iicig");
        wordList.add("iidhg");
        wordList.add("iidig");
        wordList.add("iiehg");
        wordList.add("iieig");
        wordList.add("iifhg");
        wordList.add("iifig");
        wordList.add("iighg");
        wordList.add("iigig");
        wordList.add("iijhg");
        wordList.add("iijig");
        wordList.add("iikhg");
        wordList.add("iikig");
        wordList.add("iilhg");
        wordList.add("iilig");
        wordList.add("iimhg");
        wordList.add("iimig");
        wordList.add("iinhg");
        wordList.add("iinig");
        wordList.add("iiohg");
        wordList.add("iioig");
        wordList.add("iiphg");
        wordList.add("iipig");
        wordList.add("iiiig");
        wordList.add("aiiig");
        wordList.add("ajiig");
        wordList.add("biiig");
        wordList.add("bjiig");
        wordList.add("ciiig");
        wordList.add("cjiig");
        wordList.add("diiig");
        wordList.add("djiig");
        wordList.add("eiiig");
        wordList.add("ejiig");
        wordList.add("fiiig");
        wordList.add("fjiig");
        wordList.add("giiig");
        wordList.add("gjiig");
        wordList.add("hiiig");
        wordList.add("hjiig");
        wordList.add("kiiig");
        wordList.add("kjiig");
        wordList.add("liiig");
        wordList.add("ljiig");
        wordList.add("miiig");
        wordList.add("mjiig");
        wordList.add("niiig");
        wordList.add("njiig");
        wordList.add("oiiig");
        wordList.add("ojiig");
        wordList.add("piiig");
        wordList.add("pjiig");
        wordList.add("jjiig");
        wordList.add("jjaig");
        wordList.add("jjajg");
        wordList.add("jjbig");
        wordList.add("jjbjg");
        wordList.add("jjcig");
        wordList.add("jjcjg");
        wordList.add("jjdig");
        wordList.add("jjdjg");
        wordList.add("jjeig");
        wordList.add("jjejg");
        wordList.add("jjfig");
        wordList.add("jjfjg");
        wordList.add("jjgig");
        wordList.add("jjgjg");
        wordList.add("jjhig");
        wordList.add("jjhjg");
        wordList.add("jjkig");
        wordList.add("jjkjg");
        wordList.add("jjlig");
        wordList.add("jjljg");
        wordList.add("jjmig");
        wordList.add("jjmjg");
        wordList.add("jjnig");
        wordList.add("jjnjg");
        wordList.add("jjoig");
        wordList.add("jjojg");
        wordList.add("jjpig");
        wordList.add("jjpjg");
        wordList.add("jjjjg");
        wordList.add("ajjjg");
        wordList.add("akjjg");
        wordList.add("bjjjg");
        wordList.add("bkjjg");
        wordList.add("cjjjg");
        wordList.add("ckjjg");
        wordList.add("djjjg");
        wordList.add("dkjjg");
        wordList.add("ejjjg");
        wordList.add("ekjjg");
        wordList.add("fjjjg");
        wordList.add("fkjjg");
        wordList.add("gjjjg");
        wordList.add("gkjjg");
        wordList.add("hjjjg");
        wordList.add("hkjjg");
        wordList.add("ijjjg");
        wordList.add("ikjjg");
        wordList.add("ljjjg");
        wordList.add("lkjjg");
        wordList.add("mjjjg");
        wordList.add("mkjjg");
        wordList.add("njjjg");
        wordList.add("nkjjg");
        wordList.add("ojjjg");
        wordList.add("okjjg");
        wordList.add("pjjjg");
        wordList.add("pkjjg");
        wordList.add("kkjjg");
        wordList.add("kkajg");
        wordList.add("kkakg");
        wordList.add("kkbjg");
        wordList.add("kkbkg");
        wordList.add("kkcjg");
        wordList.add("kkckg");
        wordList.add("kkdjg");
        wordList.add("kkdkg");
        wordList.add("kkejg");
        wordList.add("kkekg");
        wordList.add("kkfjg");
        wordList.add("kkfkg");
        wordList.add("kkgjg");
        wordList.add("kkgkg");
        wordList.add("kkhjg");
        wordList.add("kkhkg");
        wordList.add("kkijg");
        wordList.add("kkikg");
        wordList.add("kkljg");
        wordList.add("kklkg");
        wordList.add("kkmjg");
        wordList.add("kkmkg");
        wordList.add("kknjg");
        wordList.add("kknkg");
        wordList.add("kkojg");
        wordList.add("kkokg");
        wordList.add("kkpjg");
        wordList.add("kkpkg");
        wordList.add("kkkkg");
        wordList.add("ggggx");
        wordList.add("gggxx");
        wordList.add("ggxxx");
        wordList.add("gxxxx");
        wordList.add("xxxxx");
        wordList.add("xxxxy");
        wordList.add("xxxyy");
        wordList.add("xxyyy");
        wordList.add("xyyyy");
        wordList.add("yyyyy");
        wordList.add("yyyyw");
        wordList.add("yyyww");
        wordList.add("yywww");
        wordList.add("ywwww");
        wordList.add("wwwww");
        wordList.add("wwvww");
        wordList.add("wvvww");
        wordList.add("vvvww");
        wordList.add("vvvwz");
        wordList.add("avvwz");
        wordList.add("aavwz");
        wordList.add("aaawz");
        wordList.add("aaaaz");

        System.out.println("The begin word is : " + beginWord);
        System.out.println("The end word is : " + targetWord);
        System.out.println("The word list is : ");
        printList(wordList);
        System.out.println();

        ArrayList<ArrayList<String>> ans = findLadders(beginWord, targetWord, wordList);

        if (ans.isEmpty()) {
            System.out.println("No sequence found");
            return;
        }

        System.out.println("The Sequence through which we can convert the begin word to end word is : ");

        for (ArrayList<String> currSequence : ans) {
            printList(currSequence);
            System.out.println();
        }
    }

}
