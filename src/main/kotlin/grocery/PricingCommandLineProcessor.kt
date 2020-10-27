package grocery

import java.io.BufferedReader
import java.io.PrintStream

/**
 * process items on the command line and prints out prices
 */
class PricingCommandLineProcessor(val pricingCalculator: PricingCalculator,  val stockItems: List<StockItem>) {
    companion object {
        val ADD_COMMAND="add"
        val PRICE_COMMAND="price"
    }
    private fun lookupStockItem(id: String) = stockItems.filter { it.product.equals(id) }.firstOrNull()

    fun run(inputReader: BufferedReader, outputStream: PrintStream) {
        var currentItems = mutableListOf<ItemLine>()
        inputReader.lines().forEach {
            val line = it.trim()
            if (line.startsWith(ADD_COMMAND)) {
                val words = line.split(" ")
                val quantity = words[1].toIntOrNull()
                if (quantity==null) {
                    //TODO
                    throw NotImplementedError("invalid quantity not implemented ${words[1]}")
                }
                val stockItem = lookupStockItem(words[2])
                if (stockItem==null) {
                    //TODO
                    throw NotImplementedError("invalid stock item  not implemented ${words[2]}")
                }
                currentItems.add(ItemLine(stockItem, quantity))

            } else if (line.equals(PRICE_COMMAND)) {
                val price = pricingCalculator.priceInPence(currentItems.toList())
                outputStream.println("$price")
                currentItems.clear()
            }
        }
    }
}