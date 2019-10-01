@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import ru.spbstu.kotlin.generate.assume.retry
import java.lang.Math.*
import java.lang.Math.PI
import java.util.ArrayList
import kotlin.math.*
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var counter = 1
    // Не понимаю почему нельзя работать с переменной n
    // Да, переписал решение, через логарифм работает, но возникают прблемы округления
    // Решение которых сложновато для этой задачки :D пойду простым путем.
    var x = n
    while (true) {
        if (abs(x) / 10 > 0) {
            counter++
            x /= 10
        } else {
            return counter
        }

    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var lastNum = 1
    var preLastNum = 1
    var helper = 1
    for (i in 1..n) {
        if (i >= 3) {
            helper = lastNum
            lastNum += preLastNum
            preLastNum = helper
        }
    }
    return lastNum
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var x = m
    var y = n
    while (x != y) {
        if (x > y) {
            x -= y
        } else {
            y -= x
        }
    }
    return (m * n) / x
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var d = 1
    while (true) {
        ++d
        if (n % d == 0)
            return d
    }
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    // вообще упадет если n окажется меьше 1
    // но у вас условие задано что оно больше, по этому проверки не пишу
    var d = n
    while (true) {
        --d
        if (n % d == 0)
            return d
    }
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in (2..max(m, n) + 1)) {
        if (m % i == 0 && n % i == 0) {
            return false
        }
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
//    Error:(184, 15) Kotlin: Overload resolution ambiguity:
//    @HotSpotIntrinsicCandidate public open fun min(p0: Int, p1: Int): Int defined in java.lang.Math
//    @SinceKotlin @InlineOnly public inline fun min(a: Int, b: Int): Int defined in kotlin.math
//    в общем, пришлось это, т.к. функция min(m,n) не работает
    val x = if (m < n) m else n
    for (i in x..max(m, n)) {
        if (sqrt(i.toDouble()) == sqrt(i.toDouble()).toInt().toDouble()) {
            return true
        }
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    // Я так и не понял почему переменная x не доступна для изменения в условии, спасибо великому
    // StackOverflow я нашел объяснение что желательно создавать локальную переменную в таких ситуациях
    var x1 = x
    var counter = 0
    while (x1 != 1) {
        when {
            x1 % 2 == 0 -> {
                x1 /= 2
                counter++
            }
            x1 == 1 -> return 0
            else -> {
                x1 = 3 * x1 + 1
                counter++
            }
        }
    }
    return counter
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {

    val z = x - 2 * PI * truncate(x / (2 * PI))
    var p = z
    var s = z
    var n = 2
    while (abs(p) > eps) {
        p = -p * z * z / (n * (n + 1))
        s += p
        n += 2
    }
    return s
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var p = 0
    var s = 1.0
    var t = 1.0
    val z = x - 2 * PI * truncate(x / (2 * PI))
    // да, пожалуй по поводу двойной точности вам все таки стоит уточнить в самом задании
    while (abs(t / s) > eps) {
        p++
        t = (-t * z * z) / ((2 * p - 1) * (2 * p))
        s += t
    }
    return s
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var stepParam = n
    var b = 0
    while (true) {
        if (stepParam / 10 > 0) {
            b += stepParam % 10
            stepParam /= 10
            b *= 10
        } else {
            b += stepParam % 10
            stepParam /= 10
            break
        }
    }

    return b
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var stepParam = n
    val b = revert(n)
    return b == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var stepParam = n
    var count = 0
    var beNum = 0
    val b = stepParam % 10

    for (i in 0..n) {
        return if (stepParam < 10 && stepParam > -1) {
            false
        } else {
            stepParam /= 10
            if (b != stepParam % 10) {
                true
            } else {
                continue
            }
        }
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 1 4 9 16 25 36 49 64 81 100 121 144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var x: Int = 0
    var counter = 0
    var count = 0
    var i = 1

    if (n == 0) {
        return 0
    }

    while (count < n) {
        x += sqr(i)
        counter = x
        while (x != 0) {
            count++
            x /= 10
        }
        i++
    }
    while (count > n) {
        counter /= 10
        count--
    }
    return counter % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1 1 2 3 5 8 13 21 34 55 89 144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var x: Int = 0
    var counter = 0
    var count = 2
    var i = 1

    var n0 = 1
    var n1 = 1

    if (n == 0) {
        return 0
    } else if (n == 1 || n == 2) {
        return 1
    }

    while (count < n) {

        x = n0 + n1

        counter = x
        while (x != 0) {
            count++
            x /= 10
        }
        n0 = n1
        n1 = counter
    }
    while (count > n) {
        counter /= 10
        count--
    }
    return counter % 10
}
