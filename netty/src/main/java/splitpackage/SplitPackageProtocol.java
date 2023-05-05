package splitpackage;

/**
 * @ClassName SplitPackageProtocol
 * @Description 协议
 * @Author zhangyuchenb
 * @Date 2023/5/5 15:42
 * @Version 1.0
 */
public class SplitPackageProtocol {
    //这个长度是content转换成字节数组的长度
    private int length;
    private String content;

    public int getLength() {
        return length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.length = content.getBytes().length;
        this.content = content;
    }
}
