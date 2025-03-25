package org.error1015.somanyenchantments.utils

import java.util.UUID

fun String.toUUID(): UUID = UUID.fromString(this)