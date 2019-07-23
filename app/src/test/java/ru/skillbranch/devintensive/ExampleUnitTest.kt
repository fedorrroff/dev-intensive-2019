package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.*
import ru.skillbranch.devintensive.models.Bender
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_user() {
        val user = User.makeUser("")
        print(Utils.toInitials("kst", "Frd"))
        val user2 = user.copy(lastVisit = Date())
        print(user2.lastVisit?.format("HH:mm"))
        val user3 = user.copy(lastVisit = Date().add(-10, TimeUnits.HOUR))
        print(user3.lastVisit?.format("HH:mm"))
    }

	@Test
	fun test_translit() {
		val delimeter = "_"
		val name = ""
		val leters: Map<String, String> = mapOf("а" to "a",
									"б" to "b",
									"в" to "v",
									"г" to "g",
									"д" to "d",
									"е" to "e",
									"ё" to "e",
									"ж" to "zh",
									"з" to "z",
									"и" to "i",
									"й" to "i",
									"к" to "k",
									"л" to "l",
									"м" to "m",
									"н" to "n",
									"о" to "o",
									"п" to "p",
									"р" to "r",
									"с" to "s",
									"т" to "t",
									"у" to "u",
									"ф" to "f",
									"х" to "h",
									"ц" to "c",
									"ч" to "ch",
									"ш" to "sh",
									"щ" to "sh'",
									"ъ" to "",
									"ы" to "i",
									"ь" to "",
									"э" to "e",
									"ю" to "yu",
									"я" to "ya",
									" " to delimeter
									)

		var newName = name.trim()
		for (i in newName){

			if (i.isUpperCase()) newName = newName.replace(i.toString(), leters.getOrDefault(i.toString().toLowerCase(), i.toString()).capitalize())
			else {
				newName = newName.replace(i.toString(), leters.getOrDefault(i.toString(), i.toString()))
			}

		}
//		newName = newName.replaceAfter(leters.getOrDefault(delimeter), )

		print(newName)
	}

	@Test
	fun tst_trans(){
		print(Utils.transliteration("Amazing Петр"))
	}

	@Test
	fun test_humanize() {

		assertEquals("2 часа назад" ,Date().add(-2, TimeUnits.HOUR).humanizeDiff()) //2 часа назад
		assertEquals("5 дней назад" ,Date().add(-5, TimeUnits.DAY).humanizeDiff()) //5 дней назад
		assertEquals("через 2 минуты" ,Date().add(2, TimeUnits.MINUTE).humanizeDiff())//через 2 минуты
		assertEquals("через 7 дней" ,Date().add(7, TimeUnits.DAY).humanizeDiff()) //через 7 дней
		assertEquals("более года назад" ,Date().add(-400, TimeUnits.DAY).humanizeDiff()) //более года назад
		assertEquals("более чем через год" ,Date().add(400, TimeUnits.DAY).humanizeDiff()) //более чем через год
		assertEquals("несколько секунд назад" ,Date().add(-2, TimeUnits.SECOND).humanizeDiff()) //2 часа назад
		assertEquals("только что" ,Date().add(1, TimeUnits.SECOND).humanizeDiff()) //2 часа назад

	}

	@Test
	fun test_plural() {
		assertEquals("1 минуту", TimeUnits.MINUTE.plural(1))
		assertEquals("2 минуты", TimeUnits.MINUTE.plural(2))
		assertEquals("1 час", TimeUnits.HOUR.plural(1))
		assertEquals("0 часов", TimeUnits.HOUR.plural(0))
	}
	@Test
	fun test_builder() {
		val user1 = User.Builder().id("1")
			.firstName("Alex")
			.lastName("Ferguson")
			.avatar(null)
			.respect(100)
			.build()

		print("""
			${user1.id}
			${user1.firstName}
			${user1.lastName}
			${user1.avatar}
			${user1.respect}
		""".trimIndent())
	}

	@Test
	fun test_truncate(){
		val str = "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»" //Bender Bending R..."
		assertEquals("Bender Bending R...", str.truncate())
		val str2 = "Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»"
		assertEquals("Bender Bending...", str2.truncate(15))
		val str1 = "A     "
		assertEquals("A", str1.truncate(3))
	}

	@Test
	fun test_equal() {
		val answer = "Bender"
		val bender = Bender()
//		print(bender.validation(answer))
		print(bender.listenAnswer(answer))
	}
}
