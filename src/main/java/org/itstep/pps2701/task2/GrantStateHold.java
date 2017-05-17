package org.itstep.pps2701.task2;

/**
 * Created by dk on 17.05.17.
 */
public class GrantStateHold extends AbstractGrantState {
    public GrantStateHold(Grant grant) {
        super(grant);
    }

    @Override
    public boolean checkStateChange(AbstractGrantState toState) {
        boolean result = false;

        if ((toState instanceof GrantStateView)
                || (toState instanceof GrantStateWithdraw)
                )
            result = true;

        return result;
    }

    @Override
    public void toView() {
        AbstractGrantState newState=new GrantStateView(this.grant);
        grant.setState(newState);
    }

    @Override
    public void toHold() {
        System.err.println("Отложить грант невозможно");
    }

    @Override
    public void toAccept() {
        System.err.println("Утверждение невозможно");

    }

    @Override
    public void toReject() {
        System.err.println("Отзыв невозможен");
    }

    @Override
    public void toWithdraw() {
        AbstractGrantState newState=new GrantStateWithdraw(this.grant);
        grant.setState(newState);
    }
}
