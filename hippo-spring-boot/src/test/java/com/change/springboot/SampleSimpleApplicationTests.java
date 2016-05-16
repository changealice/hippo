package com.change.springboot;

import com.change.SampleSimpleApplication;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午4:58
 */
public class SampleSimpleApplicationTests {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();


    @Test
    public void testDefaultSettings() throws Exception {
        SampleSimpleApplication.main(new String[0]);
        String output = this.outputCapture.toString();
        Assert.assertTrue(output.contains("Hello change"));
    }

    @Test
    public void testCommandLineOverrides() throws Exception {
        SampleSimpleApplication.main(new String[]{"--name=Jim"});
        String output = this.outputCapture.toString();
        Assert.assertTrue(output.contains("Hello Jim"));
    }

    @Test
    public void testCommandLineOverridesWithExceptions() {
        SampleSimpleApplication.main(new String[]{"exitcode"});
    }
}
