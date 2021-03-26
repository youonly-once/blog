package cn.shu.blog.utils;

import java.util.concurrent.*;

/**
 * 线程池工具类
 *
 * @author SXS
 * @since 3/12/2021
 */

public class ExecutorServiceUtil {

    public static ExecutorService getNetImgDownloadThreadPool() {
        return NET_IMG_DOWNLOAD_THREAD_POOL;
    }

    /**
     * 图片下载线程池
     */
    private final static ExecutorService NET_IMG_DOWNLOAD_THREAD_POOL = new ThreadPoolExecutor(
            0,
            Integer.MAX_VALUE,
            0,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());


}
