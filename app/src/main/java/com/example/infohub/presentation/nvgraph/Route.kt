package com.example.infohub.presentation.nvgraph

sealed class Route (
    val route:String
    ){
        object OnboardingScreen : Route(route ="OnboardingScreen")
        object HomeScreen : Route(route ="homeScreen")
        object SearchScreen : Route(route ="SearchScreen")
        object BookmarkScreen : Route(route ="bookmarkScreen")
        object DetailsScreen : Route(route ="detailsScreen")
        object AppStartNavigation : Route(route ="appstartNavigation")
        object NewsNavigation :Route(route ="newsNavigation")
        object NewsNavigatorScreen : Route(route ="newsNavigator")
}