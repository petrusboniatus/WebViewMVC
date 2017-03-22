import com.awesome.business.template.controller.AbstractController
import com.awesome.business.template.controller.IControllerFactory
import com.awesome.business.template.controller.ILocator
import com.awesome.business.template.controller.ViewApplication
import org.junit.Assert
import org.junit.Test

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */

class TestInit {

    @Test
    fun testBasicRun() {
        ViewApplication.startView(
                IControllerFactory { object : AbstractController(it){} },
                ILocator { "view/MainView.html" }
        )
        ViewApplication.exit()
        System.exit(0)
    }

    @Test
    fun testControllerPass() {
        var preController: AbstractController? = null
        val postController = ViewApplication.startView(
                IControllerFactory {
                    preController = object : AbstractController(it){}
                    preController
                },
                ILocator { "view/MainView.html" }
        )
        Assert.assertEquals(preController, postController)
        ViewApplication.exit()
        System.exit(0)
    }
}