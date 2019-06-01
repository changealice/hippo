package com.change;

import com.change.hippo.rpc.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class MyTests {

    private HelloService helloService;

    @Before
    public void setUp() throws Exception {
        helloService = mock(HelloService.class);
    }

    @Test
    public void helloworld() {
        final HelloService.Person value = new HelloService.Person();
        when(helloService.save(any())).thenReturn(value);
    }
}
