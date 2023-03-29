package com.example.gesture

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.OnDoubleTapListener
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnGestureListener, OnTouchListener {
    lateinit var txv: TextView
    lateinit var gDetector: GestureDetector
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txv = findViewById(R.id.txv)
        txv.setTextColor(Color.parseColor("#eeee00"))
        txv.setBackgroundColor(Color.BLUE)
        txv.setTypeface(
            Typeface.createFromAsset(
                assets,
                "font/ChenYuluoyan-Thin.ttf"
            )
        )
        txv.getBackground().setAlpha(80)  //0~255透明度值

        gDetector = GestureDetector(this, this)   //(Context(目前的應用系統), Listener(目前的監聽事件))
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//        if (txv.text == "手勢辨別") {
//            txv.text = "靜宜之美"
//        } else {
//            txv.text = "手勢辨別"
//        }
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent): Boolean {
        txv.text = "按下"
        return true
    }

    override fun onShowPress(p0: MotionEvent) {
        txv.text = "按下後無後續動作"
    }

    override fun onSingleTapUp(p0: MotionEvent): Boolean {
        txv.text = "短按"
        return true
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        if (distanceY >= 0){
            txv.text = "向上拖曳"
        }
        else{
            txv.text = "向下拖曳"
        }

        return true
    }

    override fun onLongPress(p0: MotionEvent) {
        txv.text = "長按"
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (e1.x <= e2.x){
            txv.text = "往右快滑"
            count++
            if(count>5){count=0}
        }
        else{
            txv.text = "往左快滑"
            count--
            if(count<0){count=5}
        }

        var res:Int = getResources().getIdentifier("pu" + count.toString(),
            "drawable", getPackageName())
        findViewById<LinearLayout>(R.id.bg).setBackgroundResource(res)

        return true
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }
}