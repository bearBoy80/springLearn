package com.github.bearboy80.spring.service;

public class DefaultEchoService implements EchoService {
    /**
     * echo message
     *
     * @param message
     */
    public void echo(String message) {
        System.out.println("DefaultEchoService.echo :" + message);
    }
}
