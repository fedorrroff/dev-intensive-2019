package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String? {
	var sourceString = this.trimEnd()

	if (sourceString.length >= value) {
		sourceString = sourceString.substring(0, value).trimEnd() + "..."
	}

	return sourceString
}