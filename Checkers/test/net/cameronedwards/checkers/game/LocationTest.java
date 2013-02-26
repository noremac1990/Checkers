package net.cameronedwards.checkers.game;

import static org.junit.Assert.*;

import net.cameronedwards.checkers.game.Location;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testEquals()
	 throws Exception {
		Location location = new Location(2,1);
		
		assertTrue(location.equals(new Location(2,1)));
		
		assertTrue(new Location(1,2).equals(new Location(1,2)));
		
		assertFalse(location.equals(null));
		
		assertFalse(location.equals(new Object()));
	}

	@Test
	public void testHashCode()
	 throws Exception {
		Location l1 = new Location(50, 10);
		Location l2 = new Location(50, 10);
		assertEquals(l1.hashCode(), l2.hashCode());
	}

}
