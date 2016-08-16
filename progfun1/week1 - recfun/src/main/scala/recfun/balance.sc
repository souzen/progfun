val step1 = "asd(asasd(asdasd)asdsd)as".dropWhile((c: Char) => c != '(')
val step2 = step1.tail.reverse.dropWhile((c: Char) => c != ')')
val step3 = step2.tail.reverse.dropWhile((c: Char) => c != '(')
val step4 = step3.tail.reverse.dropWhile((c: Char) => c != ')')
val step5 = step4.tail.reverse.dropWhile((c: Char) => c != '(')


val s1 = "(if (zero? x) max (/ 1 x))".filter((c: Char) => c == '(' || c == ')')
val s2 = s1.tail.dropRight(1)
val s3 = s2.tail.dropRight(1)


"asdlk".toList ::: "(abc)".toList
'a' :: "(abc)".toList