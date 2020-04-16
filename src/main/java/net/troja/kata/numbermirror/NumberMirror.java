package net.troja.kata.numbermirror;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class NumberMirror {
	private static final Long[] DIGITS_OUTER = {1l, 6l, 8l, 9l};
	private static final Long[] DIGITS_MIDDLE = {0l, 1l, 8l};
	private static final Long[] DIGITS_INNER = {0l, 1l, 6l, 8l, 9l};
	private static final Integer[] DIGIT_INVERT = {0, 1 , 2, 3, 4, 5, 9, 7, 8, 6};

	public long upsideDown(long min, long max) {
		int digitMin = getLength(min);
		int digitMax = getLength(max);
		System.out.println(digitMin + " - " + digitMax);
		long count = 0l;
		if(min%10 != 0 && min > 0) {
			Set<Long> numbers = generateNumbers(digitMin, false);
			count = numbers.parallelStream().filter(n -> n > min).count();
			digitMin++;
		}
		for (int num = digitMin; num < digitMax; num++) {
			if (num == 1) {
				count += 3;
				continue;
			}
			int digits = Math.floorDiv(num, 2);
			int sum = !isEven(num) ? 3 : 1;
			sum = sum * 4;
			digits--;
			if (digits > 0) {
				sum = sum * (int) Math.pow(5, digits);
			}
			count += sum;
		}
		if(max > digitMax * 10) {
			Set<Long> numbers = generateNumbers(digitMax, false);
			count += numbers.parallelStream().filter(n -> n < max).count();
		}
		return count;
	}

	public Set<Long> generateNumbers(int digits, boolean inner) {
		if(digits == 1)
			return Set.of(DIGITS_MIDDLE);
		Long[] toDo = DIGITS_OUTER;
		if(inner)
			toDo = DIGITS_INNER;

		Set<Long> collect = Arrays.stream(toDo).map(d -> d * (long)Math.pow(10, digits - 1) + DIGIT_INVERT[d.intValue()]).collect(Collectors.toSet());
		int rest = digits - 2;
		if (rest > 0) {
			final Set<Long> result = new TreeSet<>();
			Set<Long> innerNumbers = generateNumbers(rest, true);
			innerNumbers.forEach(n -> {
				long number = n * 10;
				collect.forEach(c -> result.add(c + number));
			});
			return result;
		}
		return collect;
	}

	private int getLength(long number) {
		if (number == 0)
			return 1;
		return (int) (Math.log10(number) + 1);
	}

	private boolean isEven(int digits) {
		return digits % 2 == 0;
	}
}
