package playn.sample.gldemo.core;

import static playn.core.PlayN.graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import playn.core.Game;
import playn.core.PlayN;
import playn.core.gl.GL20;

public class GLDemo implements Game {
  private final String vertexShaderCode = 
      "attribute vec4 vPosition; \n" + 
      "void main(){              \n" + 
      " gl_Position = vPosition; \n" + 
      "}                         \n";

  private final String fragmentShaderCode = 
      "#ifdef GL_ES\n" +
      "precision highp float;\n" +
      "#endif\n" +
      "void main(){              \n" +
      " gl_FragColor = vec4 (0.64, 0.77, 0.22, 1.0); \n" +
      "}                         \n";

  private GL20 gl;
  private FloatBuffer triangleVB;

  private int mProgram;
  private int maPositionHandle;

  @Override
  public void init() {
    //  Window.alert("init start");
    PlayN.log().info("init start");
    gl = graphics().gl20();

    // Set up GL
    
    int vertexShader = loadShader(GL20.GL_VERTEX_SHADER, vertexShaderCode);
    int fragmentShader = loadShader(GL20.GL_FRAGMENT_SHADER, fragmentShaderCode);

    mProgram = gl.glCreateProgram(); // create empty OpenGL Program
    gl.glAttachShader(mProgram, vertexShader); // add the vertex shader to
                                               // program
    gl.glAttachShader(mProgram, fragmentShader); // add the fragment shader to
                                                 // program
    gl.glLinkProgram(mProgram); // creates OpenGL program executables

    // get handle to the vertex shader's vPosition member
    maPositionHandle = gl.glGetAttribLocation(mProgram, "vPosition");
    
    
    
    // Set up the triangle array
    
    float triangleCoords[] = {
        // X, Y, Z
        -0.5f, -0.25f, 0, 0.5f, -0.25f, 0, 0.0f, 0.56f, 0 };

    // initialize vertex Buffer for triangle
    ByteBuffer vbb = ByteBuffer.allocateDirect(
    // (# of coordinate values * 4 bytes per float)
        triangleCoords.length * 4);
    vbb.order(ByteOrder.nativeOrder());// use the device hardware's native byte
                                       // order
    triangleVB = vbb.asFloatBuffer(); // create a floating point buffer from the
                                      // ByteBuffer
    triangleVB.put(triangleCoords); // add the coordinates to the FloatBuffer
    triangleVB.position(0); // set the buffer to read the first coordinate

    checkGlError("init");
    
    PlayN.log().info("init end");
  }

  @Override
  public void update(float delta) {
    PlayN.log().info("update; delta: " + delta);
  }

  @Override
  public void paint(float alpha) {
    PlayN.log().info("paint start; alpha: " + alpha);
    
    gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    gl.glViewport(0, 0, graphics().width(), graphics().height());
    gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    
    gl.glUseProgram(mProgram);

    gl.glVertexAttribPointer(maPositionHandle, 3, GL20.GL_FLOAT, false, 12, triangleVB);
    gl.glEnableVertexAttribArray(maPositionHandle);

    // Draw the triangle
    gl.glDrawArrays(GL20.GL_TRIANGLES, 0, 3);
    
    gl.glFlush();
    gl.glFinish();
    
    PlayN.log().info("paint end");
    
    checkGlError("paint");
  }

  @Override
  public int updateRate() {
    // TODO Auto-generated method stub
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
