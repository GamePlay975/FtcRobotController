package org.firstinspires.ftc.teamcode.actions;

public interface action {
    void startAction();
    void runAction();
    void stopAction();
    boolean isActionComplete();
    boolean isActionPersistent();
}
