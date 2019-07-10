package cn.com;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {


    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger(0);
        RestTemplate restTemplate = new RestTemplate();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);

        long startTime = System.currentTimeMillis();

        for(int f =0 ;f<100; f++){
            int o = i.getAndIncrement();
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " start ----" + o + "time=" + (System.currentTimeMillis()-startTime) + "  ----activeCount=" + executor.getActiveCount() + "  ----- max=" + executor.getMaximumPoolSize());
                String dResp = restTemplate.getForObject("http://127.0.0.1:12011/txlcn?value=" + o, String.class);
                System.out.println(Thread.currentThread().getName() + " end ----" + o + "time=" +  (System.currentTimeMillis()-startTime) +  " ---- " + dResp);
            });
        }

//        while(true) {
//            while (executor.getMaximumPoolSize() > executor.getActiveCount()) {
//                int o = i.getAndIncrement();
//                Boolean ex = true;
//                executor.submit(() -> {
//                    System.out.println(Thread.currentThread().getName() + " start ----" + o + "  ----activeCount=" + executor.getActiveCount() + "  ----- max=" + executor.getMaximumPoolSize());
//                    String dResp = restTemplate.getForObject("http://127.0.0.1:12011/txlcn?value=" + o, String.class);
//                    System.out.println(Thread.currentThread().getName() + " end ----" + o + " ---- " + dResp);
//                });
//            }
//            Thread.sleep(4000L);
//        }

    }

}
