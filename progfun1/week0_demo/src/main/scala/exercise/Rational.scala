package exercise

/**
  * Created by sosnickl on 13.07.2016.
  */
class Rational(x: Int, y: Int) {
  require(y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private val g = gcd(x, y)

  def numer = x / g

  def denom = y / g

  def neg: Rational = new Rational(-numer, denom)

  def add(that: Rational) =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  def sub(that: Rational) = add(that.neg)

  def less(that: Rational) = this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) = if (this.less(that)) that else this

  def unary_- : Rational = neg

  def +(that: Rational) = add(that)

  def -(that: Rational) = sub(that)

  def <(that: Rational) = less(that)

  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  override def toString: String = numer + "/" + denom
}
