package code
package comet

import scala.xml._

import net.liftweb.http._
  import js.jquery.JqJsCmds._
import net.liftweb.util._
  import Helpers._

import code.lib._

import java.util._

case object UpdateTime

class TimeUpdaterComet extends CometActor {
  Schedule(() => this ! UpdateTime, 1 second)

  def render = PassThru

  override def lowPriority = {
    case UpdateTime =>
      lazy val date: Date = DependencyFactory.time.vend 
      partialUpdate(JqSetHtml("time", Text(date.toString)))
      Schedule(() => this ! UpdateTime, 1 second)
  }
}
