/**
 * Created by taihejin on 16-6-7.
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class P30 {

//    public List<Integer> findSubstring(String s, String[] words) {
//        List<Integer> ret = new ArrayList<Integer>();
//        if (words == null || words.length == 0 || s == null || s.isEmpty()) {
//            return null;
//        }
//        Map<String, Integer> baseMap = new HashMap<String, Integer>();
//        for (String key : words) {
//            Integer value = baseMap.get(key);
//            if (value == null) {
//                baseMap.put(key, 1);
//            } else {
//                baseMap.put(key, value + 1);
//            }
//        }
//        int span = words[0].length();
//        int size = s.length();
//        Map<String, Integer> dictMap = new HashMap<String, Integer>();
//        Queue<String> windowQueue = new ArrayDeque<String>();
//        for (int i = 0, counter = 0; i <= size - span; counter++) {
//            while (i <= size - span && windowQueue.size() < words.length) {
//                String word = s.substring(i, i + span);
//                windowQueue.offer(word);
//                Integer value = dictMap.get(word);
//                if (value == null) {
//                    dictMap.put(word, 1);
//                } else {
//                    dictMap.put(word, value + 1);
//                }
//                i += span;
//            }
//            boolean correct = true;
//            for (String word : baseMap.keySet()) {
//                if (dictMap.get(word) != baseMap.get(word)) {
//                    correct = false;
//                    break;
//                }
//            }
//            if (correct) {
//                ret.add(counter * span);
//            }
//            String first = windowQueue.poll();
//            Integer val = dictMap.get(first);
//            if (val != 1) {
//                dictMap.put(first, val - 1);
//            } else {
//                dictMap.remove(first);
//            }
//
//        }
//        return ret;
//    }


    public List<Integer> findSubstringss(String s, String[] words) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(s==null||s.length()==0||words==null||words.length==0){
            return result;
        }
        //frequency of words
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String w: words){
            if(map.containsKey(w)){
                map.put(w, map.get(w)+1);
            }else{
                map.put(w, 1);
            }
        }
        int len = words[0].length();
        for(int j=0; j<len; j++){
            HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
            int start = j;//start index of start
            int count = 0;//count totoal qualified words so far
            for(int i=j; i<=s.length()-len; i=i+len){
                String sub = s.substring(i, i+len);
                if(map.containsKey(sub)){
                    //set frequency in current map
                    if(currentMap.containsKey(sub)){
                        currentMap.put(sub, currentMap.get(sub)+1);
                    }else{
                        currentMap.put(sub, 1);
                    }
                    count++;
                    while(currentMap.get(sub)>map.get(sub)){
                        String left = s.substring(start, start+len);
                        currentMap.put(left, currentMap.get(left)-1);
                        count--;
                        start = start + len;
                    }
                    if(count == words.length) {
                        result.add(start); //add to result
                        //shift right and reset currentMap, count & start point
                        String left = s.substring(start, start+len);
                        currentMap.put(left, currentMap.get(left)-1);
                        count--;
                        start = start + len;
                    }
                }else{
                    currentMap.clear();
                    start = i + len;
                    count = 0;
                }
            }
        }
        return result;
    }


    public ArrayList<Integer> findSubstring33(String S, String[] L) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(L==null || L.length==0) return null;
        int n = L.length, m = L[0].length(), l=S.length();
        ArrayList<Integer> res = new ArrayList<Integer>();

        Map<String,Integer> covered = new HashMap<String,Integer>();
        for(int j=0;j<n;j++){
            if(covered.containsKey(L[j])){
                covered.put(L[j],covered.get(L[j])+1);
            }else{
                covered.put(L[j], 1);
            }
        }

        int i=0;
        while(l-i>=n*m){
            Map<String, Integer> temp = new HashMap<String, Integer>(covered);
            for(int j=0;j<n;j++){
                String testStr = S.substring(i+j*m,i+(j+1)*m);
                if(temp.containsKey(testStr)){
                    if(temp.get(testStr)-1==0)
                        temp.remove(testStr);
                    else
                        temp.put(testStr,temp.get(testStr)-1);
                }else
                    break;
            }
            if(temp.size()==0) res.add(i);
            i++;
        }
        return res;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<Integer>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return ret;
        }
        Map<String, Integer> dictMap = new HashMap<String, Integer>();
        for (String word : words) {
            if (dictMap.containsKey(word)) {
                dictMap.put(word, dictMap.get(word) + 1);
            } else {
                dictMap.put(word, 1);
            }
        }
        Map<String, Integer> currentMap = new HashMap<String, Integer>();
        int span = words[0].length();
        int segment = words.length * span;
        int size = s.length();
        for (int i = 0; i < span; i++) {
            for (int low = i, high = low; low <= size - span; low += span) {
                boolean valid = true;
                while (high <= size - span && high - low < segment) {
                    String word = s.substring(high, high + span);
                    Integer dictFrq, currentFrq;
                    if ((dictFrq = dictMap.get(word)) != null) {
                        if ((currentFrq = currentMap.get(word)) == null || currentFrq < dictFrq) {
                            currentMap.put(word, 1 + (currentFrq == null ? 0 : currentFrq));
                            high += span;
                        } else {
                            String first = s.substring(low, low + span);
                            Integer frq;
                            if ((frq = currentMap.get(first)) == 1) {
                                currentMap.remove(first);
                            } else {
                                currentMap.put(first, frq - 1);
                            }
                            valid = false;
                            break;
                        }
                    } else {
                        valid = false;
                        currentMap.clear();
                        low = high;
                        high += span;
                        break;
                    }
                }
                if (valid && high - low == segment) {
                    ret.add(low);
                    String first = s.substring(low, low + span);
                    Integer frq;
                    if ((frq = currentMap.get(first)) == 1) {
                        currentMap.remove(first);
                    } else {
                        currentMap.put(first, frq - 1);
                    }
                }
            }
            currentMap.clear();
        }
        return ret;
    }

    public List<Integer> findSubstring12(String s, String[] words) {
        List<Integer> ret = new ArrayList<Integer>();
        if (words == null || words.length == 0 || s == null) {
            return ret;
        }
        int size = s.length();
        int wordLen = words[0].length();
        int segmentLen = wordLen * words.length;
        Map<String, Integer> dictMap = new HashMap<String, Integer>();
        for (String key : words) {
            Integer val = dictMap.get(key);
            if (val == null) {
                dictMap.put(key, 1);
            } else {
                dictMap.put(key, val + 1);
            }
        }
        Map<String, Integer> currentMap = new HashMap<String, Integer>();
        for (int i = 0; i <= size - segmentLen; i++) {
            int k = i;
            boolean failed = false;
            while (k < i + segmentLen) {
                String word = s.substring(k, k + wordLen);
                Integer val = null;
                if (dictMap.containsKey(word) && ((val = currentMap.get(word)) == null || val < dictMap.get(word))) {
                    if (val == null) {
                        currentMap.put(word, 1);
                    } else {
                        currentMap.put(word, val + 1);
                    }
                } else {
                    failed = true;
                    currentMap.clear();
                    break;
                }
                k += wordLen;
            }
            if (!failed) {
                boolean founded = true;
                for (String key : currentMap.keySet()) {
                    if (currentMap.get(key) != dictMap.get(key)) {
                        founded = false;
                        break;
                    }
                }
                if (founded) {
                    ret.add(i);
                    currentMap.clear();
                }
                i += wordLen - 1;
            }
        }
        return ret;
    }

    @Test
    public void doTest() {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        Assert.assertEquals(2, ret.size());
    }

    @Test
    public void doTest1() {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        Assert.assertEquals(3, ret.size());
    }

    @Test
    public void doTest2() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        Assert.assertEquals(1, ret.size());
    }

    @Test
    public void doTest3() {
        String s = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
        String[] words = {"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        //Assert.assertEquals(100, ret.size());
    }

    @Test
    public void doTest4() {
        String s = "abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
        String[] words = {"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};
        List<Integer> ret = findSubstring12(s, words);
        System.out.println(ret);
        //Assert.assertEquals(100, ret.size());
    }

    @Test
    public void test5() {
        //["fooo","barr","wing","ding","wing"]
                 //"ling mind rabo o fooowingdingbarrwing monkeypoundcake"
        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] words = {"fooo","barr","wing","ding","wing"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        Assert.assertEquals(1, ret.size());
    }

    @Test
    public void test6() {
        String s = "mississippi";
        String[] words = {"is"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        Assert.assertEquals(2, ret.size());
    }

    @Test
    public void test7() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        Assert.assertEquals(1, ret.size());
    }

    @Test
    public void test8() {
        String s = "aaaaaaaa";
        String[] words = {"aa","aa","aa"};
        List<Integer> ret = findSubstring(s, words);
        System.out.println(ret);
        Assert.assertEquals(3, ret.size());
    }
}
