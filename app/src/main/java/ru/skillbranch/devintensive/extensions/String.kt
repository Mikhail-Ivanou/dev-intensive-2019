package ru.skillbranch.devintensive.extensions

fun String.truncate(offset: Int = 16): String {
    val trim = trim()
    val suffix = if (trim.length > offset) "..." else ""
    return trim.take(offset).trim().plus(suffix)
}

fun String.stripHtml(): String {
    val tags = Regex("<.*?>")
    val escapes = Regex("&.*?;")
    val spaces = Regex("\\s+")
    return replace(tags, "").replace(escapes, "").replace(spaces, " ")
}
