/**
 * Copyright 2010 The PlayN Authors
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

package playn.sample.gldemo.core;

import static playn.core.PlayN.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import playn.core.Game;
import playn.core.PlayN;
import playn.core.gl.GL20;

/**
 * A simple demo for obtaining and using a GL ES 2.0 context in PlayN, based on 
 * http://developer.android.com/resources/tutorials/opengl/opengl-es20.html
 * 
 * The PlayN GL ES 2.0 context does not work in GWT Development mode. 
 * For alternatives, see 
 * http://code.google.com/p/playn/wiki/GameDebuggingOptions
 */
public class GLDemo implements Game {
  private final String VERTEX_SHADER_CODE = 
      "attribute vec4 vPosition; \n" + 
      "void main(){              \n" + 
      "  gl_Position = vPosition; \n" + 
      "}                         \n";

  private final String FRAGMENT_SHADER_CODE = 
      "#ifdef GL_ES\n" +
      "precision highp float;\n" +
      "#endif\n" +
      "void main(){              \n" +
      "  gl_FragColor = vec4 (0.64, 0.77, 0.22, 1.0); \n" +
      "}                         \n";

  private GL20 gl;
  private FloatBuffer triangleVB;

  private int programHandle;
  private int positionHandle;
  private float time;
  private float background;

  @Override
  public void init() {
    //  Window.alert("init start");
    PlayN.log().info("init start");
    gl = graphics().gl20();

    // Set up GL
    
    int vertexShader = loadShader(GL20.GL_VERTEX_SHADER, VERTEX_SHADER_CODE);
    int fragmentShader = loadShader(GL20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);

    programHandle = gl.glCreateProgram(); // create empty OpenGL Program
    gl.glAttachShader(programHandle, vertexShader); // add the vertex shader to
                                               // program
    gl.glAttachShader(programHandle, fragmentShader); // add the fragment shader to
                                                 // program
    gl.glLinkProgram(programHandle); // creates OpenGL program executables

    // get handle to the vertex shader's vPosition member
    positionHandle = gl.glGetAttribLocation(programHandle, "vPosition");

    checkGlError("init");

    // Set up the triangle array (X, Y, Z, ...)
    float triangleCoords[] = {
        -0.5f, -0.25f, 0, 0.5f, -0.25f, 0, 0.0f, 0.56f, 0 };

    // initialize vertex Buffer for triangle  (# of coordinate values * 4 bytes per float)
    ByteBuffer vbb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
    
    // use the device hardware's native byte order
    vbb.order(ByteOrder.nativeOrder());
    
    // create a floating point buffer from the ByteBuffer
    triangleVB = vbb.asFloatBuffer();
    
    // add the coordinates to the FloatBuffer
    triangleVB.put(triangleCoords);
    
    // set the buffer to read the first coordinate
    triangleVB.position(0); 
  }

  @Override
  public void update(float delta) {
    time += delta;
    // Change the background shade, depending on the elapsed time.
    background = (float) ((Math.sin(time / 1000.f) + 1) / 2);  
  }

  @Override
  public void paint(float alpha) {
    gl.glClearColor(background, background, background, 1.0f);
    gl.glViewport(0, 0, graphics().width(), graphics().height());
    gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    
    gl.glUseProgram(programHandle);

    gl.glVertexAttribPointer(positionHandle, 3, GL20.GL_FLOAT, false, 12, triangleVB);
    gl.glEnableVertexAttribArray(positionHandle);

    // Draw the triangle
    gl.glDrawArrays(GL20.GL_TRIANGLES, 0, 3);
  }

  @Override
  public int updateRate() {
    return 0;
  }

  private int loadShader(int type, String shaderCode) {
    // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
    // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
    int shader = gl.glCreateShader(type);

    // add the source code to the shader and compile it
    gl.glShaderSource(shader, shaderCode);
    gl.glCompileShader(shader);

    return shader;
  }
  
  private void checkGlError(String msg) {
    int err = gl.glGetError();
    if (err != GL20.GL_NO_ERROR) {
      PlayN.log().info("GL Error: " + err + " @ " + msg);
    }
  }
}
