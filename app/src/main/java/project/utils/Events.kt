package project.utils

import android.view.View
import project.data.model.Clazz
import project.data.model.People


class EventEvent
class EventReminder
class EventJoin
class EventCreate


data class ItemClickEvent(val position: Int, val item: Clazz)

class EventOrderOption(val view: View)

class EventMemberOption(val view: View,val people: People)


class EventNotify
