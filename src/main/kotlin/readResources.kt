import java.io.File

fun resourceAsString(filename: String): String {
    return File(filename).readText()
}

fun resourceAsList(filename: String): List<String> {
    return File(filename).readLines()
}