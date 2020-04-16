package net.troja.kata.numbermirror;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberMirrorTest {
	private NumberMirror classToTest = new NumberMirror();

	@ParameterizedTest
	@CsvSource({ "0, 10, 3", "6, 25, 2", "10, 100, 4", "100, 1000, 12", "100, 10000, 32", "1000, 84398, 56",
			"1000, 123456789, 1130", "100000, 12345678900000000, 718650"})
	public void upsideDown(long min, long max, int expected) {
		assertEquals(expected, classToTest.upsideDown(min, max));
	}

	@Test
	public void generateNumbers2() {
		Set<Long> expected = Set.of(11l, 69l, 88l, 96l);
		assertThat(classToTest.generateNumbers(2, false)).hasSameElementsAs(expected);
	}

	@Test
	public void generateNumbers3() {
		Set<Long> expected = Set.of(101l, 111l, 181l, 609l, 619l, 689l, 808l, 818l, 888l, 906l, 916l, 986l);
		assertThat(classToTest.generateNumbers(3, false)).hasSameElementsAs(expected);

		System.out.println(classToTest.generateNumbers(4, false));
	}

	@Test
	public void generateNumbers5() {
		Set<Long> result = classToTest.generateNumbers(5, false);
		System.out.println(result);
		assertThat(result).hasSize(60);
	}
}