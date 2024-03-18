package com.code.custom_exception;

import org.springframework.stereotype.Component;

@Component
public class ControllerException  extends RuntimeException{

    private static long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;

    public ControllerException(){
        
    }

    public ControllerException( String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        ControllerException.serialVersionUID = serialVersionUID;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
