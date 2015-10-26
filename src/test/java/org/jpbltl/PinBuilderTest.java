package org.jpbltl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Date;

import org.jpbltl.Pin;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PinBuilderTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testWithId() {
		Pin pin = new Pin.Builder().withId("testId").build();
		assertThat(pin.id, is("testId"));
	}

	@Test
	public void testWithTime() {
		final Date now = new Date();
		final long nowLong = now.getTime();
		Pin pin = new Pin.Builder().withId("id is required").withTime(now).build();
		assertThat(pin.time, is(nowLong));
		assertThat(pin.getTimeAsDate(), is(now));
	}

	@Test
	public void testWithDurationInMinutes() {
		Pin pin = new Pin.Builder().withId("id is required").withDurationInMinutes(3).build();
		assertThat(pin.duration, is(3));
	}
	
	@Test
	public void testWithIdNull() {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("\"id\" may not be null");
		new Pin.Builder().withId(null).build();
	}
	
	@Test
	public void testWithIdLengthGreaterThan64() {
		final String id65CharactersLong = "A sixty-five character String; One character longer than allowed.";
		assertThat(id65CharactersLong.length(), is(65));
		
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("\"id\".length() must be <= 64");
		new Pin.Builder().withId(id65CharactersLong).build();
	}

}
