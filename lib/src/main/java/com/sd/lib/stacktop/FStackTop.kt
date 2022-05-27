package com.sd.lib.stacktop

abstract class FStackTop<T> {
    private val _itemHolder = mutableListOf<T>()

    /**
     * 添加Item
     */
    @Synchronized
    fun addItem(item: T?) {
        if (item == null) return
        if (getTopItem() != item) {
            // 先移除再添加，有可能重复添加
            _itemHolder.remove(item)
            _itemHolder.add(item)
            notifyTopItem()
        }
    }

    /**
     * 移除Item
     */
    @Synchronized
    fun removeItem(item: T?) {
        if (item == null) return
        val isTop = getTopItem() == item
        _itemHolder.remove(item)
        if (isTop) {
            notifyTopItem()
        }
    }

    /**
     * 通知最顶部的Item
     */
    @Synchronized
    fun notifyTopItem() {
        updateItem(getTopItem())
    }


    /**
     * 返回最顶部的Item
     */
    @Synchronized
    fun getTopItem(): T? {
        return _itemHolder.lastOrNull()
    }

    /**
     * 更新Item
     */
    abstract fun updateItem(item: T?)
}