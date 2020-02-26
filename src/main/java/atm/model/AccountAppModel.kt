package atm.model

import atm.Account
import org.uqbar.commons.model.annotations.Observable

@Observable
class AccountAppModel(currentAccount: Account) {
    var type : String = ""
    var balance : Int = 0
    var model : Account? = currentAccount

    init {
        this.type = currentAccount.type
        this.balance = currentAccount.balance
    }

    fun extract(monto: Int): Int {
        return model!!.extract(monto)
    }
}