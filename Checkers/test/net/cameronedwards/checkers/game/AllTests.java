package net.cameronedwards.checkers.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, CheckerTest.class, LocationTest.class,
		SquareTest.class, MoveTest.class, GameControllerTest.class })
public class AllTests {

}
