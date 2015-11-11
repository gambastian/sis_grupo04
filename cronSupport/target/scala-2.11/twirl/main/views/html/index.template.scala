
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object index_Scope0 {
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

     object index_Scope1 {
import models.mongo.Document

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[Document],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(documents : List[Document]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.30*/("""

"""),format.raw/*4.1*/("""<h1>Documentos</h1>

"""),_display_(/*6.2*/for(document <- documents) yield /*6.28*/ {_display_(Seq[Any](format.raw/*6.30*/("""
  		"""),format.raw/*7.5*/("""<p>"""),_display_(/*7.9*/document/*7.17*/.content),format.raw/*7.25*/("""</p>
""")))}))
      }
    }
  }

  def render(documents:List[Document]): play.twirl.api.HtmlFormat.Appendable = apply(documents)

  def f:((List[Document]) => play.twirl.api.HtmlFormat.Appendable) = (documents) => apply(documents)

  def ref: this.type = this

}


}
}

/**/
object index extends index_Scope0.index_Scope1.index
              /*
                  -- GENERATED --
                  DATE: Wed Nov 11 11:55:11 COT 2015
                  SOURCE: /Users/Ger/Documents/Ger Repos/sis_grupo04/cronSupport/app/views/index.scala.html
                  HASH: 210875b8106123dcda87bcd17f3a4264bbc94634
                  MATRIX: 810->31|933->59|961->61|1008->83|1049->109|1088->111|1119->116|1148->120|1164->128|1192->136
                  LINES: 30->2|35->2|37->4|39->6|39->6|39->6|40->7|40->7|40->7|40->7
                  -- GENERATED --
              */
          