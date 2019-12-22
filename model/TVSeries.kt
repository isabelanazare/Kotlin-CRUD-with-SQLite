package com.example.app2.model

class TVSeries {

    var id: Int? = null
    var title: String? = null
    var description: String? = null
    var rating: String? = null

    constructor(id: Int, title: String, description: String, rating: String) {
        this.id = id
        this.title = title
        this.description = description
        this.rating = rating
    }

    override fun toString(): String = "ID: "+ this.id+"\nTITLE: " + this.title + "\nDESCRIPTION: " + this.description+"\nRATING: " + this.rating
}