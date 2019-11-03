@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import org.junit.experimental.theories.internal.ParameterizedAssertionError.join
import java.lang.Math.*
import java.util.Collections.nCopies
import kotlin.math.sqrt
import kotlin.collections.arrayListOf as arrayListOf1
import java.util.Locale
import kotlin.math.pow


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var x = 0.0
    return if (v.isEmpty()) {
        0.0
    } else {
        x = v.sum()
        sqrt(x)
    }
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) (list.sum() / list.size) else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var i = 0
    var c = 0
    return if (a.isEmpty() || b.isEmpty()) {
        c
    } else {
        while (i < a.size) {
            c += a[i] * b[i]
            i++
        }
        c
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int =
    p.mapIndexed { index, i -> i * x.toDouble().pow(index.toDouble()) }.sum().toInt()

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var a = n
    val list = arrayListOf1<Int>()
    var x = 2
    while (a > 1) {
        while (a % x == 0) {
            if (a != 2) {
                a /= x
                list.add(x)
            } else {
                list.add(x)
                a /= x
                break
            }
        }
        if (x == 2) x++
        else x += 2
    }
    return list.toList()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString("*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var list = arrayListOf1<Int>()
    var number = n
    do {
        list.add(number % base)
        number /= base
    } while (number > 0)
    return list.reversed().toList()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    // я чет подумал, зачем 2 раза писать одно и то же если можно воспользоваться написанной самим собой реализацией
    var x = convert(n, base)
    var notStandartNum = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    var i = 0
    var finalString = ""
    do {
        finalString += notStandartNum[x[i]].toString()
        i++
    } while (i < x.size)
    return finalString.toLowerCase()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var i = 0
    var finalNum = 0.0
    var digitsRevers = digits.reversed()
    while (i < digits.size) {
        finalNum += digitsRevers[i] * pow(base.toDouble(), i.toDouble())
        i++
    }
    return finalNum.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var x = ""
    var num = arrayListOf1<Int>()
    var i = 0

    while (i < str.length) {
        num.add(
            when (str[i].toString()) {
                "1" -> 1
                "2" -> 2
                "3" -> 3
                "4" -> 4
                "5" -> 5
                "6" -> 6
                "7" -> 7
                "8" -> 8
                "9" -> 9
                "a" -> 10
                "b" -> 11
                "c" -> 12
                "d" -> 13
                "e" -> 14
                "f" -> 15
                "g" -> 16
                "h" -> 17
                "i" -> 18
                "j" -> 19
                "k" -> 20
                "l" -> 21
                "m" -> 22
                "n" -> 23
                "o" -> 24
                "p" -> 25
                "q" -> 26
                "r" -> 27
                "s" -> 28
                "t" -> 29
                "u" -> 30
                "v" -> 31
                "w" -> 32
                "x" -> 33
                "y" -> 34
                "z" -> 35
                else -> 0
            }
        )
        i++
    }
    return decimal(num, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    //не бейте палками, я ещё в колледже решал такую задачку на Java так что реализация с Java библиотеками :)
    //Принцип решения прост, каждую единицу мы представляем в виде "I" и заменяем "IIII" на "IV" и ТД по списку :)
    return join("", nCopies(n, "I") as Collection<Any>?)
        .replace("IIIII", "V")
        .replace("IIII", "IV")
        .replace("VV", "X")
        .replace("VIV", "IX")
        .replace("XXXXX", "L")
        .replace("XXXX", "XL")
        .replace("LL", "C")
        .replace("LXL", "XC")
        .replace("CCCCC", "D")
        .replace("CCCC", "CD")
        .replace("DD", "M")
        .replace("DCD", "CM")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999 999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {

    //Я приношу свои глубочайшие извинения, но читать реализацию через простые List`ы крайне не удобно,
    //я просто оставлю реализацию через map

    var thousands: Map<Int, String> = linkedMapOf(
        1 to "тысяча ", 2 to "тысячи ", 3 to "тысячи ", 4 to "тысячи ", 5 to "тысяч ",
        6 to "тысяч ", 7 to "тысяч ", 8 to "тысяч ", 9 to "тысяч ", 0 to "тысяч "
    )

    var hundreeds: Map<Int, String> = linkedMapOf(
        1 to "сто ", 2 to "двести ", 3 to "триста ", 4 to "четыреста ", 5 to "пятьсот ",
        6 to "шестьсот ", 7 to "семьсот ", 8 to "восемьсот ", 9 to "девятьсот ", 0 to ""
    )
    var tens: Map<Int, String> = linkedMapOf(
        1 to "десять ", 2 to "двадцать ", 3 to "тридцать ", 4 to "сорок ", 5 to "пятьдесят ",
        6 to "шестьдесят ", 7 to "семьдесят ", 8 to "восемьдесят ", 9 to "девяносто ", 0 to ""
    )
    var justNum: Map<Int, String> = linkedMapOf(
        1 to "одна ", 2 to "две ", 3 to "три ", 4 to "четыре ", 5 to "пять ",
        6 to "шесть ", 7 to "семь ", 8 to "восемь ", 9 to "девять ", 0 to ""
    )
    var notStandartNum: Map<Int, String> = linkedMapOf(
        1 to "одиннадцать ", 2 to "двенадцать ", 3 to "тринадцать ", 4 to "четырнадцать ", 5 to "пятнадцать ",
        6 to "шестнадцать ", 7 to "семнадцать ", 8 to "восемнадцать ", 9 to "девятнадцать ", 0 to "десять "
    )
    var exeptionInJustNum: Map<Int, String> = linkedMapOf(
        1 to "один ", 2 to "два ", 3 to "три ", 4 to "четыре ", 5 to "пять ",
        6 to "шесть ", 7 to "семь ", 8 to "восемь ", 9 to "девять ", 0 to ""
    )

    var x = n.toString()

    if (n.toString().length > 5) {
        if (x[1].toString().toInt() == 1 && x[4].toString().toInt() == 1) {
            return trim(
                hundreeds[x[0].toString().toInt()] +
                        notStandartNum[x[2].toString().toInt()] +
                        "тысяч " +
                        hundreeds[x[3].toString().toInt()] +
                        notStandartNum[x[5].toString().toInt()]
            )
        } else if (x[1].toString().toInt() == 1) {
            return trim(
                hundreeds[x[0].toString().toInt()] +
                        notStandartNum[x[2].toString().toInt()] +
                        "тысяч " +
                        hundreeds[x[3].toString().toInt()] +
                        tens[x[4].toString().toInt()] +
                        exeptionInJustNum[x[5].toString().toInt()]
            )
        } else if (x[4].toString().toInt() == 1) {
            return trim(
                hundreeds[x[0].toString().toInt()] +
                        tens[x[1].toString().toInt()] +
                        justNum[x[2].toString().toInt()] +
                        thousands[x[2].toString().toInt()] +
                        hundreeds[x[3].toString().toInt()] +
                        notStandartNum[x[5].toString().toInt()]
            )
        } else {
            return trim(
                hundreeds[x[0].toString().toInt()] +
                        tens[x[1].toString().toInt()] +
                        justNum[x[2].toString().toInt()] +
                        thousands[x[2].toString().toInt()] +
                        hundreeds[x[3].toString().toInt()] +
                        tens[x[4].toString().toInt()] +
                        exeptionInJustNum[x[5].toString().toInt()]
            )
        }
    } else if (n.toString().length > 4) {
        if (x[0].toString().toInt() == 1 && x[3].toString().toInt() == 1) {
            return trim(
                notStandartNum[x[1].toString().toInt()] +
                        "тысяч " +
                        hundreeds[x[2].toString().toInt()] +
                        notStandartNum[x[4].toString().toInt()]
            )
        } else if (x[0].toString().toInt() == 1) {
            return trim(
                notStandartNum[x[1].toString().toInt()] +
                        "тысяч " +
                        hundreeds[x[2].toString().toInt()] +
                        tens[x[3].toString().toInt()] +
                        exeptionInJustNum[x[4].toString().toInt()]
            )
        } else if (x[3].toString().toInt() == 1) {
            return trim(
                tens[x[0].toString().toInt()] +
                        justNum[x[1].toString().toInt()] +
                        thousands[x[1].toString().toInt()] +
                        hundreeds[x[2].toString().toInt()] +
                        notStandartNum[x[4].toString().toInt()]
            )
        } else {
            return trim(
                tens[x[0].toString().toInt()] +
                        justNum[x[1].toString().toInt()] +
                        thousands[x[1].toString().toInt()] +
                        hundreeds[x[2].toString().toInt()] +
                        tens[x[3].toString().toInt()] +
                        exeptionInJustNum[x[4].toString().toInt()]
            )
        }

    } else if (n.toString().length > 3) {
        return if (x[3].toString().toInt() == 1) {
            trim(
                justNum[x[0].toString().toInt()] + thousands[x[0].toString().toInt()] +
                        hundreeds[x[1].toString().toInt()] + notStandartNum[x[3].toString().toInt()]
            )
        } else {
            trim(
                justNum[x[0].toString().toInt()] + thousands[x[0].toString().toInt()] +
                        hundreeds[x[1].toString().toInt()] + tens[x[2].toString().toInt()] +
                        justNum[x[3].toString().toInt()]
            )
        }
    } else if (n.toString().length > 2) {
        return if (x[1].toString().toInt() == 1) {
            trim(hundreeds[x[0].toString().toInt()] + notStandartNum[x[2].toString().toInt()])
        } else {
            trim(hundreeds[x[0].toString().toInt()] + tens[x[1].toString().toInt()] + exeptionInJustNum[x[2].toString().toInt()])
        }

    } else if (n.toString().length > 1) {
        return if (x[0].toString().toInt() == 1) {
            trim(notStandartNum[x[1].toString().toInt()].toString())
        } else {
            trim(tens[x[0].toString().toInt()] + exeptionInJustNum[x[1].toString().toInt()])
        }
    } else if (n.toString().length == 1) {
        return trim(exeptionInJustNum[x.toInt()].toString())
    }
    return ""
}

fun trim(n: String): String = n.substring(0, n.length - 1)
