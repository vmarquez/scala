/* NSC -- new Scala compiler
 * Copyright 2005-2013 LAMP/EPFL
 * @author  Martin Odersky
 */

package scala.reflect.internal
package util

import scala.collection.mutable
import scala.reflect.NameTransformer

class FreshNameCreator {
  protected var counter = 0
  protected val counters = mutable.HashMap[String, Int]() withDefaultValue 0

  /**
   * Create a fresh name with the given prefix. It is guaranteed
   * that the returned name has never been returned by a previous
   * call to this function (provided the prefix does not end in a digit).
   */
  def newName(prefix: String): String = {
    val safePrefix = NameTransformer.encode(prefix)
    counters(safePrefix) += 1
    safePrefix + counters(safePrefix)
  }

  def newName(): String = {
    counter += 1
    "$" + counter + "$"
  }
}
