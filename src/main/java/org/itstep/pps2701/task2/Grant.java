package org.itstep.pps2701.task2;

/**
 * Created by dk on 17.05.17.
 */
public class Grant {
    private String name = null;
    private String winner = null;
    private int sum = 0;

    private AbstractGrantState state = new GrantStateNew(this);

    public Grant() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public AbstractGrantState getState() {
        return state;
    }

    protected void setState(AbstractGrantState state) {
        if(state!=null)
        {
            this.state.setGrant(null);
            this.state = state;
        }
    }
}
