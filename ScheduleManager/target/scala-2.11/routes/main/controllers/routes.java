
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/fgamba/workspace/sis-experiments/sis_grupo04/ScheduleManager/conf/routes
// @DATE:Mon Oct 26 14:21:00 COT 2015

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseScheduleController ScheduleController = new controllers.ReverseScheduleController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseScheduleController ScheduleController = new controllers.javascript.ReverseScheduleController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
  }

}
