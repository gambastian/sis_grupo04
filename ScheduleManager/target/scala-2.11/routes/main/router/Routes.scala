
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/fgamba/workspace/sis-experiments/sis_grupo04/ScheduleManager/conf/routes
// @DATE:Mon Oct 26 14:21:00 COT 2015

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  ScheduleController_1: javax.inject.Provider[controllers.ScheduleController],
  // @LINE:10
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    ScheduleController_1: javax.inject.Provider[controllers.ScheduleController],
    // @LINE:10
    Assets_0: controllers.Assets
  ) = this(errorHandler, ScheduleController_1, Assets_0, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, ScheduleController_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """doctor/$doctorId<[^/]+>/appointments""", """@controllers.ScheduleController@.getDoctorAppointments(doctorId:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_ScheduleController_getDoctorAppointments0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("doctor/"), DynamicPart("doctorId", """[^/]+""",true), StaticPart("/appointments")))
  )
  private[this] lazy val controllers_ScheduleController_getDoctorAppointments0_invoker = createInvoker(
    ScheduleController_1.get.getDoctorAppointments(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ScheduleController",
      "getDoctorAppointments",
      Seq(classOf[String]),
      "GET",
      """ Home page
GET     /                           controllers.Application.index()""",
      this.prefix + """doctor/$doctorId<[^/]+>/appointments"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/$file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_ScheduleController_getDoctorAppointments0_route(params) =>
      call(params.fromPath[String]("doctorId", None)) { (doctorId) =>
        controllers_ScheduleController_getDoctorAppointments0_invoker.call(ScheduleController_1.get.getDoctorAppointments(doctorId))
      }
  
    // @LINE:10
    case controllers_Assets_versioned1_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_0.versioned(path, file))
      }
  }
}