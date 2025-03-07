@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File
import java.io.FileWriter
import java.io.Writer
import java.util.*
import java.util.regex.Matcher


/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> = TODO()


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val out = FileWriter(outputName)
    val file = File(inputName).readLines()
    var outText = mutableListOf<String>()
    var item = 0
    var lenOf = 0
    //Я все таки воздержусь от лямбда выражений, я слишком тупой что бы это отлаживать :(
    val neededLen = if (file.isNotEmpty()) file.maxBy { it.trim().length }!!.trim().length else 0

    for (i in file) {
        if (i.trim() != "") {
            var j = i.trim().split(" ").toMutableList()
            lenOf = j.joinToString("").length
            item = 0
            while (lenOf != neededLen) {
                if (j.size == 1) {
                    break
                }
                if (item == j.size - 1) {
                    item = 0;
                    continue
                }
                j[item] += " "
                lenOf++
                item++
                if (item == j.size) {
                    item = 0
                }
            }
            outText.add(j.joinToString(""))
        } else {
            outText.add(i.trim())
        }
    }
    for (i in outText) {
        out.write(i + "\n")
    }
    out.flush()
    out.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> = TODO()

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * При решении этой и двух следующих задач полезно прочитать статью Википедии "Стек".
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
<body>
<p>
Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
</p>
<p>
Suspendisse <s>et elit in enim tempus iaculis</s>.
</p>
</body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    var read = File(inputName).bufferedReader().readText()
    var write = FileWriter(outputName)

    val regular0 = Regex("\\*")
    val regular1 = Regex("\\*\\*")
    val regular2 = Regex("\\~\\~")
    val regular3 = Regex("\\n\\n")
    val pattern0 = Regex("\\*").toPattern()
    val pattern1 = Regex("\\*\\*").toPattern()
    val pattern2 = Regex("\\~\\~").toPattern()
    val pattern3 = Regex("\\n\\n").toPattern()

    var countI = 0
    var countB = 0
    var countS = 0

    write.write("<html><body><p>")
    read = read.replace("\r", "")
    var matcher: Matcher = pattern3.matcher(read)
    while (matcher.find()) {
        read = read.replaceFirst(
            regular3, "</p><p>"
        )
    }
    matcher = pattern2.matcher(read)
    while (matcher.find()) {
        read = if (countS == 1) {
            countS--
            read.replaceFirst(
                regular2, "</s>"
            )
        } else {
            countS++
            read.replaceFirst(
                regular2, "<s>"
            )
        }
    }

    matcher = pattern1.matcher(read)
    while (matcher.find()) {
        read = if (countB == 1) {
            countB--
            read.replaceFirst(
                regular1, "</b>"
            )
        } else {
            countB++
            read.replaceFirst(
                regular1, "<b>"
            )
        }
    }


    matcher = pattern0.matcher(read)
    while (matcher.find()) {
        read = if (countI == 1) {
            countI--
            read.replaceFirst(
                regular0, "</i>"
            )
        } else {
            countI++
            read.replaceFirst(
                regular0, "<i>"
            )
        }
    }

    read = read.replace("</p><p></p><p>", "</p><p>")

    write.write(read)
    write.write("</p></body></html>")
    write.flush()
    write.close()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
 * Утка по-пекински
 * Утка
 * Соус
 * Салат Оливье
1. Мясо
 * Или колбаса
2. Майонез
3. Картофель
4. Что-то там ещё
 * Помидоры
 * Фрукты
