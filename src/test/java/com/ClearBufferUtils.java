package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author yunxun
 */
public class ClearBufferUtils {


    public static void clear( final Process process ) {
        // 启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
        /* 主线程 调用waitfor之前 不断处理缓冲区数据 防止死锁 */
        /* 初始化大小10 创建一个固定长度线程池，可控制线程最大并发数，超出的线程会在队列中等待 */
        //ThreadPoolExecutor(线程池核心池大小，线程池的最大线程数)
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor ( 10, 10,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable> ( 1 ),
                new ThreadPoolExecutor.DiscardOldestPolicy () );
        threadPool.execute ( new Runnable () {
            @Override
            public void run() {
                // 获取进程的标准输入流
                try (BufferedReader br1 = new BufferedReader ( new InputStreamReader (process.getInputStream () ) )) {
                    String line1 = null;
                    while ((line1 = br1.readLine ()) != null) {
                        if (line1 != null) {
                            System.out.println ( line1 );
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        } );

        ThreadPoolExecutor threadPoolError = new ThreadPoolExecutor ( 10, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable> ( 1 ),
                new ThreadPoolExecutor.DiscardOldestPolicy () );
        threadPoolError.execute ( new Runnable () {
            @Override
            public void run() {
                // 获取进程的错误流
                try (BufferedReader br2 = new BufferedReader ( new InputStreamReader ( process.getErrorStream () ) );) {
                    String line2 = null;
                    while ((line2 = br2.readLine ()) != null) {
                        if (line2 != null) {
                            System.out.println ( line2 );
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            }
        } );

    }

}