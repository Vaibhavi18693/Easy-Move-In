/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.work;

import business.account.Account;

/**
 *
 * @author Hardik
 */
public class WorkRequest {
    private Account sender;
    private Account receiver;
    private String status;
    private String requestType;
    private int id;
    private static int count = 1;

    public WorkRequest() {
        id = count++;
    }

    public int getId() {
        return id;
    }
    
    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
    
}
