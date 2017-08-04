package com.shaz.sap.pojo

/**
 * Created by Shahbaz Akhtar on 17-07-2017.
 */
class PackageBean {

    private var packageName: String? = ""
    private var appName: String? = ""
    private var category: String? = ""
    private var downloads: String? = ""
    private var version: String? = ""
    private var description: String? = ""
    private var lastVersionDescription: String? = ""
    private var author: String? = ""
    private var ratingScore: String? = ""
    private var ratingCount: String? = ""
    private var locale: String? = ""
    private var publishDate: String? = ""

    constructor()

    constructor(packageName: String, appName: String, category: String, downloads: String, version: String, description: String,
                lastVersionDescription: String, author: String, ratingScore: String, ratingCount: String, locale: String,
                publishDate: String) {
        this.packageName = packageName
        this.appName = appName
        this.category = category
        this.downloads = downloads
        this.version = version
        this.description = description
        this.lastVersionDescription = lastVersionDescription
        this.author = author
        this.ratingScore = ratingScore
        this.ratingCount = ratingCount
        this.locale = locale
        this.publishDate = publishDate
    }

    fun getPackageName(): String? {
        return packageName
    }

    fun getAppName(): String? {
        return appName
    }

    fun getDownloads(): String? {
        return downloads
    }

    fun getVersion(): String? {
        return version
    }

    fun getDescription(): String? {
        return description
    }

    fun getLastVersionDescription(): String? {
        return lastVersionDescription
    }

    fun getAuthor(): String? {
        return author
    }

    fun getLocale(): String? {
        return locale
    }

    fun getPublishDate(): String? {
        return publishDate
    }

    fun getCategory(): String? {
        return category
    }

    fun getRatingScore(): String? {
        return ratingScore
    }

    fun getRatingCount(): String? {
        return ratingCount
    }

    fun setPackageName(packageName: String) {
        this.packageName = packageName
    }

    fun setAppName(appName: String?) {
        this.appName = appName
    }

    fun setDownloads(downloads: String) {
        this.downloads = downloads
    }

    fun setVersion(version: String?) {
        this.version = version
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun setLastVersionDescription(lastVersionDescription: String) {
        this.lastVersionDescription = lastVersionDescription
    }

    fun setAuthor(author: String) {
        this.author = author
    }

    fun setLocale(locale: String) {
        this.locale = locale
    }

    fun setPublishDate(publishDate: String) {
        this.publishDate = publishDate
    }

    fun setCategory(category: String) {
        this.category = category
    }

    fun setRatingScore(ratingScore: String) {
        this.ratingScore = ratingScore
    }

    fun setRatingCount(ratingCount: String) {
        this.ratingCount = ratingCount
    }

}
