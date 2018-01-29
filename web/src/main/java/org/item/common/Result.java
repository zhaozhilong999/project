package org.item.common;

import java.io.Serializable;

/**
 * @author ab047423
 *
 */
public class Result implements Serializable {
    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 5905715228490291386L;
    /**
     * @fields status 状态信息，正确返回OK，否则返回 ERROR，如果返回ERROR则需要填写Message信息
     */
    private Status status;
    /**
     * @fields record 消息
     */  
    private String message;
    /**
     * @fields record 数据对象
     */
    private Object data;
    
    public Result() {
        super();
    }

    /**
     * @description
     * @param status 状态
     * @param message 消息
     */
    public Result(Status status, String message,Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 结果类型信息
     * 
     */
    public static enum Status {
        //
        OK, ERROR,EXCEPTION,NOUSER
    }
    
    /**
     * 添加出现异常结果信息
     * 
     * @param record
     */
    public void addException(String message) {
        this.message = message;
        this.status = Status.EXCEPTION;
    }

    /**
     * 添加成功结果信息
     * 
     * @param record
     */
    public void addOK(String message) {
        this.message = message;
        this.status = Status.OK;
    }

    /**
     * 添加错误消息
     * 
     * @param message
     */
    public void addError(String message) {
        this.message = message;
        this.status = Status.ERROR;
    }
    
    public void addNoUser(String message) {
        this.message = message;
        this.status = Status.NOUSER;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

    
   

}
