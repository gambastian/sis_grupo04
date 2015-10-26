
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/fgamba/workspace/sis-experiments/sis_grupo04/ScheduleManager/conf/routes
// @DATE:Mon Oct 26 14:21:00 COT 2015

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:7
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:7
  class ReverseScheduleController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def getDoctorAppointments: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ScheduleController.getDoctorAppointments",
      """
        function(doctorId) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "doctor/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("doctorId", encodeURIComponent(doctorId)) + "/appointments"})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
        }
      """
    )
  
  }


}