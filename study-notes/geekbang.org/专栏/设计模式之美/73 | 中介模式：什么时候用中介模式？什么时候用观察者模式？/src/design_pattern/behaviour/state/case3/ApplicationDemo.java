package design_pattern.behaviour.state.case3;

import design_pattern.behaviour.state.case1.MarioStateMachine;
import design_pattern.behaviour.state.case1.State;

public class ApplicationDemo {
    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }
}
