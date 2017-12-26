/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.account;

import business.employee.Employee;
import business.role.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class AccountDirectory {
    private List<Account> accountDirectory;

    public AccountDirectory() {
        accountDirectory = new ArrayList<>();
    }

    public List<Account> getAccountDirectory() {
        return accountDirectory;
    }

    public void setAccountDirectory(List<Account> accountDirectory) {
        this.accountDirectory = accountDirectory;
    }
    
    public Account addAccount() {
        Account account = new Account();
        accountDirectory.add(account);
        return account;
    }
    
    public TenantAccount addTenantAccount() {
        TenantAccount account = new TenantAccount();
        accountDirectory.add(account);
        return account;
    }
    
    public void removeAccount(Account account) {
        accountDirectory.remove(account);
    }
    
    public Account createAccount(String userName, String password, Employee employee, Role role)
    {
//        RandomPassword hashGenerator = new RandomPassword();
        Account userAccount = new Account();
        userAccount.setUsername(userName);
        userAccount.setPassword(password);
        userAccount.setEmployee(employee);
        userAccount.setRole(role);
//        try {
//            userAccount.setStoredSalt(hashGenerator.getSalt());
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(AccountDirectory.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchProviderException ex) {
//            Logger.getLogger(AccountDirectory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        userAccount.setSecurePassword(hashGenerator.getSecurePassword(userAccount.getPassword(), userAccount.getStoredSalt()));
        accountDirectory.add(userAccount);
        return userAccount;
    }

    public Account authenticateUser(String username, String password){
//        RandomPassword hashGenerator = new RandomPassword();
//        String newHashCode = null;
        for (Account ua : accountDirectory)
            if (ua.getUsername().equals(username) && ua.getPassword().equals(password)){
//                newHashCode = hashGenerator.getSecurePassword(password, ua.getStoredSalt());
//                if (newHashCode.equals(ua.getSecurePassword())) 
                return ua;
            }
        return null;
    }
    
    public boolean checkIfUsernameIsUnique(String userName)
    {
        for(Account account : accountDirectory)
        {
            if(userName.equals(account.getUsername()))
            {
                return false;
            }
        }
        return true;
    }
}
