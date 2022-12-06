package threadTest;

/**
 * 读者和写者问题（mysql 的读锁和写锁）
 * 允许多个读线程同时读一个文件，但是一个写线程写一个文件时不允许任何其他读或写线程访问该文件
 * 当有线程在读时，阻塞写进程，当没有文件在读时，唤醒写进程
 * todo 好像只用sychronize实现不了。。。等我看了书之后再来。应该要用信号量机制实现的。
 */
public class ReaderAndWriter {
    public static Object file = new Object();
    public static Integer RN = 5;
    public static void read() {

    }
}
