package org.firstinspires.ftc.teamcode.opModes;

import org.firstinspires.ftc.teamcode.actions.Drive;

public class TestAuto extends BasedAuto{ //can extend one thing, can implement multiple interfaces
    @Override
    public void addActions() {
        actions.add(new Drive(thing,3));
    }
}
