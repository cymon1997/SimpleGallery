package id.ac.ui.cs.mobileprogramming.ajiimawanomi.simplegallery.ui;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SimpleSurfaceView extends GLSurfaceView {
    private final SimpleRenderer renderer;

    private class SimpleRenderer implements GLSurfaceView.Renderer {

        private Triangle mTriangle;

        private final float[] mMVPMatrix = new float[16];
        private final float[] mProjectionMatrix = new float[16];
        private final float[] mViewMatrix = new float[16];

        private class Triangle {
            private SimpleRenderer renderer;
            private FloatBuffer vertexBuffer;

            private final int COORDS_PER_VERTEX = 3;
            private float triangleCoords[] = {   // CCW order
                    0.0f,  0.622008459f, 0.0f,  // top
                    -0.5f, -0.311004243f, 0.0f, // bottom left
                    0.5f, -0.311004243f, 0.0f   // bottom right
            };

            private float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };

            private final String vertexShaderCode =
                    "attribute vec4 vPosition;" +
                            "void main() {" +
                            "  gl_Position = vPosition;" +
                            "}";

            private final String fragmentShaderCode =
                    "precision mediump float;" +
                            "uniform vec4 vColor;" +
                            "void main() {" +
                            "  gl_FragColor = vColor;" +
                            "}";

            private final int mProgram;

            private int mPositionHandle;
            private int mColorHandle;

            private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
            private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

            public Triangle(SimpleRenderer renderer) {
                this.renderer = renderer;

                ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
                bb.order(ByteOrder.nativeOrder());
                vertexBuffer = bb.asFloatBuffer();
                vertexBuffer.put(triangleCoords);
                vertexBuffer.position(0);

                int vertexShader = this.renderer.loadShader(GLES20.GL_VERTEX_SHADER,
                        vertexShaderCode);
                int fragmentShader = this.renderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                        fragmentShaderCode);

                // create empty OpenGL ES Program
                mProgram = GLES20.glCreateProgram();

                // add the vertex shader to program
                GLES20.glAttachShader(mProgram, vertexShader);

                // add the fragment shader to program
                GLES20.glAttachShader(mProgram, fragmentShader);

                // creates OpenGL ES program executables
                GLES20.glLinkProgram(mProgram);
            }

            public void draw() {
                // Add program to OpenGL ES environment
                GLES20.glUseProgram(mProgram);

                // get handle to vertex shader's vPosition member
                mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

                // Enable a handle to the triangle vertices
                GLES20.glEnableVertexAttribArray(mPositionHandle);

                // Prepare the triangle coordinate data
                GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                        GLES20.GL_FLOAT, false,
                        vertexStride, vertexBuffer);

                // get handle to fragment shader's vColor member
                mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

                // Set color for drawing the triangle
                GLES20.glUniform4fv(mColorHandle, 1, color, 0);

                // Draw the triangle
                GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

                // Disable vertex array
                GLES20.glDisableVertexAttribArray(mPositionHandle);
            }
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(74.0f/255f, 20.0f/255f, 140.0f/255f, 1.0f);
            mTriangle = new Triangle(this);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            mTriangle.draw();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);

            float ratio = (float) width / height;
            Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
        }

        private int loadShader(int type, String shaderCode) {
            int shader = GLES20.glCreateShader(type);
            GLES20.glShaderSource(shader, shaderCode);
            GLES20.glCompileShader(shader);
            return shader;
        }
    }

    public SimpleSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        renderer = new SimpleRenderer();
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
