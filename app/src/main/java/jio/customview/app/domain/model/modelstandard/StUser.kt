package jio.customview.app.domain.model.modelstandard

import java.util.*
import kotlin.collections.ArrayList

class StUser {

    companion object {
        const val TEMPORARY_REGISTER = 1

        const val VALID = 2

        const val LOCKED = 4

        const val INVALID = 8
    }
    var id: String? = null
    var officeUserId: String? = null
    var name: String? = null
    var nameKana: String? = null
    var officeId: String? = null
    var office: String? = null
    var departmentId: String? = null
    var departmentFull: String? = null



    /**
     * Quản trị
     * 1. Admin tổng thể
     * 2. Người quản lý liên kết
     * 3. Không được phép
     */
    var mngAuthority: Int? = null

    /**
     * Quyền khác
     * Cờ bít
     * cờ bit
     * FP_1 1 << 0 Có thể sử dụng phiên truy cập / phiên họp
     * FP_2 1 << 1 Bạn có thể đặt quy tắc thăm viếng cho bệnh viện
     * FP_3 1 << Có thể xem và tải xuống 2 phiên họp
     * FP_4 1 << 3 Bạn có thể xem và tải xuống tình hình tóm tắt
     * FP_5 1 << 4 Danh sách khách hàng có thể được tải xuống
     * FP_6 1 << 5 Có thể gửi đồng thời cho các đối tác kinh doanh
     * FP_7 1 << 6 Bạn có thể thiết lập các bác sĩ phụ trách trung gian và quản lý cuộc phỏng vấn của các bác sĩ thành lập trung gian
     */
    var funcAuthority: Int? = null
        get() {
            functionAuthorityDetail?.forEach {
                //#9596
                if (it == 1)
                    return 1
            }
            return 0
        }

    var functionAuthorityDetail: ArrayList<Int>? = null

    /**
     * Giới tính
     */
    var gender: Int? = null

    /**
     * Loại công việc
     */
    var jobType: Int? = null

    var jobTypeName: String? = null

    var phone: String? = null

    var experience: Int? = null

    /**
     * Ngày tốt nghiệp với người dùng là Dr
     */
    var graduationDate: Date? = null

    /**
     * Nội dung yêu thích
     */
    var favoriteContens: ArrayList<String> = ArrayList()

    var accountStatus: Int? = 0

    /**
     * Cờ khóa tài khoản
     */
    val accountLock: Boolean
        get() {
            return accountStatus?.and(LOCKED) != 0
        }

    val accountValid: Boolean
        get() {
            return accountStatus?.and(VALID) != 0
        }

    val accountInvalid: Boolean
        get() {
            return accountStatus?.and(INVALID) ?: 0 != 0
        }

    val accountTemp: Boolean
        get() {
            return accountStatus?.and(TEMPORARY_REGISTER) != 0
        }

    val accountValidAbsolutely: Boolean
        get() {
            return accountStatus == 2
        }

    var mobilePublicType:PublicType? = null

    var emailPublicType: PublicType? = null
}