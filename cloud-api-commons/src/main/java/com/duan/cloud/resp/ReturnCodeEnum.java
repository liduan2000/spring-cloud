package com.duan.cloud.resp;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ReturnCodeEnum {
    /**
     * Operation failure
     **/
    RC999("999", "Operation failure"),
    /**
     * Success
     **/
    RC200("200", "Success"),
    /**
     * Service degradation
     **/
    RC201("201", "The service is enabled for degradation protection, please try again later!"),
    /**
     * Hot spot parameter current limiting
     **/
    RC202("202", "Hot spot parameter is limited, please try again later!"),
    /**
     * System rules are not met
     **/
    RC203("203", "System rules do not meet the requirements, please try again later!"),
    /**
     * Authorization rule failed
     **/
    RC204("204", "Authorization rule failed, please try again later!"),
    /**
     * access denied
     **/
    RC403("403", "No access, please contact the administrator to grant permission."),
    RC401("401", "Exception when anonymous users access unauthorized resources."),
    RC404("404", "Exception not found on page 404"),
    /**
     * Service anomaly
     **/
    RC500("500", "System exception, please try again later!"),
    RC375("375", "Abnormal mathematical operation, please try again later!"),

    INVALID_TOKEN("2001", "Access token is illegal"),
    ACCESS_DENIED("2003", "You don't have permission to access this resource"),
    CLIENT_AUTHENTICATION_FAILED("1001", "Client authentication failed"),
    USERNAME_OR_PASSWORD_ERROR("1002", "User name or password error"),
    BUSINESS_ERROR("1004", "Business logic exception"),
    UNSUPPORTED_GRANT_TYPE("1003", "Unsupported authentication mode");

    private final String code;

    private final String message;

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    //V1 - Traditional
    public static ReturnCodeEnum getReturnCodeEnum(String code) {
        for (ReturnCodeEnum element : ReturnCodeEnum.values()) {
            if (element.getCode().equalsIgnoreCase(code)) {
                return element;
            }
        }
        return null;
    }

    //V2 - Stream
    public static ReturnCodeEnum getReturnCodeEnumV2(String code) {
        return Arrays.stream(ReturnCodeEnum.values()).filter(x -> x.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }
}
