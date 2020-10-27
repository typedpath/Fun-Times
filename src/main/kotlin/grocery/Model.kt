package grocery

data class StockItem(val product: String, val unit: String, val costInPence: Int)
data class ItemLine(val stockItem: StockItem, val quantity: Int)

object CurrentStock {
    val soup = StockItem("soup", "tin", 65)
    val bread = StockItem("bread", "loaf", 80)
    val milk = StockItem("milk", "bottle", 130)
    val apples = StockItem("apples", "single", 10)
    val all = listOf(soup, bread, milk, apples)
}