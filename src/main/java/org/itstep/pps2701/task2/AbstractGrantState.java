package org.itstep.pps2701.task2;

/**
 * Created by dk on 17.05.17.
 * Задача 2. Поведенческие паттерны - паттерн Состояние (State)
 Заказ на получение гранта на обучение может находиться в нескольких состояниях:
 • создан - подан на рассмотрение
 • рассматривается
 • отложен
 • подтвержден
 • отклонен
 • отозван
 Определить логику изменения состояний и разработать модель системы выдачи
 грантов на обучение
 */
public abstract class AbstractGrantState {
    protected Grant grant;

    public AbstractGrantState(Grant grant) {
        this.grant = grant;
    }

    protected void setGrant(Grant grant) {
        this.grant = grant;
    }

    abstract public boolean checkStateChange(AbstractGrantState toState);

    abstract public void toView();      // рассмотрение
    abstract public void toHold();      // отложен
    abstract public void toAccept();    // подтвержден
    abstract public void toReject();    // отклонен
    abstract public void toWithdraw();  // отозван

}
