package cn.edu.hust.demo;

/**
 * 单例模式几种实现方式
 */
public class Singleton {

    /**
     * 饿汉模式实现单例模式，线程安全
     */
    /**
    private static Singleton instance=new Singleton();
    private Singleton()
    {

    }
    public static Singleton getInstance()
    {
        return instance;
    }*/

    /**
     * 懒汉模式实现单例模式 线程不安全的写法

    private static Singleton instance=null;
    private Singleton()
    {

    }
    public static Singleton getInstance()
    {
        if(instance==null) instance=new Singleton();
        return instance;
    }*/

    /**
     * 懒汉模式线程安全的写法
     */
    private static Singleton instance=null;
    private Singleton()
    {

    }
    public static Singleton getInstance()
    {
        if(instance==null)
        {
            synchronized (Singleton.class)
            {
                if(instance==null)
                {
                    instance=new Singleton();
                }
            }

        }
        return instance;
    }
}
