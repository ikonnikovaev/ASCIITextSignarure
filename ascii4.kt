package signature

import java.io.File
import java.util.Scanner;
import java.lang.Math;

class Font {
    val size: Int
    val nLetters: Int
    val letters: Map<Char, Array<String?>>
    val spaceWidth: Int
    val betweenLetters: String

    constructor(pathString: String, spaceWidth: Int, betweenLetters: String) {

        val fontFile = File(pathString)
        val fileScanner = Scanner(fontFile)

        val fontProperties = fileScanner.nextLine().split(" ")
        this.size = fontProperties[0].toInt()
        this.spaceWidth = spaceWidth
        this.betweenLetters = betweenLetters
        this.nLetters = fontProperties[1].toInt()


        this.letters = mutableMapOf()

        for (i in 1..nLetters) {
            val letterProperties = fileScanner.nextLine().split(" ")
            val letter = letterProperties[0][0]
            val width = letterProperties[1].toInt()
            val letterWriting = arrayOfNulls<String>(size)
            for (i in 1..size) {
                letterWriting[i - 1] = padString(fileScanner.nextLine(), width)
            }
            letters[letter] = letterWriting

        }
        letters[' '] = arrayOfNulls<String>(size)
        var space = ""
        for (i in 1..spaceWidth) {
            space += " "
        }
        for (i in 1..size) {
            letters[' ']?.set(i - 1, space)
        }

    }
    fun getLetter(char: Char): Array<String?>? {
        return letters[char]
    }

    fun getString(string: String): Array<String?> {
        var resultLines: Array<String?> = arrayOfNulls(size)
        for (i in 1..size) {
            resultLines[i - 1] = ""
        }

        for (char in string) {
            val letter = getLetter(char)
            for (i in 1..size) {
                resultLines[i - 1] += letter?.get(i - 1) + betweenLetters
            }
        }

        return resultLines

    }
}

fun padString(s: String, w: Int): String {
    if (w <= s.length) {
        return s
    }
    val diff = w - s.length
    var res = s
    val startPaddingLength = diff / 2
    val endPaddingLength = diff - startPaddingLength
    for (i in 1..startPaddingLength) {
        res = " " + res
    }
    for (i in 1..endPaddingLength) {
        res += " "
    }
    return res
}



/*
fun loadFont(pathString: String ):MutableMap<Char, Array<String?>>  {
    val font: MutableMap<Char, Array<String?>> = mutableMapOf()

    val fontFile = File(pathString)
    val fileScanner = Scanner(fontFile)

    val fontProperties = fileScanner.nextLine().split(" ")
    val size = fontProperties[0].toInt()
    val nLetters = fontProperties[1].toInt()

    for (i in 1..nLetters) {
        val letterProperties = fileScanner.nextLine().split(" ")
        val letter = letterProperties[0][0]
        val width = letterProperties[1].toInt()
        val letterWriting = arrayOfNulls<String>(size)
        for (i in 1..size) {
            letterWriting[i - 1] = padString(fileScanner.nextLine(), width)
        }
        font[letter] = letterWriting

    }


    return font

}*/





fun main() {
    val roman = Font("/home/elena/hyperskill/learning/roman.txt", 10, "")
    val medium = Font("/home/elena/hyperskill/learning/medium.txt", 5, "")

    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()

    var nameLines = roman.getString(name)



    var statusLines = medium.getString(status)



    val nameLength = nameLines[0]?.length
    val statusLength = statusLines[0]?.length
    val maxLength = maxOf(nameLength!!, statusLength!!)

    //println("$nameLength $statusLength $maxLength")

    val nEights = maxLength + 4 + 4
    var border = ""
    for (i in 1..nEights) {
        border += "8"
    }

    for (i in 1..nameLines.size) {
        nameLines[i - 1] = "88  " + padString(nameLines[i - 1]!!, maxLength) + "  88"
    }

    for (i in 1..statusLines.size) {
        statusLines[i - 1] = "88  " + padString(statusLines[i - 1]!!, maxLength) + "  88"
    }

    println(border)

    for (i in 1..roman.size) {
        println(nameLines[i - 1])
    }

    for (i in 1..medium.size) {
        println(statusLines[i - 1])
    }

    println(border)

    /*
    val diff = Math.abs(nameLength!! - statusLength!!)
    var startPadding = ""
    var endPadding = ""
    for (i in 1..(diff / 2)) {
        startPadding += " "
    }
    for (i in 1..(diff - diff / 2)) {
        endPadding += " "
    }

    if (nameLength  >= statusLength) {
        for (i in 1..statusLines.size) {
            statusLines[i - 1] = startPadding + statusLines[i - 1] + endPadding
        }

    }
    else {


    }*/

    /*

    var nameLines: Array<String?> = arrayOfNulls(roman.size)
    for (i in 1..roman.size) {
        nameLines[i - 1] = ""
    }

    for (char in name) {
        val letter = roman.get(char)
        for (i in 1 ..roman.size) {
            nameLines[i - 1] += letter?.get(i - 1)
        }
    }

    var statusLines: Array<String?> = arrayOfNulls(medium.size)
    for (i in 1..medium.size) {
        statusLines[i - 1] = ""
    }

    for (char in status) {
        val letter = roman.get(char)
        for (i in 1 ..roman.size) {
            nameLines[i - 1] += letter?.get(i - 1)
        }
    }

    for (i in 1..roman.size) {
        println(nameLines[i - 1])
    }*/


}



