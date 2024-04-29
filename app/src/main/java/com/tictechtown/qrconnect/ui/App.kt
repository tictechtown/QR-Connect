package com.tictechtown.qrconnect.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tictechtown.qrconnect.data.LocalQRAccountsDataProvider
import com.tictechtown.qrconnect.ui.navigation.Screen
import com.tictechtown.qrconnect.ui.theme.AppTheme
import com.tictechtown.qrconnect.ui.theme.MotionConstants
import com.tictechtown.qrconnect.ui.theme.rememberSlideDistance


@Composable
fun App(
    homeUIState: HomeUIState,
    addNewAccount: (String, String) -> Unit,
    deleteAccount: (Long) -> Unit,
) {
    val navController = rememberNavController()
    Surface {
        AppContent(
            homeUIState = homeUIState,
            navController = navController,
            addNewAccount = addNewAccount,
            deleteAccount = deleteAccount
        )
    }
}


@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    homeUIState: HomeUIState,
    navController: NavHostController,
    addNewAccount: (String, String) -> Unit,
    deleteAccount: (Long) -> Unit,
) {

    val slideDistance = rememberSlideDistance()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route,

            popEnterTransition = {
                fadeIn(
                    animationSpec = tween(
                        durationMillis = MotionConstants.DefaultFadeInDuration.ForIncoming,
                        delayMillis = MotionConstants.DefaultFadeInDuration.ForOutgoing,
                        easing = LinearOutSlowInEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(
                        MotionConstants.DefaultMotionDuration,
                        easing = FastOutSlowInEasing
                    ),
                    initialOffset = { -slideDistance },
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },

            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        durationMillis = MotionConstants.DefaultFadeOutDuration.ForOutgoing,
                        delayMillis = 0,
                        easing = FastOutLinearInEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(
                        MotionConstants.DefaultMotionDuration,
                        easing = FastOutSlowInEasing
                    ),
                    targetOffset = {
                        -slideDistance
                    },
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )

            }
        ) {
            HomeScreen(
                homeUIState = homeUIState,
                addNewAccount = addNewAccount,
                navigateToDetail = {
                    navController.navigate(
                        Screen.Detail.createRoute(
                            id = it
                        )
                    )
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = Screen.Detail.navArguments,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        durationMillis = MotionConstants.DefaultFadeInDuration.ForIncoming,
                        delayMillis = MotionConstants.DefaultFadeInDuration.ForOutgoing,
                        easing = LinearOutSlowInEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(
                        MotionConstants.DefaultMotionDuration,
                        easing = FastOutSlowInEasing
                    ),
                    initialOffset = { slideDistance },
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        durationMillis = MotionConstants.DefaultFadeOutDuration.ForOutgoing,
                        delayMillis = 0,
                        easing = FastOutLinearInEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(
                        MotionConstants.DefaultMotionDuration,
                        easing = FastOutSlowInEasing
                    ),
                    targetOffset = {
                        slideDistance
                    },
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )

            }


        ) { backStackEntry ->

            val accId = backStackEntry.arguments?.getLong("qrId")
            DetailScreen(
                account = homeUIState.accounts.find { account ->
                    account.id == accId
                }!!,
                onBackPressed = { navController.navigateUp() },
                onDeletePressed = {
                    navController.navigateUp()
                    if (accId != null) {
                        deleteAccount(accId)
                    }
                }
            )
        }
    }
}

// Transition Animation
private const val ProgressThreshold = 0.35f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark", wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE
)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//    name = "DefaultPreviewLight"
//)
@Composable
fun QRAppPreview() {
    AppTheme {
        App(
            homeUIState = HomeUIState(
                accounts = LocalQRAccountsDataProvider.allAccounts
            ),
            addNewAccount = { _, _ -> },
            deleteAccount = {}
        )
    }
}
