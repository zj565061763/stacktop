package com.sd.lib.stacktop

abstract class FStackTop<T> {
    private val _itemHolder = mutableListOf<T>()
    private var _defaultItem: T? = null

    /**
     * 设置默认的Item
     */
    @Synchronized
    fun setDefaultItem(item: T?) {
        if (_defaultItem != item) {
            _defaultItem = item
            if (_itemHolder.isEmpty()) {
                notifyTopItem()
            }
        }
    }

    /**
     * 添加Item
     */
    @Synchronized
    fun addItem(item: T?) {
        if (item == null) return
        require(item != _defaultItem) { "item is default" }
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
        require(item != _defaultItem) { "item is default" }
        val isTop = getTopItem() == item
        _itemHolder.remove(item)
        if (isTop) {
            notifyTopItem()
        }
    }

    /**
     * 通知最顶部的Item，如果不存在，则会通知默认的Item
     */
    @Synchronized
    fun notifyTopItem() {
        updateItem(getTopItem())
    }

    /**
     * 通知默认的Item
     */
    fun notifyDefaultItem() {
        updateItem(_defaultItem)
    }

    /**
     * 返回最顶部的Item，如果不存在，则返回默认的Item
     */
    @Synchronized
    fun getTopItem(): T? {
        return _itemHolder.lastOrNull() ?: _defaultItem
    }

    /**
     * 更新Item
     */
    abstract fun updateItem(item: T?)
}