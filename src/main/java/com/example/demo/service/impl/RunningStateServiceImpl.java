package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.RunningState;
import com.example.demo.executer.CommonExecutor;
import com.example.demo.mapper.RunningStateMapper;
import com.example.demo.service.IRunningStateService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZouJiaLiang
 * @since 2020-12-10
 */
@Service
public class RunningStateServiceImpl extends ServiceImpl<RunningStateMapper, RunningState> implements IRunningStateService {

    public static void main(String[] args) {
        ThreadPoolExecutor test = CommonExecutor.buildThreadFirstExecutor("test");
        test.execute(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        });
        test.execute(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        });
        test.execute(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        });
        test.execute(()->{
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
        });
        //ThreadPoolExecutor test1 = ExecutorManager.getThreadPoolExecutor("test");
        //test1.shutdownNow();
        test.shutdownNow();
    }
}
