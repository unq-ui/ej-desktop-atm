package atm.view

import atm.model.AccountAppModel
import atm.model.BankAppModel
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.kotlin.extensions.*

class MainWindow(owner: WindowOwner, model: BankAppModel) : SimpleWindow<BankAppModel>(owner, model) {
    override fun addActions(actionsPanel : Panel) {}

    override fun createFormPanel(mainPanel : Panel) {
        title = "Cajero Automático"
        iconImage = atm.ICON

        Label(mainPanel) with { text = "Seleccione la cuenta con la que desea operar" }

        table<AccountAppModel>(mainPanel) with {
            bindItemsTo("accounts")
            bindTo("selectAccount")
            column {
                title = "Tipo de Cuenta"
                fixedSize = 250
                bindContentsTo("type")
            }
            column {
                title = "Disponible"
                fixedSize = 250
                bindContentsTo("balance")
            }
        }

        Label(mainPanel) with { text = "Ingrese el valor que desea extraer" }

        TextBox(mainPanel) with {
            bindTo("input")
            width = 12
            withFilter { event -> event.potentialTextResult.matches(Regex("[0-9]*")) }
        }

        Button(mainPanel) with {
            caption = "extraer"
            onClick {
                ConfirmDialog(thisWindow, modelObject) with {
                    onAccept {
                        extract()
                        cleanData()
                    }
                    open()
                }
            }
        }
    }

    private fun extract() = modelObject.extract(modelObject.selectAccount, modelObject.input)
    private fun cleanData() =  modelObject.cleanData()
}
