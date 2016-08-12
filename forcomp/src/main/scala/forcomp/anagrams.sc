object Anagrams {

  "adsd".map(c => "c" + c)

  val map1 = Map('a' -> 1, 'b' -> 2, 'c' -> 1)
  val map2 = Map('a' -> 5, 'b' -> 1, 'd' -> 2)

  val mapA = map1.withDefaultValue(0)

  map1 ++ (for {
    (k, v) <- map2
  } yield (k -> (v + map1.getOrElse(k, 0))))

  1 to 5
  1 until 5

  val x11 = List(1, 2, 3, 4, 5)
  val x111 = List(1, 2, 3)

  x111.foldLeft(0)((a, b) => (a + b))


  val x = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
  val y = List(('r', 1))

  x.map(xEntry => y.find(yEntry => yEntry._1 == xEntry._1) match {
    case None => xEntry
    case Some(entry) => {
      if (xEntry._2 - entry._2 <= 0) null
      else entry._1 -> (xEntry._2 - entry._2)
    }
  }).filter(_ != null)

  x.apply(1)
  x.updated(1, ('c', 2))

  val x2 = x.toMap
  val y2 = y.toMap

  x2.apply('r')
  x2.updated('r', 555)

  x.map(xEntry => y.find(yEntry => yEntry._1 == xEntry._1) match {
    case None => xEntry
    case Some(entry) => {
      if (xEntry._2 - entry._2 <= 0) null
      else entry._1 -> (xEntry._2 - entry._2)
    }
  }).filter(_ != null)

  y.toMap.foldLeft(x.toMap)((result, yEntry) => result.updated(yEntry._1, result.apply(yEntry._1) - yEntry._2))
    .toList.filter(_._2 > 0)

}