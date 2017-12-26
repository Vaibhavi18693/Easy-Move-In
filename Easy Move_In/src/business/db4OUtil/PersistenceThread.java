/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.db4OUtil;

import business.EcoSystem;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hardik
 */
public class PersistenceThread extends Thread{

    private EcoSystem ecoSystem;
    private DB4OUtil dB4OUtil;

    public PersistenceThread(EcoSystem ecoSystem) {
        this.ecoSystem = ecoSystem;
        dB4OUtil = DB4OUtil.getInstance();
    }
    
    @Override
    public void run() {
        while(true) {
            try {
                sleep(10000);
                dB4OUtil.storeSystem(ecoSystem);
            } catch (InterruptedException ex) {
                Logger.getLogger(PersistenceThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
