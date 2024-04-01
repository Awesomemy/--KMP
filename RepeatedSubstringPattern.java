package test5;

/**
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 */
public class RepeatedSubstringPattern {
    /*
    结论:在由重复子串组成的字符串中，最长相等前后缀不包含的子串就是最小重复子串
    那么问题就变成了求字符串s对应的next[]数组中最后一位的值
    */
    public boolean repeatedSubstringPattern(String s) {
        //这里采用的是前缀表对应值减1作为next数组
        if("".equals(s)){
            return false;
        }
        int len = s.length();
        //原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " "+s;
        char[] chars = s.toCharArray();
        int[] next = new int[len+1];
        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for(int i=2,j=0; i<=len ; i++){
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j>0 && chars[j+1]!=chars[i]) {
                j=next[j];
            }
            // 匹配成功，j往后移
            if (chars[j+1]==chars[i]) {
                j++;
            }
            // 更新 next 数组的值
            next[i]=j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len]>0 && len%(len-next[len])==0) {
            return true;
        }
        return false;
    }
    /*
    另外本题还有一种思路:
    将字符串s与自身拼接,即令s1=s+s
    再将s1的首尾字母删掉得到s2
    如果s能够通过子串多次重复构成
    那么s2中必定存在子串s
    那么问题就变成了判断s2是否包含s
    可以利用contains()方法,也可以自己用kmp算法判断
     */
}
