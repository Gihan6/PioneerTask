package com.qdreamcaller.creativemindstask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repo")

data class Repo(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String,
    @SerializedName("full_name")
    @Expose
    var fullName: String,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String?,
    @SerializedName("fork")
    @ColumnInfo(name = "fork")
    var fork: Boolean,
    @SerializedName("url")
    @ColumnInfo(name = "url")
    var url: String,

//
    @SerializedName("login")
    @ColumnInfo(name = "login")
    var login: String,
    @SerializedName("url")
    @ColumnInfo(name = "ownerUrl")
    var ownerUrl: String
)

