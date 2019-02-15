package jio.customview.app.model

class Student {
    var id: Int = 0
    var name: String? = null
    var email: String? = null
    var telephone: Int = 0
    var address: String? = null

    constructor(id: Int, name: String?, email: String?, telephone: Int, address: String?) {
        this.id = id
        this.name = name
        this.email = email
        this.telephone = telephone
        this.address = address
    }

    constructor(name: String?, email: String?, telephone: Int, address: String?) {
        this.name = name
        this.email = email
        this.telephone = telephone
        this.address = address
    }


}