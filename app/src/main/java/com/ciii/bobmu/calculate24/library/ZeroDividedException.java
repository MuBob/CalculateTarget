package com.ciii.bobmu.calculate24.library;

public class ZeroDividedException extends Exception {
    public ZeroDividedException() {
        super("异常错误：0不能被整除！");
    }
}
