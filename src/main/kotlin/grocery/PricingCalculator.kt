package grocery

import java.time.LocalDate

class PricingCalculator(val discountReferenceDate: LocalDate
           /* this is the reference date for the discounts */) {
    fun priceInPence(itemLines: List<ItemLine>, pricingDate: LocalDate) : Int {
        throw NotImplementedError("TODO")
    }
}