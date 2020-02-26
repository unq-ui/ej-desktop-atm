package atm.model

import atm.BankSystem
import atm.BankFactory
import atm.NoCreditException
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

@Observable
class BankAppModel {
    var input = 0
    private val system: BankSystem = BankFactory.makeBankSystem()
    var accounts = initAccounts();
    var selectAccount: AccountAppModel? = null

    fun cleanData() {
        input = 0
    }

    private fun initAccounts(): MutableList<AccountAppModel> {
        return system.accounts.map { AccountAppModel(it) }.toMutableList()
    }

    fun extract(accountAppModel: AccountAppModel?, amount: Int): Int? {
        try {
            selectAccount?.balance = selectAccount?.extract(amount)!!
            return selectAccount?.balance
        } catch (e: NoCreditException) {
           throw UserException(e.message);
        }
    }
}
