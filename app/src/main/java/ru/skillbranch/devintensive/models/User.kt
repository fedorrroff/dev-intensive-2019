package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User (val id: String,
            var firstName: String?,
            var lastName: String?,
            var avatar: String?,
            var raiting: Int = 0,
            var respect: Int = 0,
            var lastVisit: Date? = Date(),
            var isOnline: Boolean = false){

    constructor(id: String, firstName: String?, lastName: String?): this(
            id = id,
            firstName = firstName,
            lastName = lastName,
            avatar = null
    )



    companion object Factory {
        private var lastId: Int = 0

        fun makeUser(fullName: String?): User{
            lastId++

            val (firstName, lastName) = Utils.parceFullName(fullName)

            return User(id = lastId.toString(), firstName = firstName, lastName = lastName)
        }
    }

    class Builder {
        private var id: String = ""
        private var firstName: String? = ""
        private var lastName: String? = ""
        private var avatar: String? = ""
        private var raiting: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = Date()
        private var isOnline: Boolean = false

        fun id(id: String) = apply { this.id = id }

        fun firstName(firstName: String?) = apply { this.firstName = firstName }

        fun lastName(lastName: String?) = apply{ this.lastName = lastName }

        fun avatar(avatar: String?) = apply { this.avatar = avatar }

        fun raiting(raiting: Int) = apply { this.raiting = raiting }

        fun respect(respect: Int) = apply { this.respect = respect }

        fun lastVisit (lastVisit: Date?) = apply { this.lastVisit = lastVisit }

        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }

        fun build() = User(id = id,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar,
            raiting = raiting,
            respect = respect,
            lastVisit = lastVisit,
            isOnline = isOnline
            )
    }
}
