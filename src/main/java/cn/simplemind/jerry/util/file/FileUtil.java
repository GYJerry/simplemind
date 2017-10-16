package cn.simplemind.jerry.util.file;

/**
 * 文件处理，包含文件读取、文件写入等
 * @author wuyingdui
 * @date   2017年5月26日 上午12:42:20
 */
public class FileUtil {
    /**
     * 限制该类的静态方法只能通过类名调用，不能进行实例化
     * @throws Exception
     */
    private FileUtil() throws Exception {
        throw new Exception("please do not initialiize me!");
    }
}
