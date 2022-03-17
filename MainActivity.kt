package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.abs
import java.util.*

/* 목표 숫자와 타이머 숫자 사이의 차가 가장 적게 나는 게임 만들기*/


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //연동된 상태로 id 바꾸면 알아서 바뀜
        var timerTask: Timer? = null // ? = null은 nullable로 설정하는거임. 뭐 null일 수 있다는 거겠지.
        var isRunning = false
        // 초기화 하고
        var sec : Int = 0 // var도 변수 선언. 왜 다르게 할까?
        val tv: TextView = findViewById(R.id.tv_random) // 빨강 처리됐던 거는 alt + enter 다음 import widget
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val btn: Button = findViewById(R.id.btn_kor) // 회색이 뜬다면 아직 이 함수를 사용 안해서

        // 버튼 눌렀을 때 빼고 그냥 바로 나오게 바꿈. (btn.setonclicklistner 어쩌고 뺌)
        val random_box = Random()
        val num = random_box.nextInt(1001)
        tv.text = ((num.toFloat())/100).toString()

        btn.setOnClickListener {
            isRunning = !isRunning // 이제 돌리겠다. / 돌리다 누르면 멈추겠다
            if (isRunning == true){
                // period는 필수. ms랑 같은 뜻.
                // 추가하도록 학
                    // period = 1000 -> 10해서 소수점 둘째 자리까지 보이게 함.
                timerTask = kotlin.concurrent.timer(period = 10) {
                    sec++
                    runOnUiThread {
                        // 밑에 쪽에 있는 숫자를 바꿔줄거니까 tv -> tv_t
                        tv_t.text = (sec.toFloat()/100).toString() // sec가 int형이라서 string 자료형으로 바꿔줌
                    }
                }
            }else {
                timerTask?.cancel()
                // 정지하면서 계산하기
                val point = abs(sec-num).toFloat()/100 // 시간차 계산!
                tv_p.text = point.toString()
            }
        }
    }
}