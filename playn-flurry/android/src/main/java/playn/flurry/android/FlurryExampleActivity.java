package playn.flurry.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import playn.flurry.core.FlurryExample;

public class FlurryExampleActivity extends GameActivity {

  @Override
  public void main(){
    PlayN.run(new FlurryExample());
  }
}
