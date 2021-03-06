package com.example.petmatcher.imageutil

import android.graphics.drawable.Drawable
import android.util.LruCache
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.models.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


const val CACHE_SIZE = 25
/**
 * In memory cache of pet images
 */
@Singleton
class ImageCache @Inject constructor(private val imageLoader: ImageLoader) {

    private val cache = LruCache<Int, Drawable>(CACHE_SIZE)

    suspend fun cacheImages(pets: List<Animal>) {
        withContext(Dispatchers.Default) {
            pets.forEach { animal ->
                val photos = animal.photos
                if (photos.isNotEmpty()) {
                    imageLoader.loadImageIntoCustomTarget(photos[0].large, object: CustomTarget<Drawable>(){
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                            cache.put(animal.id, resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                    })
                }
            }
        }

    }

    fun getImage(key: Int): Drawable? {
        return cache.get(key)
    }
}