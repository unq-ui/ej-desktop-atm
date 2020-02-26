package atm.view

import atm.model.BankAppModel
import org.uqbar.arena.Application

// Start Application
fun main() = MainApplication().start()

class MainApplication : Application() {
    override fun createMainWindow() = MainWindow(this, BankAppModel())
}
