package com.shaz.sap

import android.content.Context
import android.os.AsyncTask
import android.support.annotation.NonNull
import com.shaz.sap.pojo.PackageBean
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup

/**
 * Created by Shahbaz Akhtar on 17-07-2017.
 */
class Puller private constructor() {

    private val mAppNotFound: String = "Application not found"
    private val mErrorInternet: String = "Please check your internet connection"

    private val appName = "div[class=id-app-title]"
    private val downloads = "div[itemprop=numDownloads]"
    private val version = "div[itemprop=softwareVersion]"
    private val description = "div[itemprop=description]"
    private val lastVersionDescription = "div[class=recent-change]"
    private val author = "span[itemprop=name]"
    private val ratingScore = "div[class=score]"
    private val ratingCount = "span[class=reviews-num]"
    private val locale = "div[class=local]"
    private val publishDate = "div[itemprop=datePublished]"
    private val category = "span[itemprop=genre]"

    interface PullerListener {
        fun onResult(bean: PackageBean?)
        fun onError(reason: String?)
        fun onApplicationNotFound()
    }

    companion object {
        private var mInstance = Puller()

        @JvmStatic fun getInstance(): Puller {
            return mInstance
        }
    }

    fun pull(@NonNull context: Context, @NonNull packageName: String, @NonNull pullerListener: PullerListener) {
        if (Utils.isConnectedToInternet(context))
            VersionRequest(packageName, pullerListener).execute()
        else pullerListener?.onError(mErrorInternet)
    }

    internal inner class VersionRequest(private val packageName: String, private val pullerListener: PullerListener) : AsyncTask<String, Void, Void>() {

        private var isError: Boolean = false
        private var errorMessage: String? = null
        private var bean: PackageBean? = null

        override fun doInBackground(vararg uri: String): Void? {
            try {
                val doc = Jsoup.connect("https://play.google.com/store/apps/details?id=$packageName&hl=en")
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                /*.select("div[class=app-compatibility-final]")
                .first()
                .ownText()*/
                val elemAppName = doc.select("$appName").first()
                val elemVersion = doc.select("$version").first()
                val elemCategory = doc.select("$category").first()
                val elemDownloads = doc.select("$downloads").first()
                val elemDescription = doc.select("$description").first()
                val elemLastVersionDescription = doc.select("$lastVersionDescription")
                val elemAuthor = doc.select("$author").first()
                val elemRatingScore = doc.select("$ratingScore").first()
                val elemRatingCount = doc.select("$ratingCount").first()
                val elemLocale = doc.select("$locale").first()
                val elemPublishDate = doc.select("$publishDate").first()

                bean = PackageBean()
                bean?.setPackageName(packageName)
                elemAppName?.ownText()?.let { bean?.setAppName(it) }
                elemVersion?.ownText()?.let { bean?.setVersion(it) }
                elemCategory?.ownText()?.let { bean?.setCategory(it) }
                elemDownloads?.ownText()?.let { bean?.setDownloads(it) }
                elemDescription?.text()?.let { bean?.setDescription(it) }
                if (elemLastVersionDescription?.size!! > 0) {
                    val lastVersionDescription = StringBuilder()
                    for (element in elemLastVersionDescription) {
                        element?.ownText()?.let { lastVersionDescription.append(element?.ownText() + "\n") }
                    }
                    lastVersionDescription.trim()
                    lastVersionDescription.let { bean?.setLastVersionDescription(it.toString()) }
                }
                elemAuthor?.ownText()?.let { bean?.setAuthor(it) }
                elemRatingScore?.ownText()?.let { bean?.setRatingScore(it) }
                elemRatingCount?.ownText()?.let { bean?.setRatingCount(it) }
                elemLocale?.ownText()?.let { bean?.setLocale(it) }
                elemPublishDate?.ownText()?.let { bean?.setPublishDate(it) }

            } catch (e: HttpStatusException) {
                isError = true
                errorMessage = if (e.statusCode == 404) mAppNotFound else e.message
            } catch (e: Exception) {
                isError = true
                errorMessage = e.message
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            if (!isError && bean != null) {
                pullerListener.onResult(bean)
            } else if (isError && errorMessage.equals(mAppNotFound))
                pullerListener.onApplicationNotFound()
            else
                pullerListener.onError(errorMessage)
        }
    }

}