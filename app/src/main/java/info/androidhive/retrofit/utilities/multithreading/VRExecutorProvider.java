package info.androidhive.retrofit.utilities.multithreading;

/**
 * Created by kalpesh on 17/10/2016.
 */

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * manages background thread executors and such.
 *
 * priorities are currently not all working.
 *
 * Created by florian on 24/06/14.
 */
public class VRExecutorProvider {

    private static HandlerThread mLowPriorityHandlerThread=new HandlerThread("lowPriorityHandlerThread");
    private static Handler mLowPriorityHandler=null;
    private static Executor mLowPriorityExecutor=null;
    private static HandlerThread mMediumPriorityHandlerThread=new HandlerThread("mediumPriorityHandlerThread");
    private static Handler mMediumPriorityHandler=null;
    private static Executor mMediumPriorityExecutor=null;

    public static Looper getLowPriorityLooper() {
        if(!mLowPriorityHandlerThread.isAlive()) {
            mLowPriorityHandlerThread.start();
            mLowPriorityHandlerThread.setPriority(Thread.MIN_PRIORITY);
        }
        return mLowPriorityHandlerThread.getLooper();
    }

    public static Handler getLowPriorityHandler() {
        if(mLowPriorityHandler==null) {
            mLowPriorityHandler=new Handler(getLowPriorityLooper());
        }
        return mLowPriorityHandler;
    }

    /**
     * low priority executor. maximum of one thread a time. use it for long-running background stuff where you don't care when something is executed.
     * @return
     */
    public static Executor getLowPriorityExecutor() {
        if(mLowPriorityExecutor==null) {
            mLowPriorityExecutor=new ThreadPoolExecutor(1,1,5,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        }
        return mLowPriorityExecutor;
    }


    public static Looper getMediumPriorityLooper() {
        if(!mMediumPriorityHandlerThread.isAlive()) {
            mMediumPriorityHandlerThread.start();
            mMediumPriorityHandlerThread.setPriority(Thread.NORM_PRIORITY);
        }
        return mMediumPriorityHandlerThread.getLooper();
    }

    public static Handler getMediumPriorityHandler() {
        if(mMediumPriorityHandler==null) {
            mMediumPriorityHandler=new Handler(getMediumPriorityLooper());
        }
        return mMediumPriorityHandler;
    }


    /**
     * medium priority executor. maximum of two threads a time.
     * @return
     */
    public static Executor getMediumPriorityExecutor() {
        if(mMediumPriorityExecutor==null) {
            mMediumPriorityExecutor=new ThreadPoolExecutor(1,2,15,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        }
        return mMediumPriorityExecutor;
    }



}
