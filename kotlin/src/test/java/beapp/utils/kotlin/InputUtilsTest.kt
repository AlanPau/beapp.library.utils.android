package beapp.utils.kotlin

import beapp.utils.kotlin.InputUtils.formatPhone
import beapp.utils.kotlin.InputUtils.validateEmail
import beapp.utils.kotlin.InputUtils.validatePhoneWithWhiteSpaces
import beapp.utils.kotlin.InputUtils.validateSmsCode
import org.junit.Assert.assertEquals
import org.junit.Test

class InputUtilsTest {

	@Test
	fun `test validate phone with white space`() {
		// Given
		val phone = "06 00 00 00 01"
		val phoneWithoutWhiteSpace = "0600000001"
		val randomString = "azertyuiop"
		// When
		val phoneIsOK = validatePhoneWithWhiteSpaces(phone)
		val phoneIsKO = validatePhoneWithWhiteSpaces(phoneWithoutWhiteSpace)
		val notAPhone = validatePhoneWithWhiteSpaces(randomString)
		// Then
		assertEquals(true, phoneIsOK)
		assertEquals(false, notAPhone)
		assertEquals(false, phoneIsKO)
	}

	@Test
	fun `test validate mail`() {
		// Given
		val mail = "test@test.com"
		val incorrectMail = "test.com"
		// When
		val mailOK = validateEmail(mail)
		val mailKO = validateEmail(incorrectMail)
		// Then
		assertEquals(true, mailOK)
		assertEquals(false, mailKO)
	}

	@Test
	fun `test validate sms code`() {
		// Given
		val code = "0000"
		val wrongCode = "test"
		// When
		val codeOK = validateSmsCode(code, 4)
		val codeKO = validateSmsCode(wrongCode, 4)
		// Then
		assertEquals(true, codeOK)
		assertEquals(false, codeKO)
	}

	@Test
	fun `test format phone`() {
		// Given
		val phoneNumber = "0609090909"
		val wrongCode = "0809 jdzd"
		// When
		val codeOK = formatPhone(phoneNumber)
		val codeKO = formatPhone(wrongCode)
		// Then
		assertEquals("06 09 09 09 09", codeOK)
		assertEquals(null, codeKO)
	}
}