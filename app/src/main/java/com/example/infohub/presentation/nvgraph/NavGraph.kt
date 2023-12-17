
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.infohub.presentation.news_navigator.components.NewsNavigator
import com.example.infohub.presentation.nvgraph.Route
import com.example.infohub.presentation.onboarding.OnBoardingViewModel
import com.example.infohub.presentation.onboarding.OnboardingScreen

@Composable
fun NavGraph (
    startDestination : String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route= Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route
        ){
            composable(
                route =Route.OnboardingScreen.route
            ){
                val viewModel:OnBoardingViewModel= hiltViewModel()
                OnboardingScreen(
                    event = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                NewsNavigator()
            }
        }
    }
}

