package jio.customview.app.domain.model.modelstandard

/**
 * Trong nay dinh nghia 1 abstract (dang dở)
 * Khi thực thi hàm ta sẽ override
 * Nó giống như đóng bật công tắc, đang đóng thì bật, đang bật thì tắt
 */
enum class ActionState {
    ACTIVE {
        override fun reverseAction(): ActionState = INACTIVE
    },
    INACTIVE {
        override fun reverseAction(): ActionState = ACTIVE
    };

    abstract fun reverseAction(): ActionState
}