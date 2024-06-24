package beapp.utils.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test


class StringExtTest {

	@Test
	fun `test capitalize string`() {
		// Given
		val res = "hello friends"
		// When
		val resCapitalized = res.capitalize()
		// Then
		assertEquals("Hello friends", resCapitalized)
	}

	@Test
	fun `test remove accent`() {
		// Given
		val res = "Héllo frïênds"
		// When
		val resWithoutAccents = res.removeAccents()
		// Then
		assertEquals("Hello friends", resWithoutAccents)
	}

	@Test
	fun `test clear white space`() {
		// Given
		val stringWithSpace = "Hello friend"
		// When
		val stringWithoutSpace = stringWithSpace.clearWhiteSpace()
		// Then
		assertEquals("Hellofriend", stringWithoutSpace)
	}

	@Test
	fun `test string is numeric`() {
		// Given
		val stringWithLowercase = "432"
		val stringNotNumeric = "HI 32 BUDDY"
		// When
		val isNumeric = stringWithLowercase.isNumeric()
		val isNotNumeric = stringNotNumeric.isNumeric()
		// Then
		assertEquals(true, isNumeric)
		assertEquals(false, isNotNumeric)
	}

	@Test
	fun `test string has a lowercase`() {
		// Given
		val stringWithLowercase = "he"
		val stringWithoutLowercase = "HI BUDDY"
		// When
		val stringHasALowercase = stringWithLowercase.hasALowercase()
		val stringHasNotALowercase = stringWithoutLowercase.hasALowercase()
		// Then
		assertEquals(true, stringHasALowercase)
		assertEquals(false, stringHasNotALowercase)
	}

	@Test
	fun `test string has a number`() {
		// Given
		val stringWithNumber = "hello fri3nd"
		val stringWithoutNumber = "HI buddy"
		// When
		val stringHasANumber = stringWithNumber.hasANumber()
		val stringHasNotANumber = stringWithoutNumber.hasANumber()
		// Then
		assertEquals(true, stringHasANumber)
		assertEquals(false, stringHasNotANumber)
	}

	@Test
	fun `test string has no space`() {
		// Given
		val stringWithSpace = "hello friend"
		val stringWithoutSpace = "Hibuddy"
		// When
		val stringHasSpace = stringWithSpace.hasNoSpace()
		val stringHasNotSpace = stringWithoutSpace.hasNoSpace()
		// Then
		assertEquals(false, stringHasSpace)
		assertEquals(true, stringHasNotSpace)
	}

	@Test
	fun `test string has a capital char`() {
		// Given
		val stringWithCapital = "Hello friend"
		val stringWithoutCapital = "bye bye"
		// When
		val stringHasCapital = stringWithCapital.hasACapital()
		val stringHasNotCapital = stringWithoutCapital.hasACapital()
		// Then
		assertEquals(true, stringHasCapital)
		assertEquals(false, stringHasNotCapital)
	}
}