package com.august.ua.blackout.ui.infrastructure.state

sealed class UIState {

    sealed class Validation : UIState() {
        data object NeverBeenPerformed: Validation()
        data object Passed : Validation()
        data object Failed : Validation()
        data object InProgress : Validation()
    }

    sealed class Focus : UIState() {
        data object NeverBeenFocused : Focus()
        data object FirstTimeInFocus : Focus()
        data object InFocus : Focus()
        data object OutOfFocus : Focus()
    }

    sealed class Interaction : UIState() {
        data object Enabled : Interaction()
        data object Disabled : Interaction()

        fun reverse(): Interaction {
            return when (this){
                Disabled -> Enabled
                Enabled -> Disabled
            }
        }
    }

    sealed class Selection : UIState() {
        data object Selected : Selection()
        data object Unselected : Selection()

        fun reverse(): Selection {
            return when (this){
                Selected -> Unselected
                Unselected -> Selected
            }
        }
    }

    sealed class Expand : UIState() {
        data object Expanded : Expand()
        data object Collapsed : Expand()

        fun reverse(): Expand {
            return when (this){
                Collapsed -> Expanded
                Expanded -> Collapsed
            }
        }
    }

    sealed class Progress : UIState() {
        data object NotDownloaded : Progress()
        data object InProgress : Progress()
        data object Failed : Progress()
        data object Downloaded : Progress()
        data object NotAvailable : Progress()
        data object UpdateAvailable : Progress()
    }

    sealed class MediaUploadState : UIState() {
        data object Loaded : MediaUploadState()
        data object InProgress : MediaUploadState()
        data object FailedLoading : MediaUploadState()
    }
}

fun UIState.Selection.reverse() = when (this) {
    UIState.Selection.Selected -> UIState.Selection.Unselected
    UIState.Selection.Unselected -> UIState.Selection.Selected
}