package com.change.hippo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * User: change.long@99bill.com
 * Date: 2017/6/12
 * Time: 下午11:07
 */

@SpringBootApplication
open class HippoSpringBootKotlinApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(HippoSpringBootKotlinApplication::class.java, *args)
        }
    }
}