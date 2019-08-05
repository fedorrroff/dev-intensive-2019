package ru.skillbranch.devintensive.models

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.RequiresApi
import ru.skillbranch.devintensive.utils.Utils

@TargetApi(Build.VERSION_CODES.N)
data class Profile(

	val firstName: String,
	val lastName: String,
	val about: String,
	val repository: String,
	val rating: Int = 0,
	val respect: Int = 0
) {

	private val rank: String = "Junior Android Developer"
	private var nickName: String = createNickName()

	fun toMap(): Map<String, Any> = mapOf(
		"nickName" to nickName,
		"rank" to rank,
		"respect" to respect,
		"rating" to rating,
		"firstName" to firstName,
		"lastName" to lastName,
		"about" to about,
		"repository" to repository
	)

	@RequiresApi(Build.VERSION_CODES.N)
	fun createNickName(): String = Utils.transliteration("$firstName $lastName", "_")

}