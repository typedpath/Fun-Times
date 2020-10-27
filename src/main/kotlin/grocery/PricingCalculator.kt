package grocery

import grocery.CurrentStock.apples
import grocery.CurrentStock.bread
import grocery.CurrentStock.soup
import java.time.LocalDate
import kotlin.math.roundToInt

/**
 * calculates prices
 */
class PricingCalculator(val discountReferenceDate: LocalDate
           /* this is the reference date for the discounts */) {
    // calculate the price in pence
    fun priceInPence(itemLines: List<ItemLine> /* the basket*/ ,
                     pricingDate: LocalDate /* the date at which to price*/ ) : Int {
        var totalInPence= itemLines.map { it.quantity*it.stockItem.costInPence }.sum()
        totalInPence -= calculate2SoupLoafHalfPriceDiscount(itemLines)
        totalInPence -= calculateApplesPriceDiscount(itemLines, pricingDate)
        return totalInPence
    }

    private fun calculate2SoupLoafHalfPriceDiscount(itemLines: List<ItemLine>) : Int {
        //TODO check date validity
        val soupCount = countByType(itemLines, soup)
        val loafCount = countByType(itemLines, bread)
        return if (soupCount>=2 && loafCount>0) bread.costInPence/2 else 0
    }

    private fun calculateApplesPriceDiscount(itemLines: List<ItemLine>, pricingDate: LocalDate) : Int {
        // check after start date
        if (pricingDate< discountReferenceDate.plusDays(5)) {
            return 0
        }
        //TODO check before end date
        val appleCount = countByType(itemLines, apples)
        val loafCount = countByType(itemLines, bread)
        return ((appleCount * apples.costInPence).toDouble() * 0.1).roundToInt()
    }

    private fun countByType(itemLines: List<ItemLine>, stockItem: StockItem)
            = itemLines.filter {it.stockItem== stockItem }.map { it.quantity }.sum()
}