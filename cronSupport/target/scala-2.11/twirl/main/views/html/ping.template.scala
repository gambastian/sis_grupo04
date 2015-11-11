
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object ping_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class ping extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.19*/("""

"""),format.raw/*3.1*/("""<p>Mensaje: """),_display_(/*3.14*/message),format.raw/*3.21*/("""</p>"""))
      }
    }
  }

  def render(message:String): play.twirl.api.HtmlFormat.Appendable = apply(message)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (message) => apply(message)

  def ref: this.type = this

}


}

/**/
object ping extends ping_Scope0.ping
              /*
                  -- GENERATED --
                  DATE: Wed Nov 11 00:41:28 COT 2015
                  SOURCE: /Users/Ger/Documents/Ger Repos/sis_grupo04/cronSupport/app/views/ping.scala.html
                  HASH: 2ee9d7b4669930bd75b4a494fe05391a9dca41f7
                  MATRIX: 743->1|855->18|883->20|922->33|949->40
                  LINES: 27->1|32->1|34->3|34->3|34->3
                  -- GENERATED --
              */
          