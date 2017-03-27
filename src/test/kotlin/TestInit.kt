import com.awesome.business.template.controller.*
import org.junit.Assert
import org.junit.Test

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */

class TestInit {

    @Test
    fun testIO() {
        val viewHandler = ViewHandlerImpl()
        val vista = viewHandler.loadView("view/TestView.html")
        val box = Array(1) { "old" }

        vista.addObjectOnJS("output", box)

        viewHandler.show(vista)

        vista.runOnJS("output[0] = 'test'")

        Thread.sleep(100)
        Assert.assertEquals("test", box[0])

        viewHandler.close()
    }
}