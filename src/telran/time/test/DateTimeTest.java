package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.time.*;

class DateTimeTest {

	@Test
	void barMizvaAdjusterTest() {
		LocalDate birthDate = LocalDate.parse("1799-06-06");
		LocalDate expected = LocalDate.of(1812, 6, 6);
		assertEquals(expected, birthDate.with(new BarMizvaAdjuster()));
	}
	@Test
	void nextFriday13Test() {
		Temporal today = LocalDate.now();
		LocalDate expected = LocalDate.of(2024, 9, 13);
		Temporal res = new NextFriday13().adjustInto(today);
		assertEquals(expected, res);
	}
	@Test
	void friday13RangeTest() {
		int index = 0;
		Temporal today = LocalDate.now();
		Temporal endOfTheYear = LocalDate.of(2025, 6, 13);
		Temporal [] expected = {LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13),LocalDate.of(2025, 6, 13)};
		Friday13Range friday13Range = new Friday13Range(today, endOfTheYear);
		Iterator<Temporal> it = friday13Range.iterator();
		while(it.hasNext()) assertEquals(expected[index++], it.next());
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}
	@Test
	void displayCurrentDateTimeTest() {
		displayCurrentDateTime("Canada");
	}
	private void displayCurrentDateTime(String zonePart) {
		 String[] zoneIds = ZoneId.getAvailableZoneIds().toArray(new String[0]);
		 for(String zoneId : zoneIds) {
			 if(zoneId.contains(zonePart)) {
				 ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of(zoneId));
				 System.out.printf("%s <%s>\n",currentDateTime.format(DateTimeFormatter.ofPattern("<yyyy>-<MM>-<dd> <HH:mm>", Locale.forLanguageTag("en"))),zoneId);
			 }
		 }
	}
}