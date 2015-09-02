package edu.xmu.hwb.util;

/**
 * Created by pmsg863 on 14-5-25.
 */
public class RequestAck {

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public RequestAck(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public RequestAck setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
        return this;
    }

    Object returnObject;
    String returnCode;
    String returnMessage = " un define ";
}
