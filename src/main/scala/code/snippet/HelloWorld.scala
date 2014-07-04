package code 
package snippet 

import scala.xml.{NodeSeq, Text}
import net.liftweb.http._
  import SHtml._
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import code.comet._
import Helpers._

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> date.map(_.toString)

  def addCometButton = {
    {
      S.findOrCreateComet[TimeUpdaterComet](Full("awesome")).map { comet =>
        "#add-comet [onclick]" #> ajaxInvoke(() => {
          S.addComet(comet)
        })
      }
    } openOr ClearNodes
  }

  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}

