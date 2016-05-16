package com.change.exception;

import org.springframework.boot.ExitCodeGenerator;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午4:55
 */
public class ExitException extends RuntimeException implements ExitCodeGenerator {


    @Override
    public int getExitCode() {
        return 10;
    }
}
