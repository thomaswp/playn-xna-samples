package playn.flurry.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import playn.flurry.core.FlurryExample;

public class FlurryExampleJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed
    JavaPlatform.register(config);
    PlayN.run(new FlurryExample());
  }
}
