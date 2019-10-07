package com.datly.imagechallenge.data

import com.datly.imagechallenge.data.model.ImageList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * This class used to communicate with ImgurApi and provide an Observable
 * object for ViewModel
 * @author: Dat Ly
 * Date: 10/04/2019
 */
class ImageRepository(val imgurApi: ImgurApi) {

    fun fetchImageList(pageNumber: Int, searchTerm: String): Observable<ImageList> {
        return Observable.create{ emitter ->
            imgurApi.fetchImageList(pageNumber, searchTerm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    if (it.body() != null) {
                        emitter.onNext(it.body()!!)
                    }
                }, {
                    it.printStackTrace()
                })

        }
    }
}