
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
import models.mongo.AllergyMongo

class index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[AllergyMongo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(allergies : List[AllergyMongo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.34*/("""

"""),format.raw/*4.1*/("""<h1>Allergias</h1>

"""),_display_(/*6.2*/for(allergy <- allergies) yield /*6.27*/ {_display_(Seq[Any](format.raw/*6.29*/("""
  		"""),format.raw/*7.5*/("""<p>"""),_display_(/*7.9*/allergy/*7.16*/.name),format.raw/*7.21*/("""</p>
""")))}))
      }
    }
  }

  def render(allergies:List[AllergyMongo]): play.twirl.api.HtmlFormat.Appendable = apply(allergies)

  def f:((List[AllergyMongo]) => play.twirl.api.HtmlFormat.Appendable) = (allergies) => apply(allergies)

  def ref: this.type = this

}


}
}

/**/
object index extends index_Scope0.index_Scope1.index
              /*
                  -- GENERATED --
                  DATE: Mon Nov 09 22:01:14 COT 2015
                  SOURCE: /Users/Ger/Documents/Ger Repos/sis_grupo04/cronSupport/app/views/index.scala.html
                  HASH: 9452940ed39ec7d68d057916c76195b7989f84b0
                  MATRIX: 818->35|945->67|973->69|1019->90|1059->115|1098->117|1129->122|1158->126|1173->133|1198->138
                  LINES: 30->2|35->2|37->4|39->6|39->6|39->6|40->7|40->7|40->7|40->7
                  -- GENERATED --
              */
          