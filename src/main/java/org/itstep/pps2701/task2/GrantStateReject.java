package org.itstep.pps2701.task2;

/**
 * Created by dk on 17.05.17.
 */
public class GrantStateReject extends AbstractGrantState {
    public GrantStateReject(Grant grant) {
        super(grant);
    }

    @Override
    public boolean checkStateChange(AbstractGrantState toState) {
        return false;
    }
    @Override
    public void toView() {
        System.err.println("Рассмотрение невозможно");
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
        System.err.println("Отклонение невозможно");
    }

    @Override
    public void toWithdraw() { System.err.println("Отзыв невозможен");

    }
}
