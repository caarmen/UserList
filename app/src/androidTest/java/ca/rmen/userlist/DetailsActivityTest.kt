package ca.rmen.userlist

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.rmen.userlist.model.UserIdModel
import ca.rmen.userlist.model.UserModel
import ca.rmen.userlist.model.UserNameModel
import ca.rmen.userlist.view.DetailsActivity
import ca.rmen.userlist.viewmodel.ApiMapping
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {

    /**
     * Given a UserModel, verify that the details screen displays the expected information
     */
    @Test
    fun testUserDetailsScreen() {
        val userModel = UserModel(
            id = UserIdModel(name = "some id name", value = "some id value"),
            name = UserNameModel(first = "John", last = "Doe"),
            phone = "+1-626-555-1212"
        )
        val userDisplayData = ApiMapping.convert(userModel)
        ActivityScenario.launch<DetailsActivity>(
            DetailsActivity.getLaunchIntent(
                ApplicationProvider.getApplicationContext(),
                userDisplayData
            )
        )

        onView(withId(R.id.name)).check(matches(withText("John Doe")))
        onView(withId(R.id.phone)).check(matches(withText("+1-626-555-1212")))
    }
}