package design_pattern.behaviour.state.case2;

import design_pattern.behaviour.state.case1.MarioStateMachine;
import design_pattern.behaviour.state.case1.State;

public class ApplicationDemo {
    public static void main(String[] args) {
        design_pattern.behaviour.state.case1.MarioStateMachine mario = new MarioStateMachine();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);
    }
}
