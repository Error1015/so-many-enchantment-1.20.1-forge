package org.error1015.somanyenchantments.utils

import java.util.UUID

fun String.asUUID(): UUID = UUID.fromString(this)