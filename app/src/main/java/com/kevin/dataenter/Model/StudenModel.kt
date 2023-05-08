package com.kevin.dataenter.Model

class StudenModel {

    var id: Int = 0
    var name: String
    var surname: String
    var std: String

    constructor(id: Int, name: String, surname: String, std: String) {
        this.id = id
        this.name = name
        this.surname = surname
        this.std = std
    }
}