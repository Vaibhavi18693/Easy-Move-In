/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.work;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class WorkQueue {

    private List<WorkRequest> workQueue;

    public WorkQueue() {
        workQueue = new ArrayList<>();
    }

    public List<WorkRequest> getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(List<WorkRequest> workQueue) {
        this.workQueue = workQueue;
    }
}
