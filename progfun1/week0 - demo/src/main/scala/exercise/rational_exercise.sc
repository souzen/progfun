import exercise.Rational

val x2 = new Rational(1, 2)
x2.numer
x2.denom

val y2 = new Rational(2, 3)

x2.add(y2)



val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)

x.sub(y).sub(z)
y.add(y)
x.less(y)
x.max(y)
x.neg

//val strange = new Rational(1, 0)
//strange.add(strange)

new Rational(2)

x - y - z
y + y
x < y
x max y
-x