1. Бананы
23. Яблоки
1. Красные
2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
<body>
<ul>
<li>
Утка по-пекински
<ul>
<li>Утка</li>
<li>Соус</li>
</ul>
</li>
<li>
Салат Оливье
<ol>
<li>Мясо
<ul>
<li>
Или колбаса
</li>
</ul>
</li>
<li>Майонез</li>
<li>Картофель</li>
<li>Что-то там ещё</li>
</ol>
</li>
<li>Помидоры</li>
<li>
Фрукты
<ol>
<li>Бананы</li>
<li>
Яблоки
<ol>
<li>Красные</li>
<li>Зелёные</li>
</ol>
</li>
</ol>
</li>
</ul>
</body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    val read = File(inputName).bufferedReader().readText().split("\n")
    val q = Stack<String>()
    val write = FileWriter(outputName)
    var lvl = 0
    var prelvl = -1

    //ну и решеньйице :D
    //напишу небольшую документацию что бы не все так плохо было:
    //по сути всё что я делаю это играю высотой и последовательностью открытых тегов
    //теги хранятся в стеке (LIFO) и в зависимости уровня извлекаются и добавляются
    //к слову об уровнях, я слежу только за предыдущим и текущим уровнем, хранить больше в принципе не требуется,
    //т.к. в случае если мы не возвращаемся на 0 уровень мы должны сгрузить 3 последних тега, в ином случае до самого
    //нижнего
    write.write("<html><body>")
    for (i in read) {
        if (i != "") {
            lvl = 0
            for (j in i) {
                if (j == ' ') {
                    lvl++
                } else {
                    break
                }
            }
            if (i[lvl] == '*' && lvl > prelvl) {
                q.push("<ul>")
                q.push("<li>")
                write.write("<ul><li>${i.replace(Regex("[\\s\\n\\t\\*]|(\\d+\\.+\\s)"), "")}")
                prelvl = lvl
            } else if ((i[lvl].toString() + i[lvl + 1].toString() + i[lvl + 2].toString()).matches("\\d+\\.+\\s".toRegex()) && lvl > prelvl) {
                q.push("<ol>")
                q.push("<li>")
                write.write("<ol><li>${i.replace(Regex("[\\s\\n\\t\\*]|(\\d+\\.+\\s)"), "")}")
                prelvl = lvl
            } else if (lvl < prelvl) {
                if (lvl == 0) {
                    while (q.size != 1) {
                        when {
                            q.peek() == "<ul>" -> {
                                write.write("</ul>")
                                q.pop()
                            }
                            q.peek() == "<ol>" -> {
                                write.write("</ol>")
                                q.pop()
                            }
                            q.peek() == "<li>" -> {
                                write.write("</li>")
                                q.pop()
                            }
                        }
                    }
                } else {
                    for (l in 0..2) {
                        when {
                            q.peek() == "<ul>" -> {
                                write.write("</ul>")
                                q.pop()
                            }
                            q.peek() == "<ol>" -> {
                                write.write("</ol>")
                                q.pop()
                            }
                            q.peek() == "<li>" -> {
                                write.write("</li>")
                                q.pop()
                            }
                        }
                    }
                }
                q.push("<li>")
                write.write("<li>")
                write.write(i.replace(Regex("[\\s\\n\\t\\*]|(\\d+\\.+\\s)"), ""))
                prelvl = lvl
            } else if (lvl == prelvl) {
                when {
                    q.peek() == "<ul>" -> {
                        write.write("</ul>")
                        q.pop()
                    }
                    q.peek() == "<ol>" -> {
                        write.write("</ol>")
                        q.pop()
                    }
                    q.peek() == "<li>" -> {
                        write.write("</li>")
                        q.pop()
                    }
                }
                write.write("<li>${i.replace("[\\s\\n\\t\\*]|(\\d+\\.+\\s)".toRegex(), "")}")
                q.push("<li>")
            }
        }
    }
    while (!q.isEmpty()) {
        while (q.size != 0) {
            when {
                q.peek() == "<ul>" -> {
                    write.write("</ul>")
                    q.pop()
                }
                q.peek() == "<ol>" -> {
                    write.write("</ol>")
                    q.pop()
                }
                q.peek() == "<li>" -> {
                    write.write("</li>")
                    q.pop()
                }
            }
        }
    }
    write.write("</body></html>")
    write.flush()
    write.close()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
19935
 *    111
--------
19935
+ 19935
+19935
--------
2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
235
 *  10
-----
0
+235
-----
2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
19935 | 22
-198     906
----
13
-0
--
135
-132
----
3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    //ТУДУ :D - вынести пдсчет пробелов и проверку чисел в отдельные функции
    val out = FileWriter(outputName)
    var list = mutableListOf<String>()
    var firstC = 0
    var helpStr = ""
    var minusString = ""
    var spaceCount = ""
    out.write(" $lhv | $rhv\n")
    if (lhv >= rhv) {
        for (i in lhv.toString()) {
            if (helpStr == "" || helpStr.trim().toInt() / rhv == 0) {
                helpStr += i
                if (helpStr.length >= 3 && helpStr[0] == '0' && helpStr[1] == '0'
                    || helpStr.length >= 3 && helpStr[0] == '0' && helpStr[1] != '0') {
                    helpStr = helpStr[1].toString() + helpStr[2].toString()
                }

                if (firstC > 0) {
                    for (l in 0..list.last().length - helpStr.length) {
                        spaceCount += " "
                    }
                    list.add(spaceCount + helpStr)
                    spaceCount = ""
                }
            }
            if (helpStr.trim().toInt() / rhv > 0) {
                if (list.size > 0) {
                    if (helpStr[0] != '0') {
                        for (l in 1..list.last().length - (helpStr.length + 1)) {
                            spaceCount += " "
                        }
                    } else {
                        for (l in 0 until list.last().length - helpStr.length) {
                            spaceCount += " "
                        }
                    }
                }
                list.add(spaceCount + "-" + (helpStr.trim().toInt() - helpStr.trim().toInt() % rhv).toString())
                for (j in "-" + (helpStr.trim().toInt() - helpStr.trim().toInt() % rhv).toString()) {
                    minusString += "-"
                }
                list.add(spaceCount + minusString)
                minusString = ""
                spaceCount = ""
                helpStr = (helpStr.trim().toInt() % rhv).toString()
                firstC++
            } else if (helpStr.trim().toInt() / rhv == 0 && firstC > 0) {
                for (l in 0..list.last().length - (helpStr.length + 1)) {
                    spaceCount += " "
                }
                list.add("$spaceCount-0")
                list.add("$spaceCount--")
                spaceCount = ""
            }
        }
        for (l in 0..list.last().length - (helpStr.length + 1)) {
            spaceCount += " "
        }
        if (helpStr == "00"){
            helpStr = "0"
        }
        list.add(spaceCount + helpStr)
        spaceCount = ""

        for (i in 0.."$lhv | ".length - list[0].length) {
            spaceCount += " "
        }
        list[0] = (list[0] + spaceCount + lhv / rhv)
        for (i in list) {
            out.write(i + "\n")
        }
    } else {
        list.add("-0")
        list.add("--")
        for (i in 0.."$lhv | ".length - 2) {
            spaceCount += " "
        }
        list[0] = (list[0] + spaceCount + 0)
        list.add(" $lhv")
        for (i in list) {
            out.write(i + "\n")
        }
    }

    out.flush()
    out.close()
}
