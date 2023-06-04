package com.example.secretvault.ui.screens.contact.contacts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secretvault.R
import com.example.secretvault.data.models.Contact
import com.example.secretvault.ui.components.EmptyContent
import com.example.secretvault.ui.theme.Purple80
import com.example.secretvault.ui.theme.PurpleGrey40
import com.example.secretvault.util.RequestState
import com.example.secretvault.util.SearchAppBarState
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun ContactsContent(
    allContacts: RequestState<List<Contact>>,
    searchedContacts: RequestState<List<Contact>>,
    searchAppBarState: SearchAppBarState,
    navigateToNewContactScreen: (contactId: Int) -> Unit
) {
    if (searchAppBarState == SearchAppBarState.TRIGGERED) {
        if (searchedContacts is RequestState.Success) {
            HandleContactsContent(
                contacts = searchedContacts.data,
                navigateToNewContactScreen = navigateToNewContactScreen
            )
        }
    } else {
        if (allContacts is RequestState.Success) {
            HandleContactsContent(
                contacts = allContacts.data,
                navigateToNewContactScreen = navigateToNewContactScreen
            )
        }
    }

}

@Composable
fun HandleContactsContent(
    contacts: List<Contact>,
    navigateToNewContactScreen: (contactId: Int) -> Unit
) {
    if (contacts.isEmpty()) {
        EmptyContent(
            title = stringResource(id = R.string.no_contacts_found)
        )
    } else {
        DisplayContacts(
            contacts = contacts,
            navigateToNewContactScreen = navigateToNewContactScreen
        )
    }
}

@Composable
fun DisplayContacts(
    contacts: List<Contact>,
    navigateToNewContactScreen: (contactId: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            items(
                items = contacts,
                key = { contact ->
                    contact.id
                }
            ) { contact ->
                ContactItem(
                    contact = contact,
                    navigateToNewContactScreen = navigateToNewContactScreen
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactItem(
    contact: Contact,
    navigateToNewContactScreen: (contactId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        onClick = {
            navigateToNewContactScreen(contact.id)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                )
                Box(
                    modifier = Modifier
                    .width(45.dp)
                        .height(45.dp)
                    .clip(CircleShape)
                    .background(Purple80),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = contact.nameAndSurname.first().toString().uppercase(),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                    )
                }
                Box(
                    modifier = Modifier
                        .width(10.dp)
                )
                Column() {
                    Text(
                        text = contact.nameAndSurname,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                    )
                    Text(
                        text = contact.number,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Light,
                        maxLines = 1,
                    )
                }
                Box(
                    modifier = Modifier
                        .width(12.dp)
                )
            }
            Box(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp))
            Divider()
        }
    }
}