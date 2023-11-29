package com.example.infohub.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.infohub.R
data class page(
    val title:String,
    val description:String,
    @DrawableRes val image: Int
)
val pages = listOf(
    page(
        title="Welcome to the InfoHub",
        description="InfoHub provides news from all over the world",
        image= R.drawable.onboarding1
    ),
    page(
        title="Share news to your friends",
        description="InfoHub allows you to share your interest to your friends and family",
        image= R.drawable.onboarding2
    ),
    page(
        title="What's happening",
        description="InfoHub will keep you aware of the current activities happening in the world",
        image= R.drawable.onboarding3
    ),
)