package cn.edu.hust.conf;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件管理类
 * 主要的功能:从特定的properties文件中读取相应的key/value
 */
public class ConfigurationManager {
    private static Properties prop=new  Properties();

    /**
     * 通过静态代码块加载配置文件
     */
    static{
        try
        {
            //通过类的加载器读取配置文件
            InputStream is=ConfigurationManager.class.getClassLoader().getResourceAsStream("conf.properties");
            //加载配置文件
            prop.load(is);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过指定的key获取value
     * @param key
     * @return
     */
    public static String getProperty(String key)
    {
        return prop.getProperty(key);
    }
}
