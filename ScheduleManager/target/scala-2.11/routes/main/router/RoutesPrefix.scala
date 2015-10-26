
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/fgamba/workspace/sis-experiments/sis_grupo04/ScheduleManager/conf/routes
// @DATE:Mon Oct 26 14:21:00 COT 2015


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
