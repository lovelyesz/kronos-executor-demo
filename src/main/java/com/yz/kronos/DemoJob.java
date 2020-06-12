package com.yz.kronos;

import com.yz.kronos.execute.IsolatedJavaJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * @author shanchong
 * @date 2020-06-12
 **/
@Slf4j
@Component
public class DemoJob implements IsolatedJavaJob {

    @Override
    public void execute(final Integer integer, final Integer integer1, final String s) throws Exception {
        IntStream.range(0,10000).forEach(i->{
            log.info("demo job data : {}",i);
        });
    }

}
