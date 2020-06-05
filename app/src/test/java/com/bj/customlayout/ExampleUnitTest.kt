package com.bj.customlayout

import com.bj.customlayout.demo.DemoA
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testIPlus() {
        var i = 0
        var z = 0
        val x = i++
        val y = ++z
        println("output:->$x")
        println("output:->$z")
    }

    @Test
    fun testFor() {
        var i = 0
        val x = arrayOf(10, 10)
        for (i in x.indices) {
            println("output:->$i")
        }
    }

    @Test
    fun textClassIndex() {
        var demoa: DemoA? = DemoA()
        var demoa1 = demoa
        demoa!!.par1 = "hello world"
        demoa1!!.par1 = "hello java"

        println("demoa.par->${demoa.par1}")
        println("demoa1.par->${demoa1.par1}")

        demoa1 = null
        demoa = null

        println("demoa->$demoa\ndemoa1->$demoa1")
    }

    @Test
    fun testTry() {
        var x = 0
        while (true) {
            try {
                x++
                if (x == 100) {
                    throw ArrayIndexOutOfBoundsException("随机的越界异常")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if(x>200){
                break
            }
            println("hello world ->$x")

        }
    }


}
