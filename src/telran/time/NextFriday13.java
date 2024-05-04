package telran.time;

import java.time.*;

import java.time.temporal.*;


public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate nextFriday13 = LocalDate.from(temporal);
		
		if(!(nextFriday13.getDayOfMonth() == 13 && nextFriday13.getDayOfWeek() == DayOfWeek.FRIDAY)) {
			boolean isDayNotNextFriday13 = true;
			nextFriday13 = nextFriday13.withDayOfMonth(13);
			while(isDayNotNextFriday13) {
				if(nextFriday13.getDayOfWeek() == DayOfWeek.FRIDAY && nextFriday13.isAfter(LocalDate.from(temporal))) isDayNotNextFriday13 = false;
				else {
					nextFriday13 = nextFriday13.plusMonths(1);					
				}
			}
		}
		return nextFriday13;
	}
}