/**
 * Copyright 2011 The PlayN Authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package playn.sample.hello.core;

import static playn.core.PlayN.*;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

public class Pea {
  public static String IMAGE = "images/pea.png";
  private ImageLayer layer;
  private float angle;

  public Pea(final GroupLayer peaLayer, final float x, final float y) {
    Image image = HelloGame.peaImage;
    layer = graphics().createImageLayer(image);

    // Add a callback for when the image loads.
    // This is necessary because we can't use the width/height (to center the
    // image) until after the image has been loaded
    image.addCallback(new ResourceCallback<Image>() {
      @Override
      public void done(Image image) {
        layer.setOrigin(image.width() / 2f, image.height() / 2f);
        layer.setTranslation(x, y);
        peaLayer.add(layer);
      }

      @Override
      public void error(Throwable err) {
        log().error("Error loading image!", err);
      }
    });
  }

  public void update(float delta) {
    angle += delta;
    layer.setRotation(angle);
    layer.setScale((float)Math.sin(angle) + 1.1f);
  }
}
