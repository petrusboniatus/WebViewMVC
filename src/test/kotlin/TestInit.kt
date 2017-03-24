import com.awesome.business.template.controller.*
import org.junit.Assert
import org.junit.Test

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */

class TestInit {

    @Test
    fun testBasicRun() {
        val viewHandler = ViewHandlerImpl()
        val vista = viewHandler.loadView("view/MainView.html")

        vista.addObjectOnJS("enviado desde java", "ejemplo")

        viewHandler.show(vista)
        viewHandler.close()
    }
}