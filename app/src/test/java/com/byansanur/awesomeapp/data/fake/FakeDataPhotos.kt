package com.byansanur.awesomeapp.data.fake

import com.byansanur.awesomeapp.data.model.PhotoList
import com.byansanur.awesomeapp.data.model.response.RespListPhotos


/**
 * Created by byansanur on 9/15/2021.
 * ratbyansanur81@gmail.com
 */
object FakeDataPhotos {

    fun generateFakeDataRespPhotos() : RespListPhotos {
        return RespListPhotos(
            generateFakeDataPhotos()
        )
    }

    fun generateFakeDataPhotos() : List<PhotoList> {
        return listOf(
            PhotoList(
                "#6D5B51",
                6240,
                9533466,
                false,
                "Mitya  Zotov",
                71755548,
                "https://www.pexels.com/@mitya-zotov-71755548",
                PhotoList.SourcePhoto(
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&h=350",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&h=130",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280",
                ),
                "https://www.pexels.com/photo/woman-in-green-and-black-tank-top-9533466/",
                4160
            ),
            PhotoList(
                "#6D5B51",
                6240,
                9533466,
                false,
                "Mitya  Zotov",
                71755548,
                "https://www.pexels.com/@mitya-zotov-71755548",
                PhotoList.SourcePhoto(
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&h=350",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&h=130",
                    "https://images.pexels.com/photos/9533466/pexels-photo-9533466.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280",
                ),
                "https://www.pexels.com/photo/woman-in-green-and-black-tank-top-9533466/",
                4160
            )
        )
    }
}