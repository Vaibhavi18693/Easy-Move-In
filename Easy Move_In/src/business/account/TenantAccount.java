/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.account;

import business.FeedBackForm;
import business.Tenant.Tenant;
import business.event.Event;

/**
 *
 * @author Hardik
 */
public class TenantAccount extends Account {

    private Tenant tenant;
    private Event.Decision decision;
    private boolean feedBackSubmitted;
    private FeedBackForm providedFeedback;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Event.Decision getDecision() {
        return decision;
    }

    public void setDecision(Event.Decision decision) {
        this.decision = decision;
    }
     public boolean isFeedBackSubmitted() {
        return feedBackSubmitted;
    }

    public void setFeedBackSubmitted(boolean feedBackSubmitted) {
        this.feedBackSubmitted = feedBackSubmitted;
    }

    public FeedBackForm getProvidedFeedback() {
        return providedFeedback;
    }

    public void setProvidedFeedback(FeedBackForm providedFeedback) {
        this.providedFeedback = providedFeedback;
    }

}
