package pl.lodz.uni.math.danielg.toothache

import androidx.test.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }
//}

@RunWith(RobolectricTestRunner::class)
class ExampleRoboTest {
    @Test fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("pl.lodz.uni.math.danielg.toothache", appContext.packageName)
    }
}