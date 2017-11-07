package android.view.surfaceview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback{

  private SurfaceHolder mHolder;
  private volatile boolean mIsDrawing;
  public Path mPath;
  public Paint mPaint;
  private ExecutorService mSingle = Executors.newSingleThreadScheduledExecutor();



  public SurfaceViewTemplate(Context context) {
    this(context, null, 0);
  }

  public SurfaceViewTemplate(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPath = new Path();
    mPaint = new Paint();
    mPaint.setColor(Color.BLUE);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeWidth(50);

    final SurfaceHolder.Callback callback = this;

    mHolder = getHolder();
    mHolder.addCallback(callback);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    mIsDrawing = true;
//    postInvalidate();
//    thread = new Thread(this);
//    thread.start();


  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//    holder.setFormat(format);
//    holder.setFixedSize(width, height);

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    mIsDrawing = false;
  }


  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int x = (int) event.getX();
    int y = (int) event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mPath.moveTo(x, y);
        break;
      case MotionEvent.ACTION_MOVE:
      case MotionEvent.ACTION_UP:
        mPath.lineTo(x, y);
        postDraw();
        break;
    }
    return true;
  }

  private void postDraw() {
    mSingle.submit(new Runnable() {
      @Override
      public void run() {
        drawLine();
      }
    });
  }

  private void drawLine() {
    Canvas canvas = null;
    try {
      canvas = mHolder.lockCanvas();
      //draw something
      canvas.drawColor(Color.WHITE);
      canvas.drawPath(mPath, mPaint);
    } finally {
      if (canvas != null) {
        mHolder.unlockCanvasAndPost(canvas);
      }
    }
  }

  private void drawBack() {
    Canvas canvas = null;
    try {
      canvas = mHolder.lockCanvas();
      //draw something
      canvas.drawColor(Color.WHITE);
    } finally {
      if (canvas != null) {
        mHolder.unlockCanvasAndPost(canvas);
      }
    }
  }


  private int x, y;

  private void drawSin() {
    Canvas canvas = null;
    try {
      canvas = mHolder.lockCanvas();
      //draw something
      canvas.drawColor(Color.WHITE);
      canvas.drawPath(mPath, mPaint);
    } finally {
      if (canvas != null) {
        mHolder.unlockCanvasAndPost(canvas);
      }
    }
    x += 1;
    y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
    mPath.lineTo(x, y);
  }

}
