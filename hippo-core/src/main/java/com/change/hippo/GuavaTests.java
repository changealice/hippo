package com.change.hippo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.Map;

/**
 * User: change.long Date: 16/6/17 Time: 上午10:28
 */
public class GuavaTests {



    public static void main(String[] args) {
        //        boolean isNullOrEmpty = Strings.isNullOrEmpty(null);
        //        String repeatResult = Strings.repeat("13482799373", 2);
        //        System.out.println(isNullOrEmpty);
        //        System.out.println(repeatResult);
        //
        //        final String line = "13482799373|13482799373";
        //        long start  =  System.currentTimeMillis();
        //        for (int i = 0, len = 10000 ; i < len; i++) {
        //            String[] result = line.split("\\|");
        //        }
        //        System.out.println("JAVA split:"+(System.currentTimeMillis()-start) + "ms");
        //
        //        start  =  System.currentTimeMillis();
        //        for (int i = 0, len = 10000 ; i < len; i++) {
        //            String[] result = StringUtils.split(line,"|");
        ////            System.out.println(Arrays.toString(result));
        //        }
        //        System.out.println("Apache split:"+(System.currentTimeMillis()-start) + "ms");

        final String line2 = "13482799373|13482799373\n" +
            " 13482799371|13482799371 \n" + "\n" +
            "13482799372|13482799372\n";

        //一次拆分,去trim ,去empty
        Iterable<String> splitResults =
            Splitter.onPattern("\n").trimResults().omitEmptyStrings().split(line2);
        for (String item : splitResults) {
//            System.out.println(item);
        }

        //二次拆分,限制,map结构,所以只能有2列
        Map<String, String> kvs =
            Splitter.onPattern("\n").trimResults().omitEmptyStrings().withKeyValueSeparator("|").split(line2);
        for (Map.Entry<String,String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(),entry.getValue()));
        }

        String joinString = Joiner.on(" ").join(new String[] {"hello", "world"});
        System.out.println(joinString);
    }

}
