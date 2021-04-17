package com.vinilcommerce.vinilcommerce.exception

class NotFoundException(message: String? = null) : Exception(message) {
    companion object {
        private const val serialVersionUID: Long = 1
    }
}