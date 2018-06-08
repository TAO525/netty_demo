package com.example.demo;

import org.springframework.util.StopWatch;

/**
 * @Author TAO
 * @Date 2018/5/31 15:21
 */
public class TestStopWatch {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("吃饭");
        Thread.sleep(100);
        stopWatch.stop();

        stopWatch.start("睡觉");
        Thread.sleep(200);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
