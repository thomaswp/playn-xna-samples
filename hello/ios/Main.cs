using System;
using MonoTouch.Foundation;
using MonoTouch.UIKit;

using playn.ios;
using playn.core;
using playn.sample.hello.core;

namespace playn.sample.hello.ios {

  [Register ("AppDelegate")]
  public partial class AppDelegate : UIApplicationDelegate {
    public override bool FinishedLaunching (UIApplication app, NSDictionary options) {
      var p = IOSPlatform.register(app);
      p.assets().setPathPrefix("assets");
      PlayN.run(new HelloGame());
      return true;
    }
  }

  public class Application {
    static void Main (string[] args) {
      UIApplication.Main (args, null, "AppDelegate");
    }
  }
}
