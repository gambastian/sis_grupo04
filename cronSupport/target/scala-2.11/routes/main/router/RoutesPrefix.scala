
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Ger/Documents/Ger Repos/sis_grupo04/cronSupport/conf/routes
// @DATE:Wed Nov 11 15:06:49 COT 2015


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
