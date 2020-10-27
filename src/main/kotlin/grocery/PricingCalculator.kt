package grocery

import grocery.CurrentStock.bread
import grocery.CurrentStock.soup
import java.time.LocalDate

class PricingCalculator(val discountReferenceDate: LocalDate
           /* this is the reference date for the discounts */) {
    fun priceInPence(itemLines: List<ItemLine>, pricingDate: LocalDate) : Int {
        var totalInPence= itemLines.map { it.quantity*it.stockItem.costInPence }.sum()
        totalInPence -= calculate2SoupLoafHalfPriceDiscount(itemLines)
        return totalInPence
    }

    private fun calculate2SoupLoafHalfPriceDiscount(itemLines: List<ItemLine>) : Int {
        //TODO check date validity
        val soupCount = itemLines.filter {it.stockItem==soup }.map { it.quantity }.sum()
        val loafCount = itemLines.filter {it.stockItem==bread  }.map { it.quantity }.sum()
        return if (soupCount>=2 && loafCount>0) bread.costInPence/2 else 0
    }
}