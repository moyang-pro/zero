package com.moyang.zero.common.util.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: moyang
 * @ClassName: ThreadPoolManager
 * @Date: 2022/2/9 22:04
 * @Description: 线程池管理
 * @Version: V1.0
 **/
public class ThreadPoolManager {

	private static final ThreadPoolManager INSTANCE = new ThreadPoolManager();

	public static ThreadPoolManager getInstance() {
		return INSTANCE;
	}

	private final ThreadPoolExecutor executor;

	private ThreadPoolManager() {
		/*
		  给corePoolSize赋值：当前设备可用处理器核心数*2 + 1,能够让cpu的效率得到最大程度执行（有研究论证的）
		 */
		int corePoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
		//虽然maximumPoolSize用不到，但是需要赋值，否则报错
		//corePoolSize 当某个核心任务执行完毕，会依次从缓冲队列中取出等待任务，
		// maximumPoolSize,先corePoolSize,然后new LinkedBlockingQueue<Runnable>(),然后maximumPoolSize,但是它的数量是包含了corePoolSize的，
		// keepAliveTime 表示的是maximumPoolSize当中等待任务的存活时间
		TimeUnit unit = TimeUnit.HOURS;
		long keepAliveTime = 1;
		executor = new ThreadPoolExecutor(
				corePoolSize,
				corePoolSize,
				keepAliveTime,
				unit,
				//缓冲队列，用于存放等待任务，Linked的先进先出
				new LinkedBlockingQueue<Runnable>(),
				//创建线程的工厂
				Executors.defaultThreadFactory(),
				//用来对超出maximumPoolSize的任务的处理策略
				new ThreadPoolExecutor.AbortPolicy()
		);
	}

	/**
	 * 执行任务
	 */
	public void execute(Runnable runnable) {
		if (runnable == null) {
			return;
		}
		executor.execute(runnable);
	}

	/**
	 * 从线程池中移除任务
	 */
	public void remove(Runnable runnable) {
		if (runnable == null) {
			return;
		}
		executor.remove(runnable);
	}
}
