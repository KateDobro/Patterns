package org.itstep.pps2701.task2;

/**
 * Created by dk on 17.05.17.
 */
public class GrantStateView extends AbstractGrantState {
    public GrantStateView(Grant grant) {
        super(grant);
    }

    @Override
    public boolean checkStateChange(AbstractGrantState toState) {
        boolean result = false;

        if ((toState instanceof GrantStateHold)
                || (toState instanceof GrantStateWithdraw)
                || (toState instanceof GrantStateAccept)
                || (toState instanceof GrantStateReject)
           )
            result = true;

        return result;
    }

    @Override
    public void toView() {
        System.err.println("Рассмотрение невозможно");

    }

    @Override
    public void toHold() {
        AbstractGrantState newState=new GrantStateHold(this.grant);
        grant.setState(newState);
    }

    @Override
    public void toAccept() {
        AbstractGrantState newState=new GrantStateAccept(this.grant);
        grant.setState(newState);
    }

    @Override
    public void toReject() {
        AbstractGrantState newState=new GrantStateReject(this.grant);
        grant.setState(newState);
    }

    @Override
    public void toWithdraw() {
        AbstractGrantState newState=new GrantStateWithdraw(this.grant);
        grant.setState(newState);
    }
}
