package beapp.utils.system

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant

class InstantExtTest {

	//private val context = ApplicationProvider.getApplicationContext<Context>()

	@Test
	fun `test instant is today`() {
		// Given
		val today = Instant.now()
		val yesterday = Instant.now().minusMillis( 115741000)
		// When
		val isToday = today.isToday()
		val isYesterday = yesterday.isToday()
		// Then
		assertEquals(true, isToday)
		assertEquals(false, isYesterday)
	}

	@Test
	fun `test format instant`() {
		// Given
		val date = Instant.parse("1980-04-09T15:30:45.123Z")
		// When
		val timeInstant = date.toFormat(DateFormat.TIME)
		val dateInstant = date.toFormat(DateFormat.DATE)
		val dateMinifyMonthInstant = date.toFormat(DateFormat.DATE_MINIFY_MONTH)
		val dateAndTimeInstant = date.toFormat(DateFormat.DATE_AND_TIME)
		val dateCompactInstant = date.toFormat(DateFormat.DATE_COMPACT)
		val dateWithSlashesInstant = date.toFormat(DateFormat.FORMAT_EXTENDED_DATE_WITH_SLASHES)
		// Then
		assertEquals("17h30", timeInstant)
		assertEquals("09 avril 1980", dateInstant)
		assertEquals("09 avr. 1980", dateMinifyMonthInstant)
		assertEquals("09/04/1980 à 17h30", dateAndTimeInstant)
		assertEquals("09/04/80", dateCompactInstant)
		assertEquals("09/04/1980", dateWithSlashesInstant)
	}


	// TODO mock context
	/*
	@Test
	fun `test instant to label`() {
		// Given
		val lessThanOneMinAgoDate = Instant.now().minusMillis(40000) // a l'instant
		val oneMinAgoDate = Instant.now().minusMillis(60000) // il y a 1 minute
		val oneHourAgoDate = Instant.now().minusMillis(3600000) // il y a 1 heure
		val oneDayAgoDate = Instant.now().minusMillis(3000000) // hier
		val date = Instant.parse("1980-04-09T15:30:45.123Z") // all else

		// When
		val lessThanOneMinAgoLabel = lessThanOneMinAgoDate.toLabel(context, DateFormat.TIME)
		val oneMinAgoLabel = oneMinAgoDate.toLabel(context, DateFormat.TIME)
		val oneHourAgoLabel = oneHourAgoDate.toLabel(context, DateFormat.TIME)
		val oneDayAgoLabel = oneDayAgoDate.toLabel(context, DateFormat.TIME)
		val dateLabel = date.toLabel(context, DateFormat.TIME)

		// Then
		assertEquals("À l'instant", lessThanOneMinAgoLabel)
		assertEquals("Il y a 1 minute", oneMinAgoLabel)
		assertEquals("Il y a 1 heure", oneHourAgoLabel)
		assertEquals("Hier", oneDayAgoLabel)
		assertEquals("17h30", dateLabel)
	}
	 */
}