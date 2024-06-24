package beapp.utils.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test


class StringBuilderExtTest {

	@Test
	fun `test replace char seq`() {
		// Given
		val initialString = "hello friends"
		// When
		val result = StringBuilder(initialString).apply {
			replace("hello", "Goodbye")
		}.toString()
		// Then
		assertEquals("Goodbye friends", result)
	}


}