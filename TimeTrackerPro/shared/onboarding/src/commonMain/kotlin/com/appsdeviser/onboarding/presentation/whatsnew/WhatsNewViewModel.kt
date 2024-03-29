package com.appsdeviser.onboarding.presentation.whatsnew

import com.appsdeviser.core_db.flows.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class WhatsNewViewModel(
    coroutineScope: CoroutineScope?
) {

    private val _state = MutableStateFlow(
        WhatsNewState(
            list = listOf(
                WhatsNewItem(
                    image = "https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png",
                    title = "Title 1 jhfjhesfnjk enfhewh i ekwhfkoahf iwfiewfh ",
                    description = "Description 1 eofjoaewjfo awefjoewjof askmv akdwfgeowj ewkf aowefioaew fasdkfnvf dksfviaew akfn klsdnvigheirwghaw ienfgvaiwehgioewhgasksknv  k ks dns dn d kv v  diopioo gh h  eiopreiorerghg aw eagk kw h  ehi ew ak dksfik dei diop f efot aow "
                ),
                WhatsNewItem(
                    image = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/255px-Flag_of_Canada_%28Pantone%29.svg.png",
                    title = "Title 2 oiewfriw iewfaeiw fhvefioteh",
                    description = "Description 2  oiawehfia ewawiehfga jnvjsiufgeiwf akldjnvawehghyg a aj ajow djwdnvwdnvnvajvadjsvadjsadsdsdduauw aw a  ddjdnnvv a adisudfgiewew a aohaiuwghaw n nviv i iaeiwehwygi9aw  av;anvvhjoswgewgygwaw al alw adjklwbhvbejwaegwgaow aeiowg aio hw ei egh "
                )
            )
        )
    )
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WhatsNewState())
            .toCommonStateFlow()

    fun onEvent(event: WhatsNewEvent) {
        when (event) {
            WhatsNewEvent.OnNext -> {
                if (state.value.currentSelection < state.value.list.size - 1) {
                    _state.update {
                        it.copy(
                            currentSelection = it.currentSelection + 1
                        )
                    }
                } else _state.update {
                    it.copy(
                        event = WhatsNewEvent.OnFinish
                    )
                }
            }

            WhatsNewEvent.OnPrevious -> {
                _state.update {
                    it.copy(
                        currentSelection = it.currentSelection - 1
                    )
                }
            }

            else -> Unit
        }
    }
}