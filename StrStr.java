package test5;

/**
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class StrStr {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;    //当模式串为空,直接返回0
        }
        int[] next = new int[needle.length()];
        getNext(next , needle);

        //文本串匹配模式串的过程与生成next[]数组的过程是一样的
        int j=0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j>0 && haystack.charAt(i)!=needle.charAt(j)) {
                j=next[j-1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                return i-needle.length()+1;
            }
        }
        return -1;
    }

    //这里是获取不减一的next数组,也就是模式串的前缀表
    private void getNext(int[] next , String s){
        int j = 0;
        next[0] = 0;
        for(int i=1 ; i<s.length() ;i++){
            //此处回退是循环进行的,也就是要么找到前后缀相等,要么j退回到0
            while (j>0 && s.charAt(i)!=s.charAt(j)) {
                j = next[j-1];
            }
            //若是前后缀相等的情况,j+1,因为j除了下标外也表示当前最大相等前后缀的长度
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            //无论是否找到相等前后缀,都要更新next[i]的值
            next[i]=j;
        }
    }
}
