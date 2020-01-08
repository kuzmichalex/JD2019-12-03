package by.it.plugatar.jd01_06;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//TaskA2.Определить, сколько раз повторяется в тексте каждое слово,
// которое встречается в нем, используя массивы. Вывести результаты на консоль в формате слово=повторы

public class TaskA2 {

    private static String[] w={};
    private static int[] count={};

    public static void main(String[] args) {


        Pattern pattern= Pattern.compile("[а-яА-яёЁ]+");
        Matcher matcher= pattern.matcher(Poem.text);
        while (matcher.find()) {
            String word=matcher.group();// и это группа без выделения
            int p=pos(word);
            if (p>=0){
                count[p]++;
            }
            else {
                int last=w.length;
                w= Arrays.copyOf(w, last+1);
                w[last]=word;
                count=Arrays.copyOf(count, last+1);
                count[last]=1;
            }
        }
        for (int i = 0; i < w.length; i++) {
            System.out.println(w[i]+"="+count[i]);
        }
    }

    private static int pos(String word){
        for (int i = 0; i < w.length; i++) {
            if (w[i].equals(word))
                return i;
        }
        return -1;
    }

}
