package telran.time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Friday13Range implements Iterable<Temporal> {
    Temporal from;
    Temporal to;
    
	public Friday13Range(Temporal from, Temporal to) {
		super();
		this.from = from;
		this.to = to;
	}
	@Override
	public Iterator<Temporal> iterator() {
		return new FridayIterator();
	}
	private class FridayIterator implements Iterator<Temporal> {
		Temporal currentFriday13;
		FridayIterator(){
			currentFriday13 = from != null ? LocalDate.from(from):null;
			if(currentFriday13 != null) setCurrentFriday13(currentFriday13);
		}
		
		@Override
		public boolean hasNext() {
			return LocalDate.from(currentFriday13).compareTo(LocalDate.from(to)) <= 0;
		}

		@Override
		public Temporal next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Temporal nextFriday13 = currentFriday13;
            setCurrentFriday13(currentFriday13.plus(1, ChronoUnit.DAYS));
            return nextFriday13;
		}
		private void setCurrentFriday13(Temporal temporal) {
			currentFriday13 = new NextFriday13().adjustInto(temporal);
		}
	}
}