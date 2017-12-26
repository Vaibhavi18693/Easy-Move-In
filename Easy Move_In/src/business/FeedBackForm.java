/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Shruti
 */
public class FeedBackForm {
    private boolean elecVerySatis;
    private boolean elecSatis;
    private boolean elecUnsure;
    private boolean elecNotSatis;
    
    private boolean sewVerySatis;
    private boolean sewSatis;
    private boolean sewUnsure;
    private boolean sewNotSatis;
    
    private boolean plumVerySatis;
    private boolean plumSatis;
    private boolean plumUnsure;
    private boolean plumNotSatis;
    private boolean transVerySatis;
    private boolean transSatis;
    private boolean transUnsure;
    private boolean transNotSatis;
    private String comments;
    int rating;

    public boolean isElecVerySatis() {
        return elecVerySatis;
    }

    public void setElecVerySatis(boolean elecVerySatis) {
        this.elecVerySatis = elecVerySatis;
    }

    public boolean isElecSatis() {
        return elecSatis;
    }

    public void setElecSatis(boolean elecSatis) {
        this.elecSatis = elecSatis;
    }

    public boolean isElecUnsure() {
        return elecUnsure;
    }

    public void setElecUnsure(boolean elecUnsure) {
        this.elecUnsure = elecUnsure;
    }

    public boolean isElecNotSatis() {
        return elecNotSatis;
    }

    public void setElecNotSatis(boolean elecNotSatis) {
        this.elecNotSatis = elecNotSatis;
    }

    public boolean isSewVerySatis() {
        return sewVerySatis;
    }

    public void setSewVerySatis(boolean sewVerySatis) {
        this.sewVerySatis = sewVerySatis;
    }

    public boolean isSewSatis() {
        return sewSatis;
    }

    public void setSewSatis(boolean sewSatis) {
        this.sewSatis = sewSatis;
    }

    public boolean isSewUnsure() {
        return sewUnsure;
    }

    public void setSewUnsure(boolean sewUnsure) {
        this.sewUnsure = sewUnsure;
    }

    public boolean isSewNotSatis() {
        return sewNotSatis;
    }

    public void setSewNotSatis(boolean sewNotSatis) {
        this.sewNotSatis = sewNotSatis;
    }
    
    

    public boolean isPlumVerySatis() {
        return plumVerySatis;
    }

    public void setPlumVerySatis(boolean plumVerySatis) {
        this.plumVerySatis = plumVerySatis;
    }

    public boolean isPlumSatis() {
        return plumSatis;
    }

    public void setPlumSatis(boolean plumSatis) {
        this.plumSatis = plumSatis;
    }

    public boolean isPlumUnsure() {
        return plumUnsure;
    }

    public void setPlumUnsure(boolean plumUnsure) {
        this.plumUnsure = plumUnsure;
    }

    public boolean isPlumNotSatis() {
        return plumNotSatis;
    }

    public void setPlumNotSatis(boolean plumNotSatis) {
        this.plumNotSatis = plumNotSatis;
    }

    public boolean isTransVerySatis() {
        return transVerySatis;
    }

    public void setTransVerySatis(boolean transVerySatis) {
        this.transVerySatis = transVerySatis;
    }

    public boolean isTransSatis() {
        return transSatis;
    }

    public void setTransSatis(boolean transSatis) {
        this.transSatis = transSatis;
    }

    public boolean isTransUnsure() {
        return transUnsure;
    }

    public void setTransUnsure(boolean transUnsure) {
        this.transUnsure = transUnsure;
    }

    public boolean isTransNotSatis() {
        return transNotSatis;
    }

    public void setTransNotSatis(boolean transNotSatis) {
        this.transNotSatis = transNotSatis;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    
    
}
