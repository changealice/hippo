package com.change.spring.cloud.service;

import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface Github {
    @RequestLine("GET/repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
}
