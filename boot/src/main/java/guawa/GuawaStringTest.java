package guawa;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @ClassName guawaTest
 * @Description guawa字符串测试类
 * @Author zhangyuchenb
 * @Date 2023/5/4 11:04
 * @Version 1.0
 */
public class GuawaStringTest {

    private static final Joiner joiner1 = Joiner.on(" ").skipNulls();
    private static final Splitter splitter1 = Splitter.on(",");
    //筛选出自动trim
    private static final Splitter splitter2 = Splitter.on(",").trimResults();
    //过滤掉空字符串
    private static final Splitter splitter3 = Splitter.on(",").trimResults().omitEmptyStrings();

    public static void main(String[] args) {
        //测试连接器
//        String joinStr = joiner1.join(Lists.newArrayList("liming", "danny", "jenny"));
//        System.out.println(joinStr);

        //测试分割器
        Iterable<String> split = splitter3.split("  ,  abc ,cd,   ef,gi  ,  ,j  k ,  ");
        for(String msg :split){
            System.out.println(msg);
        }
    }
}
